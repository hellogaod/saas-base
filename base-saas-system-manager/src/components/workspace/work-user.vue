<template>
  <section class="component work-user">
    <el-dropdown @command="dropdownClickHandle" trigger="click">
      <div class="row middle-span">
        <img class="user-photo" src="@/assets/images/common/user-photo.png" />
        <span class="user-name">欢迎您, {{userData.realName}}</span>
        <i class="el-icon-arrow-down el-icon--right"></i>
      </div>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="password">修改密码</el-dropdown-item>
        <el-dropdown-item command='exit'>退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-dialog :visible.sync="dialog.workPassword" width="500px" title="修改密码" @close="$refs['work-password'].reset()">
      <work-password ref="work-password" @close="dialog.workPassword=false"></work-password>
    </el-dialog>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { State, Mutation } from "vuex-class";
import { Dependencies } from "~/core/decorator";
import WorkPassword from "~/components/workspace/work-password.vue";
import { webLoginService } from "~/services/systemweb-services/syswebLogin.services";
import { LoginService } from "~/services/manager-services/sysLogin.services";
@Component({
  components: {
    WorkPassword
  }
})
export default class WorkUser extends Vue {
  @Dependencies(webLoginService) private webLoginService: webLoginService;
  @Dependencies(LoginService) private loginService: LoginService;
  @State userData;
  
  private dialog = {
    workPassword: false
  };

  /**
   *  用户操作处理
   */
  dropdownClickHandle(command) {
    switch (command) {
      case "exit": 
        this.loginOut()
        break;
      case "password": 
        this.dialog.workPassword = true
        break;      
    }
  }
  /*
   * 退出
   * */ 
  loginOut() {
    if(sessionStorage.getItem("loginType")==="sys"){//system-web
      this.webLoginService.logout().subscribe(
          data => {
            this.$router.push("/system-web");
            sessionStorage.setItem("loginType","");
          },
          ({ msg }) => {
            this.$message.error(msg);
          }
        );
    }else{
      this.loginService.logout().subscribe(
          data => {
            this.$router.push("/");
            sessionStorage.setItem("loginType","");
          },
          ({ msg }) => {
            this.$message.error(msg);
          }
        );
    }
  }
}
</script>

<style lang="less" scoped>
.work-user.component {
  * {
    padding: 0 10px;
    color: white;
  }
}
</style>
