<template>
  <section class="component modify-business">
    <el-form :model="modifyModel" :rules="rules" ref="modify-form" label-width="90px">
      <el-form-item label="企业名称" prop="companyName">
        <el-input v-model="modifyModel.companyName" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="企业简称" prop="shortName">
        <el-input v-model="modifyModel.shortName" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="tel">
        <el-input v-model="modifyModel.tel" :maxlength="11"></el-input>
      </el-form-item>
      <el-form-item label="联系人" prop="companyManager">
        <el-input v-model="modifyModel.companyManager" :maxlength="6"></el-input>
      </el-form-item>
      <el-form-item label="企业邮箱" prop="email">
        <el-input v-model="modifyModel.email" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="企业微信号" prop="wechatNo">
        <el-input v-model="modifyModel.wechatNo" :maxlength="20"></el-input>
      </el-form-item>
      <!-- <el-form-item label="状态" align="left" prop="status">
        <el-radio-group v-model="modifyModel.status">
          <el-radio :label=1>启用</el-radio>
          <el-radio :label=0>停用</el-radio>
        </el-radio-group>
      </el-form-item> -->
      <el-form-item label="系统名称" prop="remark">
        <el-checkbox-group v-model="modifyModel.moduleIds">
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
  export default class ModifyBusiness extends Vue {
    @Dependencies(SysModuleService) private moduleService: SysModuleService;
    @Dependencies(EnterpiseService) private enterpiseService: EnterpiseService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    private modifyModel: any = {
      companyName: "",
      shortName: "",
      tel: "",
      companyManager: "",
      email: "",
      wechatNo: "",
      status: 1,
      moduleIds: [],
      companyCode: ''
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
      wechatNo: [{required: true, message: "请输入企业微信号", trigger: "blur"}]
    };
    private checkAll: boolean = false;
    private isIndeterminate: boolean = true;
    private effectiveModule: any = []

    reset() {
      let modifyForm: any = this.$refs["modify-form"];
      modifyForm.resetFields();
      this.modifyModel.moduleIds = []
    }

    refresh(obj) {
      this.moduleService.getEffectiveModule().subscribe(
        data => {
          this.effectiveModule = data
        }, ({msg}) => {
          this.$message.error(msg)
        }
      );
      this.enterpiseService.getEnterpriseByCompanyCode(obj.companyCode).subscribe(
        data => {
          this.modifyModel.companyName = data.companyName
          this.modifyModel.shortName = data.shortName
          this.modifyModel.tel = data.tel
          this.modifyModel.companyManager = data.companyManager
          this.modifyModel.email = data.email
          this.modifyModel.wechatNo = data.wechatNo
          this.modifyModel.status = data.status
          this.modifyModel.companyCode = data.companyCode
          let arr: any = [];
          for (var i = 0; i < data.moduleIds.length; i++) {
            arr.push(data.moduleIds[i])
          }
          this.modifyModel.moduleIds = arr
        }, ({msg}) => {
          this.$message.error(msg)
        }
      );
    }

    commit() {
      let modifyForm: any = this.$refs["modify-form"];
      modifyForm.validate(valid => {
        if (!valid) return false;
        if (this.modifyModel.moduleIds.length === 0) {
          this.$message.error('请选择系统名称');
          return false;
        }
        this.enterpiseService.updateSysEnterprise(this.modifyModel).subscribe(
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

    created() {

    }

  }
</script>

<style lang="less" scoped>
  .modify-business {
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
