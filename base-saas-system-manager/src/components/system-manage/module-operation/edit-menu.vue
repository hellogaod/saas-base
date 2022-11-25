<template>
  <section class="component edit-menu moduleAdd">
    <el-form :model="editModel" :rules="rules" ref="edit-form" label-width="90px">
      <el-form-item label="父级菜单:" align="left" prop="parentName" v-show="editModel.parentName">
        <el-input v-model="editModel.parentName" readonly></el-input>
      </el-form-item>

      <el-form-item label="菜单名称" prop="menuName">
        <el-input v-model="editModel.menuName" :maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="数据脱敏:" align="left" prop="desensitizeStatus" v-if="editModel.parentName" v-show="editModel.parentName">
        <el-select v-model="editModel.desensitizeStatus">
          <el-option label="是" :value=1></el-option>
          <el-option label="否" :value=0></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="菜单序列" prop="sequence">
        <el-input v-model="editModel.sequence" name="sequence" oninput="if(value.length>3)value=value.slice(0,3)"
                  onkeyup="this.value=this.value.replace(/\s+/g,'')" type="number" min="1"></el-input>
      </el-form-item>
      <el-form-item label="菜单URL" prop="url" v-if="editModel.parentName" v-show="editModel.parentName">
        <el-input v-model="editModel.url" onkeyup="this.value=this.value.replace(/\s+/g,'')" maxlength="150"></el-input>
      </el-form-item>
      <el-form-item style="margin-top: 15px;position:relative" label="菜单图标:" prop="icon">
        <el-input placeholder="" v-model="editModel.icon" class="input-with-select inpurClass" readonly="">
          <el-button slot="append" icon="el-icon-search" style="margin-bottom:0"
                     @click="dialog.iconDialog = true"></el-button>
        </el-input>
        <i id="newIcon" :class="editModel.icon"></i>
      </el-form-item>
      <el-form-item label="描述" prop="remark">
        <el-col :span="18">
          <el-input type="textarea" class="inpurClass" v-model="editModel.remark" :rows="3" :maxlength="50"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label-width="0px">
        <el-row type="flex" justify="center">
          <el-button @click="close">取消</el-button>
          <el-button @click="commit">确定</el-button>
        </el-row>
      </el-form-item>
    </el-form>
    <el-dialog title="选择图标" :append-to-body='true' class="iconDialog" v-model="editModel.icon" :center="true"
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
  import {SysMenuService} from "~/server/services/system-manage-services/sys-menu.service";
  import iconDialog from "~/components/system-manage/module-operation/icon-dialog.vue";

  @Component({
    components: {
      iconDialog
    }
  })
  export default class AddModule extends Vue {
    @Dependencies(SysMenuService)
    private moduledetailService: SysMenuService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    private dialog: any = {
      iconDialog: false
    };
    private editModel: any = {
      menuName: "",
      menuType: "",
      desensitizeStatus: 0,
      sequence: "",
      url: "",
      remark: "",
      parentId: "",
      id: '',
      parentName: '',
      moduleId: "",
      status: 1,
      icon: "",

    };
    private rules: any = {

      menuName: [
        {required: true, message: "请输入模块名称", trigger: "blur"}
      ],
      desensitizeStatus: [
        {required: true, message: "请选择是否脱敏", trigger: "blur"}
      ],

      sequence: [{required: true, message: "请输入序列", trigger: "blur"}],
      url: [
        {required: true, message: "url菜单路径不能为空", trigger: "blur"},
        {
          message: "请输入除汉字的字符",
          trigger: "blur",
          pattern: /^[^\u4e00-\u9fa5]{0,}$/
        }
      ]
    };

    reset() {
      let addForm: any = this.$refs["edit-form"];
      addForm.resetFields();
    }

    refresh(obj) {
      this.editModel.moduleId = this.$route.params.moduleId;

      //获取菜单详情
      this.moduledetailService.getMenuById(obj.menuId).subscribe(
        data => {
          this.editModel = data;

        },
        ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    //编辑
    commit() {
      let addForm: any = this.$refs["edit-form"];
      addForm.validate(valid => {
        if (!valid) return false;
        this.moduledetailService.editMenu(this.editModel).subscribe(
          data => {
            this.$message.success("修改成功!");
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
      this.editModel.icon = name;
    }
  }
</script>

<style lang="less">
  .moduleAdd .inpurClass, .moduleAdd .el-input, .moduleAdd el-select {
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
