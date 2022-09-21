<template>
  <!--新增用户-->
  <div class="page add-user">
    <el-form :rules="rules" :model="addParams" ref="add-form" label-width="100px" align="left">
      <el-row>
        <el-col :span="12">
          <el-form-item label="账号" prop="account">
            <el-input v-model="addParams.account" size="small" :maxlength="15"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="addParams.password" size="small" :maxlength="16"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="真实姓名" prop="realName">
            <el-input v-model="addParams.realName" size="small" :maxlength="25"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="addParams.sex">
              <el-radio :label=0>男</el-radio>
              <el-radio :label=1>女</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="手机号" prop="tel">
            <el-input v-model="addParams.tel" size="small" :maxlength="11"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="addParams.email" size="small" :maxlength="25"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="组织架构" prop="department">
            <el-input v-model="addParams.department" size="small" :maxlength="25" :readonly="true" @click.native="showDepartmentTree()"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态" align="left" prop="status">
            <el-radio-group v-model="addParams.status">
              <el-radio :label=1>启用</el-radio>
              <el-radio :label=0>停用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="QQ" prop="qq">
            <el-input v-model="addParams.qq" size="small" :maxlength="25"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="员工编号" prop="employeeId">
            <el-input v-model="addParams.employeeId" size="small" :maxlength="25"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="备注" prop="remark">
        <el-col :span="21">
          <el-input type="textarea" v-model="addParams.remark" :autosize="{ minRows:2, maxRows:4}" :maxlength="100"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item scope="scope" label-width="0px" align="center">
        <el-button @click="close">取消</el-button>
        <el-button @click="addUserCommit">确定</el-button>
      </el-form-item>
    </el-form>
    <!--组织架构弹框-->
    <el-dialog title="组织架构树" :center="true" :visible.sync="addParams.treeShow" width="300px" append-to-body @open="$nextTick(()=>$refs['department-tree-dialog'].refresh())">
      <department-tree @close="addParams.treeShow = false" @getDepartment="getDepartment"  ref="department-tree-dialog"></department-tree>
    </el-dialog>
  </div>
</template>
<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Dependencies } from "~/core/decorator";
import { State, Mutation, Getter, namespace } from "vuex-class";
import { sysUserService } from "~/services/systemweb-service/sysUser.service";
import { Emit, Prop } from "vue-property-decorator";
// import clone from "clone";
import { CityService } from "~/utils/city.service";
const systemManageModule = namespace("system-manage");
import DepartmentTree from "~/components/common/department-tree.vue"; // 新增用户
@Component({
  components: {
    DepartmentTree
  }
})
export default class AddUser extends Vue {
  @Dependencies(sysUserService) private sysUserService: sysUserService;
  @Prop() deptId: any;
  @State companyList: any;
  @State departmentList: any;
  @systemManageModule.State orgInfo;
  @Getter departmentData;
  @State userData;
  @Emit("close")
  close() {}
  @Emit("refreshList")
  refreshList() {}
  private addParams: any = {
    account: '',
    companyCode: '',
    password: '',
    realName: '',
    sex: 0,
    tel: '',
    email: '',
    department: '',
    orgId: '',
    employeeId: '',
    remark: '',
    treeShow: false,
    status: 1,
    qq: ''
  };
  private rules: any = {
    account: [
      { required: true, message: '请输入账号', trigger: 'blur' },
      {
        message: '请输入3-15位数字、字母、下划线或组合',
        trigger: 'blur',
        pattern: /^[a-zA-Z0-9_]{3,15}$/
      }
    ],
    password: [
      { required: true, message: '请输入密码', trigger: 'blur' },
      {
        message: '请输入6-16位数字、字母或组合',
        trigger: 'blur',
        pattern: /^[a-zA-Z0-9]{6,16}$/
      }
    ],
    realName: [
      { required: true, message: "请输入真实姓名", trigger: "blur" },
      {
        message: "请输入汉字或英文字母",
        trigger: "blur",
        pattern: /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/
      }
    ],
    tel: [
      { required: true, message: "请输入手机号", trigger: "blur" },
      {
        message: "请输入正确的手机号",
        trigger: "blur",
        pattern: /^1[345789]\d{9}$/
      }
    ],
    qq: [
      {
        message: "请输入正确的qq号",
        trigger: "blur",
        pattern: /^[1-9]\d{4,9}$/
      }
    ],
    email: [
      { type: "email", message: "请输入正确的邮箱", trigger: "blur" },
    ],
    department: {required: true, message: "请选择组织架构",},
    employeeId: [
      {
        message: "请输入数字和字母",
        trigger: "blur",
        pattern: /^[a-zA-Z0-9]{1,25}$/
      }
    ],
    remark: [{
      message: '请输入数字字母汉字组合',
      trigger: 'blur',
      pattern: /^[A-Za-z0-9\u4e00-\u9fa5,\.，。！!]+$/
    }]
  };
  reset(){
    let addForm: any = this.$refs["add-form"];
    addForm.resetFields();
  }
  refresh(dept) {

  }
  /**
   * 新增用户确认
   */
  addUserCommit() {
    let addForm: any = this.$refs["add-form"];
    addForm.validate(valid => {
      if (!valid) return false;
      this.sysUserService.addUser(this.addParams).subscribe(
        data => {
          this.$message.success("新增成功!");
          this.refreshList();
          this.close();
        },
        ({ msg }) => {
          this.$message.error(msg);
        }
      );
    });
  }
  showDepartmentTree() {
    this.addParams.treeShow = true
  }
  addUserCancel(){

  }
  getDepartment(param) {
    this.addParams.department=param.label
    this.addParams.orgId=param.id
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="less" scoped>
.data-grid {
  margin-bottom: 10px;
}

.header {
  border: solid 1px #dee1e5;
  border-bottom: 0px;
  height: 40px;
  line-height: 40px;
}
</style>
