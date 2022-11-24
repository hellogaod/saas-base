<template>
  <!--修改用户-->
  <div class="page modify-user">
    <el-form :rules="rules" :model="modifyParams" ref="modify-form" label-width="100px" align="left">
      <el-row>
        <el-col :span="12">
          <el-form-item label="账号" prop="account">
            <el-input v-model="modifyParams.account" size="small" :maxlength="15"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="真实姓名" prop="realName">
            <el-input v-model="modifyParams.realName" size="small" :maxlength="25"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="手机号" prop="tel">
            <el-input v-model="modifyParams.tel" size="small" :maxlength="11"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="modifyParams.sex">
              <el-radio :label=0>男</el-radio>
              <el-radio :label=1>女</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="组织架构" prop="orgName">
            <el-input v-model="modifyParams.orgName" size="small" :maxlength="25" :readonly="true"
                      @click.native="showDepartmentTree()"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态" align="left" prop="status">
            <el-radio-group v-model="modifyParams.status">
              <el-radio :label=1>启用</el-radio>
              <el-radio :label=0>停用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="QQ" prop="qq">
            <el-input v-model="modifyParams.qq" size="small" :maxlength="25"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="modifyParams.email" size="small" :maxlength="25"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="员工编号" prop="employeeNo">
            <el-input v-model="modifyParams.employeeNo" size="small" :maxlength="25"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="备注" prop="remark">
        <el-col :span="21">
          <el-input type="textarea" v-model="modifyParams.remark" :autosize="{ minRows:2, maxRows:4}"
                    :maxlength="100"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item scope="scope" label-width="0px" align="center">
        <el-button @click="close">取消</el-button>
        <el-button @click="modifyUserCommit">确定</el-button>
      </el-form-item>
    </el-form>
    <!--组织架构弹框-->
    <el-dialog title="组织架构树" :center="true" :visible.sync="treeShow" width="300px" append-to-body
               @open="$nextTick(()=>$refs['department-tree-dialog'].refresh())">
      <department-tree @close="treeShow = false" @getDepartment="getDepartment"
                       ref="department-tree-dialog"></department-tree>
    </el-dialog>
  </div>
</template>
<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Dependencies} from "~/core/decorator";
  import {entUserService} from "~/server/services/enterprise-manage-services/ent-user.service";
  import {Emit, Prop} from "vue-property-decorator";
  import DepartmentTree from "~/components/common/department-tree.vue"; // 新增用户

  @Component({
    components: {
      DepartmentTree
    }
  })
  export default class AddUser extends Vue {
    @Dependencies(entUserService) private entUserService: entUserService;
    @Prop() deptId: any;

    @Emit("close")
    close() {
    }

    @Emit("refreshList")
    refreshList() {
    }

    public modifyParams: any = {
      account: '',
      companyCode: '',
      realName: '',
      sex: 0,
      tel: '',
      email: '',
      orgName: '',
      orgId: '',
      employeeNo: '',
      remark: '',
      status: 1,
      qq: '',
      userId: ''
    };
    private treeShow: Boolean = false;
    private rules: any = {
      account: [
        {required: true, message: '请输入账号', trigger: 'blur'},
        {
          message: '请输入3-15位数字、字母、下划线或组合',
          trigger: 'blur',
          pattern: /^[a-zA-Z0-9_]{3,15}$/
        }
      ],
      realName: [
        {required: true, message: "请输入真实姓名", trigger: "blur"},
        {
          message: "请输入汉字或英文字母或组合",
          trigger: "blur",
          pattern: /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/
        }
      ],
      tel: [
        {required: true, message: "请输入手机号", trigger: "blur"},
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
        {type: "email", message: "请输入正确的邮箱", trigger: "blur"},
      ],
      orgName: {required: true, message: "请选择组织架构",},
      employeeNo: [
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

    reset() {
      let modifyForm: any = this.$refs["modify-form"];
      modifyForm.resetFields();
    }

    refresh(obj) {
      this.entUserService.getUserById(obj.userId).subscribe(
        data => {
          console.log("修改用户信息：" + JSON.stringify(data))
          this.modifyParams.account = data.account
          this.modifyParams.companyCode = data.companyCode
          this.modifyParams.realName = data.realName
          this.modifyParams.sex = data.sex
          this.modifyParams.tel = data.tel
          this.modifyParams.email = data.email
          this.modifyParams.orgName = data.orgName
          this.modifyParams.orgId = data.orgId
          this.modifyParams.employeeNo = data.employeeNo
          this.modifyParams.remark = data.remark
          this.modifyParams.account = data.account
          this.modifyParams.status = data.status
          this.modifyParams.qq = data.qq
          this.modifyParams.userId = data.userId
        },
        ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    /**
     * 修改用户确认
     */
    modifyUserCommit() {
      let modifyForm: any = this.$refs["modify-form"];
      modifyForm.validate(valid => {
        if (!valid) return false;
        this.entUserService.updateUser(this.modifyParams).subscribe(
          data => {
            this.$message.success("保存成功!");
            this.refreshList();
            this.close();
          },
          ({msg}) => {
            this.$message.error(msg);
          }
        );
      });
    }

    showDepartmentTree() {
      this.treeShow = true
    }

    modifyUserCancel() {

    }

    getDepartment(param) {
      this.modifyParams.orgName = param.orgName
      this.modifyParams.orgId = param.orgId
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
