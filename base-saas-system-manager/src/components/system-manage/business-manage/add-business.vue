<template>
  <section class="component add-business">
    <el-form :model="addModel" :rules="rules" ref="add-form" label-width="90px">
      <el-form-item label="企业名称" prop="companyName">
        <el-input v-model="addModel.companyName" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="企业简称" prop="shortName">
        <el-input v-model="addModel.shortName" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="tel">
        <el-input v-model="addModel.tel" :maxlength="11"></el-input>
      </el-form-item>
      <el-form-item label="联系人" prop="companyManager">
        <el-input v-model="addModel.companyManager" :maxlength="6"></el-input>
      </el-form-item>
      <el-form-item label="企业邮箱" prop="email">
        <el-input v-model="addModel.email" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="超管账号" prop="adminAccount">
        <el-input v-model="addModel.adminAccount" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="超管密码" prop="adminPassword">
        <el-input v-model="addModel.adminPassword" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="微信号" prop="wechatNo">
        <el-input v-model="addModel.wechatNo" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="状态" align="left" prop="status">
        <el-radio-group v-model="addModel.status">
          <el-radio :label=1>启用</el-radio>
          <el-radio :label=0>停用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="系统名称" prop="remark">
        <el-checkbox-group v-model="addModel.moduleIds">
          <el-checkbox v-for="item in effectiveModule" :key="item.moduleId" :label="item.moduleId">{{item.moduleName}}
          </el-checkbox>
        </el-checkbox-group>
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
  import {EnterpiseService} from "~/server/services/system-manage-services/sys-enterprise.service";
  import {Emit} from "vue-property-decorator";

  @Component({
    components: {}
  })
  export default class AddBussiness extends Vue {
    @Dependencies(SysModuleService) private moduleService: SysModuleService;
    @Dependencies(EnterpiseService) private enterpiseService: EnterpiseService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    private addModel: any = {
      companyName: "",
      shortName: "",
      tel: "",
      companyManager: "",
      email: "",
      adminAccount:'',
      adminPassword:'',
      wechatNo: "",
      status: 1,
      moduleIds: []
    };
    private rules: any = {
      companyName: [{required: true, message: "请输入企业名称", trigger: "blur"}, {
        message: "请输入汉字或英文字母",
        trigger: "blur",
        pattern: /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/
      }],
      shortName: [{required: true, message: "请输入企业简介", trigger: "blur"}, {
        message: "请输入汉字或英文字母",
        trigger: "blur",
        pattern: /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/
      }],
      tel: [{required: true, message: "请输入联系电话", trigger: "blur"}, {
        message: "请输入正确的手机号",
        trigger: "blur",
        pattern: /^1[345789]\d{9}$/
      }],
      companyManager: [{required: true, message: "请输入联系人", trigger: "blur"}, {
        message: "请输入汉字或英文字母",
        trigger: "blur",
        pattern: /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/
      }],
      email: [{required: true, message: "请输入企业邮箱", trigger: "blur"}, {
        message: "请输入正确的邮箱",
        trigger: "blur",
        pattern: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
      }],
      adminAccount: [{required: true, message: "请输入超级管理员账号", trigger: "blur"}],
      adminPassword: [{required: true, message: "请输入超级管理员密码", trigger: "blur"}],
      wechatNo: [{required: true, message: "请输入企业微信号", trigger: "blur"}],

    };
    private checkAll: boolean = false;
    private isIndeterminate: boolean = true;
    private effectiveModule: any = []

    reset() {
      let addForm: any = this.$refs["add-form"];
      addForm.resetFields();
      this.addModel.moduleIds = []
    }

    commit() {
      let addForm: any = this.$refs["add-form"];
      addForm.validate(valid => {
        if (!valid) return false;
        if (this.addModel.moduleIds.length === 0) {
          this.$message.error('请选择系统名称');
          return false;
        }
        this.enterpiseService.addSysEnterprise(this.addModel).subscribe(
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

    created() {
      this.moduleService.getEffectiveModule().subscribe(
        data => {
          this.effectiveModule = data
        }, ({msg}) => {
          this.$message.error(msg)
        }
      );
    }

  }
</script>

<style lang="less" scoped>
  .add-business {
    .el-input {
      width: 80% !important;
    }

    .el-checkbox-group {
      overflow: hidden;

      .el-checkbox {
        float: left;
        margin-left: 0;
        width: 40%;
      }
    }
  }
</style>
