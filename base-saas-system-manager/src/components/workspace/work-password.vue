<template>
  <section class="component work-password">
    <el-form :model="passwordModel" :rules="rules" ref="password-form" label-width="120px" class="text-left">
      <el-form-item label="旧密码" prop="oldPassword">
        <el-input type="password" v-model="passwordModel.oldPassword" :maxlength="16" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input type="password" v-model="passwordModel.newPassword" :maxlength="16" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认新密码" prop="confirmPassword">
        <el-input type="password" v-model="passwordModel.confirmPassword" :maxlength="16"></el-input>
      </el-form-item>
    </el-form>
    <el-row type="flex" justify="center" style="margin-top:40px">
      <el-button @click="close">取消</el-button>
      <el-button @click="confirm">确定</el-button>
    </el-row>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Dependencies } from "~/core/decorator";
import { webLoginService } from "~/services/systemweb-services/syswebLogin.service";
import { LoginService } from "~/services/manager-services/sysLogin.service";
import { Emit } from "vue-property-decorator";
import { Form } from "element-ui";
// import md5 from "md5";

@Component({
  components: {}
})
export default class WorkPassword extends Vue {
  @Dependencies(webLoginService) private webLoginService: webLoginService;
  @Dependencies(LoginService) private loginService: LoginService;
  @Emit("close")
  close() {
    this.reset();
  }
  private passwordForm: Form;
  private passwordModel: any = {
    oldPassword: "",
    newPassword: "",
    confirmPassword: ""
  };
  /**
   * 验证密码
   */
  private validatePassword = (rule, value, callback) => {
    let pwd = this.passwordModel.newPassword;
    let repwd = this.passwordModel.confirmPassword;

    if (pwd !== "" && repwd !== "" && pwd !== repwd) {
      callback(new Error("请确认两次输入密码是否一致"));
    } else {
      callback();
    }
  };

  // 数据校验规则
  private rules: any = {
    oldPassword: [
      { required: true, message: "请输入原密码", trigger: "blur" },
      {
        min: 6,
        max: 16,
        message: "密码长度不能小于6位大于16位",
        trigger: "blur"
      },
      {
        message: '请输入6-16位数字、字母组合',
        trigger: 'blur',
        pattern: /^[a-zA-Z0-9]{6,16}$/
      }
    ],
    newPassword: [
      { required: true, message: "请输入新密码", trigger: "blur" },
      { validator: this.validatePassword, trigger: "blur" },
      {
        min: 6,
        max: 16,
        message: "密码长度不能小于6位大于16位",
        trigger: "blur"
      },
      {
        message: '请输入6-16位数字、字母组合',
        trigger: 'blur',
        pattern: /^[a-zA-Z0-9]{6,16}$/
      }
    ],
    confirmPassword: [
      { required: true, message: "请再次输入新密码", trigger: "blur" },
      { validator: this.validatePassword, trigger: "blur" },
      {
        min: 6,
        max: 16,
        message: "密码长度不能小于6位大于16位",
        trigger: "blur"
      },
      {
        message: '请输入6-16位数字、字母组合',
        trigger: 'blur',
        pattern: /^[a-zA-Z0-9]{6,16}$/
      }
    ]
  };
  /**
   * 确定
   */
  confirm() {
    this.passwordForm.validate(valid => {
      if (!valid) {
        return false;
      }
      if(sessionStorage.getItem("loginType")==="sys"){
        this.webLoginService.updateUserPwd({
          oldPwd: this.passwordModel.oldPassword,
          newPwd: this.passwordModel.newPassword
        }).subscribe(
          data => {
            this.$message.success("修改密码成功");
            this.close();
          },
          ({ msg }) => {
            this.$message.error(msg);
          }
        );
      }else{
        this.loginService.updateUserPwd({
          oldPwd: this.passwordModel.oldPassword,
          newPwd: this.passwordModel.newPassword
        }).subscribe(
          data => {
            this.$message.success("修改密码成功");
            this.close();
          },
          ({ msg }) => {
            this.$message.error(msg);
          }
        );
      }
    });
  }
  reset() {
    let form: any = this.$refs["password-form"];
    form.resetFields();
  }

  mounted() {
    this.passwordForm = <Form>this.$refs["password-form"];
  }
}
</script>

<style lang="less">

</style>
