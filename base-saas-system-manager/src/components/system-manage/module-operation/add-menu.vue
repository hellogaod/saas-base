<template>
  <section class="component add-menu moduleAdd">
    <el-form :model="addModel" :rules="rules" ref="add-form" label-width="90px">
      <el-form-item label="父级菜单:" align="left" prop="parentName" v-show="parentMenu">
        <el-select class="inpurClass" v-model="addModel.id" @change="changeParentName">
          <el-option v-for="(item,index) in addModel.options" :key="index" :label="item.menuName" :value="item.id">
            {{item.menuName}}
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="菜单类型:" align="left" prop="menuType">
        <el-select class="inpurClass" ref="menuType" v-model="addModel.menuType" @change="changeMenu">
          <el-option v-show="item.flag" :label="item.key" :value="item.value" v-for="(item,index) in typeOption"
                     :key="index"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="菜单名称" prop="menuName">
        <el-input v-model="addModel.menuName" :maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="数据脱敏:" align="left" prop="desensitizeStatus" v-show="show">
        <el-select v-model="addModel.desensitizeStatus">
          <el-option label="是" :value=1></el-option>
          <el-option label="否" :value=0></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="权限分配:" align="left" prop="authStatus" v-show="show">
        <el-select v-model="addModel.authStatus">
          <el-option label="是" :value=1></el-option>
          <el-option label="否" :value=0></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="菜单序列" prop="sequence">
        <el-input v-model="addModel.sequence" name="sequence" oninput="if(value.length>3)value=value.slice(0,3)"
                  onkeyup="this.value=this.value.replace(/\s+/g,'')" type="number" min="1"></el-input>
      </el-form-item>
      <el-form-item label="菜单URL" prop="url" v-show="url">
        <el-input v-model="addModel.url" onkeyup="this.value=this.value.replace(/\s+/g,'')" maxlength="150"></el-input>
      </el-form-item>
      <el-form-item style="margin-top: 15px;position:relative" label="菜单图标:" prop="icon">
        <el-input placeholder="" v-model="addModel.icon" class="input-with-select inpurClass" readonly="">
          <el-button slot="append" icon="el-icon-search" style="margin-bottom:0"
                     @click="dialog.iconDialog = true"></el-button>
        </el-input>
        <i id="newIcon" :class="addModel.icon"></i>
      </el-form-item>
      <el-form-item label="描述" prop="remark">
        <el-col :span="18">
          <el-input type="textarea" class="inpurClass" v-model="addModel.remark" :rows="3" :maxlength="50"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label-width="0px">
        <el-row type="flex" justify="center">
          <el-button @click="close">取消</el-button>
          <el-button @click="commit">确定</el-button>
        </el-row>
      </el-form-item>
    </el-form>
    <el-dialog title="选择图标" :append-to-body='true' class="iconDialog" v-model="addModel.icon" :center="true"
               :visible.sync="dialog.iconDialog" width="700px">
      <icon-dialog @close="dialog.iconDialog = false" ref="icon-dialog" @iconRefresh="iconRefresh"></icon-dialog>
    </el-dialog>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Dependencies} from "~/core/decorator";
  import {Emit, Prop} from "vue-property-decorator";
  import {SysModuleOperationService} from "~/server/services/system-manage-services/sys-module-operation.service";
  import iconDialog from "~/components/system-manage/module-operation/icon-dialog.vue";

  @Component({
    components: {
      iconDialog
    }
  })
  export default class AddModule extends Vue {
    @Dependencies(SysModuleOperationService)
    private moduledetailService: SysModuleOperationService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    private dialog: any = {
      iconDialog: false
    };
    private show: boolean = false;
    private url: boolean = true;
    private parentMenu: boolean = false;
    private flag: any = {};
    private addModel: any = {
      menuName: "",
      menuType: "",
      desensitizeStatus: "0",
      authStatus: "0",
      sequence: "",
      url: "",
      remark: "",
      parentId: "",
      sysCode: "",
      status: 1,
      icon: "",
      options: [{
        parentName: ''
      }]
    };
    private rules: any = {
      id: [
        {required: false, message: "请选择一项", trigger: "blur"}
      ],
      menuName: [
        {required: true, message: "请输入模块名称", trigger: "blur"}
      ],
      menuType: [{required: true, message: "请选择一项", trigger: "blur"}],
      desensitizeStatus: [
        {required: true, message: "请选择一项", trigger: "blur"}
      ],
      authStatus: [
        {required: true, message: "请选择一项", trigger: "blur"}
      ],
      sequence: [{required: true, message: "请选择一项", trigger: "blur"}],
      url: [
        {required: true, message: "不能为空", trigger: "blur"},
        {
          message: "请输入除汉字的字符",
          trigger: "blur",
          pattern: /^[^\u4e00-\u9fa5]{0,}$/
        }
      ]
    };
    private typeOption: any = [
      {
        key: '菜单',
        value: 1,
        flag: true
      },
      {
        key: '按钮',
        value: 2,
        flag: true
      },
      // {
      //     key: '组件',
      //     value: 3,
      //     flag: true
      // }
    ]
    private addObj: any = {};

    reset() {
      let addForm: any = this.$refs["add-form"];
      addForm.resetFields();
      this.show = false;
      this.addModel.desensitizeStatus = "0";
      this.addModel.authStatus = "0";
      this.rules.url[0].required = true;
    }

    //菜单类型切换
    changeMenu(obj) {
      if (this.addObj.pid === "#") {//根节点 菜单不展示 组件展示
        if (obj === 1) {
          this.show = false;
          this.addModel.desensitizeStatus = "0";
          this.addModel.authStatus = "0";
          this.rules.url[0].required = false;
          this.url = false;
        } else {
          this.rules.url[0].required = true;
          this.url = true;
          this.show = true;
          this.addModel.desensitizeStatus = "";
          this.addModel.authStatus = "";
        }
      } else {
        if (obj === 1) { //只有菜单
          this.rules.url[0].required = true;
          this.url = true;
          this.show = true;
          this.addModel.desensitizeStatus = "";
          this.addModel.authStatus = "";
        } else {
          this.rules.url[0].required = true;
          this.url = true;
          this.show = false;
          this.addModel.desensitizeStatus = "0";
          this.addModel.authStatus = "0";
        }
      }
    }

    //父菜单
    changeParentName(obj) {
      this.addModel.parentId = obj;
    }

    refresh(obj) {
      this.addObj = obj;
      this.typeOption[0].flag = true;
      this.typeOption[1].flag = true;
      // this.typeOption[2].flag=true;
      this.addModel.sysCode = this.$route.params.sysCode;
      if (obj.pid === "#") {
        this.parentMenu = false;
        this.addModel.parentName = '';
        this.addModel.parentId = "#";
        this.addModel.id = "";
        this.rules.id[0].required = false;
        this.typeOption[1].flag = false;//隐藏 按钮
      } else {
        this.parentMenu = true;
        this.rules.id[0].required = true;
        if (obj.menuType === 2) {//按钮
          this.flag = 2;
          this.typeOption[0].flag = false;//隐藏 菜单和组件
          // this.typeOption[2].flag=false;//隐藏 菜单和组件
        } else {
          this.flag = 1;
          this.typeOption[1].flag = false;//隐藏 按钮和组件
          // this.typeOption[2].flag=false;//隐藏 按钮和组件
        }
        //获取一级菜单
        this.moduledetailService.getOneMenu(this.flag, this.addModel.sysCode).subscribe(
          data => {
            this.addModel.options = data;
            for (var i = 0; i < this.addModel.options.length; i++) {
              if (this.addModel.options[i].id === obj.pid) {
                this.addModel.id = this.addModel.options[i].id
              }
            }
          },
          ({msg}) => {
            this.$message.error(msg);
          }
        );
      }
    }

    //新增
    commit() {
      let addForm: any = this.$refs["add-form"];
      addForm.validate(valid => {
        if (!valid) return false;
        if (this.addObj.pid != "#") {
          this.addModel.parentId = this.addModel.id;
          for (var i = 0; i < this.addModel.options.length; i++) {
            if (this.addModel.options[i].id === this.addModel.id) {
              this.addModel.parentName = this.addModel.options[i].menuName
            }
          }
        }
        this.moduledetailService.addMenu(this.addModel).subscribe(
          data => {
            this.$message.success("新增成功!");
            this.refreshList();
            this.close();
          },
          ({msg}) => {
            this.$message.error(msg);
          }
        );
      });
    }

    iconRefresh(name) {
      this.addModel.icon = name;
    }
  }
</script>

<style lang="less">
  .moduleAdd .inpurClass,
  .moduleAdd .el-input,
  .moduleAdd el-select {
    width: 300px !important;
  }

  .moduleAdd .el-select .el-input .el-input--suffix input {
    width: 300px !important;
  }

  .moduleAdd .input-with-select .el-input__inner {
    background: #eee;
  }

  .moduleAdd .el-button--default {
    margin-top: -1px !important;
    padding: 0 10px !important;
    min-width: 50px !important;
  }

  .moduleAdd .v-modal {
    z-index: 2004 !important;
  }

  #newIcon {
    display: inline-block !important;
    position: relative;
    right: 80px;
    border: 1px solid #eee;
    padding: 5px;
  }
</style>
