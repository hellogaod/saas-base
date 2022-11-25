<template>
  <section class="component add-menu moduleAdd">
    <el-form :model="addModel" :rules="rules" ref="add-form" label-width="90px">

      <el-form-item label="菜单名称" prop="menuName">
        <el-input v-model="addModel.menuName" :maxlength="10"></el-input>
      </el-form-item>

      <el-form-item label="菜单序列" prop="sequence">
        <el-input v-model="addModel.sequence" name="sequence" oninput="if(value.length>3)value=value.slice(0,3)"
                  onkeyup="this.value=this.value.replace(/\s+/g,'')" type="number" min="1"></el-input>
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
    private addModel: any = {
      menuName: "",
      sequence: "",
      remark: "",
      parentId: "#",
      moduleId: "",
      status: 1,
      icon: ""
    };
    private rules: any = {

      menuName: [
        {required: true, message: "请输入模块名称", trigger: "blur"}
      ],
      sequence: [{required: true, message: "请输入菜单序列", trigger: "blur"}],
    };

    reset() {
      let addForm: any = this.$refs["add-form"];
      addForm.resetFields();
    }

    refresh(obj) {
      this.addModel.moduleId = this.$route.params.moduleId;

    }

    //新增
    commit() {
      let addForm: any = this.$refs["add-form"];
      addForm.validate(valid => {
        if (!valid) return false;

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
