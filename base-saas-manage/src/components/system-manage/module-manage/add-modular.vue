<template>
  <section class="component add-modular">
    <el-form :model="addModel" :rules="rules" ref="add-form" label-width="90px">
      <el-form-item label="模块名称" prop="moduleName">
        <el-input v-model="addModel.moduleName" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="状态" align="left" prop="status">
        <el-select v-model="addModel.status">
          <el-option label="启用" :value=1></el-option>
          <el-option label="停用" :value=0></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-col :span="18">
          <el-input type="textarea" v-model="addModel.remark" :rows="3" :maxlength="500"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label-width="0px">
        <el-row type="flex" justify="center">
          <el-button @click="close">取消</el-button>
          <el-button @click="commit">确定</el-button>
        </el-row>
      </el-form-item>
    </el-form>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Dependencies} from "~/core/decorator";
  import {SysModuleService} from "~/server/services/system-manage-services/sys-module.service";
  import {Emit} from "vue-property-decorator";

  @Component({
    components: {}
  })
  export default class AddModule extends Vue {
    @Dependencies(SysModuleService) private moduleService: SysModuleService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    private addModel: any = {
      moduleName: "",
      status: 1,
      remark: ""
    };
    private rules: any = {
      moduleName: [{required: true, message: "请输入模块名称", trigger: "blur"}],
    };

    reset() {
      let addForm: any = this.$refs["add-form"];
      addForm.resetFields();
    }

    commit() {
      let addForm: any = this.$refs["add-form"];
      addForm.validate(valid => {
        if (!valid) return false;
        this.moduleService.saveSysModule(this.addModel).subscribe(
          data => {
            this.$message.success("新增成功!");
            this.refreshList();
            this.close();
          }, ({msg}) => {
            this.$message.error(msg);
          }
        );
      });
    }

    mounted() {
    }
  }
</script>

<style lang="less" scoped>

</style>
