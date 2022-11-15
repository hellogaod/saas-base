<template>
  <section class="component modify-role">
    <el-form :model="modifyModel" :rules="rules" ref="modify-form" label-width="90px">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="modifyModel.roleName" :maxlength="20"></el-input>
      </el-form-item>
      <!-- <el-form-item label="状态" align="left" prop="status">
        <el-radio-group v-model="modifyModel.status">
        <el-radio :label=1>启用</el-radio>
        <el-radio :label=0>停用</el-radio>
      </el-radio-group>
      </el-form-item> -->
      <el-form-item label="描述" prop="remark">
        <el-col :span="18">
          <el-input type="textarea" v-model="modifyModel.remark" :rows="3" :maxlength="50"></el-input>
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
  import {entRoleService} from "~/server/services/enterprise-manage-services/ent-role.service";
  import {Emit} from "vue-property-decorator";

  @Component({
    components: {}
  })
  export default class ModifyRole extends Vue {
    @Dependencies(entRoleService) private entRoleService: entRoleService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    private modifyModel: any = {
      companyCode: '',
      orgId: '',
      roleName: "",
      roleCode: '',
      roleId: '',
      status: 1,
      remark: ""
    };
    private rules: any = {
      roleName: [{required: true, message: "请输入角色名称", trigger: "blur"}],
      remark: [{
        message: '请输入数字字母汉字组合',
        trigger: 'blur',
        pattern: /^[A-Za-z0-9\u4e00-\u9fa5,\.，。！!  ]+$/
      }]
    };

    reset() {
      let modifyForm: any = this.$refs["modify-form"];
      modifyForm.resetFields();
    }

    refresh(obj) {
      this.sysRoleService.getRoleById(obj.roleId).subscribe(
        data => {
          this.modifyModel = data
        }, ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    commit() {
      let modifyForm: any = this.$refs["modify-form"];
      modifyForm.validate(valid => {
        if (!valid) return false;
        this.modifyModel.resources = null;
        this.sysRoleService.updateRole(this.modifyModel).subscribe(
          data => {
            this.$message.success("修改成功!");
            this.refreshList();
            this.close();
          }, ({msg}) => {
            this.$message.error(msg);
          }
        );
      });
    }
  }
</script>

<style lang="less" scoped>

</style>
