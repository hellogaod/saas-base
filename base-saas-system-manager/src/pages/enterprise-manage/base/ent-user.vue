<template>
  <!--消费金融-用户管理-->
  <section class="page user-manage">
    <data-form :model="userModel" @onSearch="refreshData" :page="pageUtil">
      <template slot="default-input">
        <el-form-item label="账号" prop="account">
          <el-input v-model="userModel.account"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="realName">
          <el-input v-model="userModel.realName"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="tel">
          <el-input v-model="userModel.tel"></el-input>
        </el-form-item>
      </template>
      <template slot="default-button">
        <el-button @click="dialog.add = true">新增</el-button>
      </template>
    </data-form>
    <data-box :data="userDataSet" @onPageChange="refreshData" :page="pageUtil" ref="data-box">
      <template slot="columns">
        <el-table-column prop="account" label="登录账号" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="realName" label="真实姓名" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="tel" label="联系电话" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="status" label="状态" :min-width="$helper.getColumnWidth(2)">
          <template slot-scope="scope">
            <span v-if="scope.row.status == 1">启用</span>
            <span v-if="scope.row.status == 0">停用</span>
            <span v-if="scope.row.status == 2">锁定</span>
          </template>
        </el-table-column>
        <el-table-column prop="errorCount" label="登录错误次数" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="latestTime" label="最后一次登录时间" :min-width="$helper.getColumnWidth(4)">
          <template slot-scope="scope">
            <span>{{$dateutils.dateTimeFormat(scope.row.latestTime)}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="lockTime" label="锁定时间" :min-width="$helper.getColumnWidth(4)">
          <template slot-scope="scope">
            <span>{{$dateutils.dateTimeFormat(scope.row.lockTime)}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="修改时间" :min-width="$helper.getColumnWidth(4)">
          <template slot-scope="scope">
            <span>{{$dateutils.dateTimeFormat(scope.row.updateTime)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" :min-width="$helper.getColumnWidth(10)">
          <template slot-scope="scope">
            <el-button type="text" @click="dialog.modify=true,userObj=scope.row">修改
            </el-button>
            <el-button type="text" @click="dialog.moduleRole=true,userObj=scope.row">角色分配
            </el-button>
            <el-button type="text" @click="enableClick(scope)"
                       v-if="scope.row.status === 0">启用
            </el-button>
            <el-button type="text" @click="disableClick(scope)"
                       v-if="scope.row.status === 1">停用
            </el-button>
            <el-button type="text" size="small" @click="resetPassword(scope)">重置密码
            </el-button>
          </template>
        </el-table-column>
      </template>
    </data-box>
    <!--新增用户-->
    <el-dialog title="新增" :center="true" :visible.sync="dialog.add" width="750px" @close="$refs['add-user'].reset()"
               @open="$nextTick(()=>$refs['add-user'].refresh())">
      <add-user @close="dialog.add = false" @refreshList="refreshData" ref="add-user"></add-user>
    </el-dialog>
    <!--修改用户-->
    <el-dialog title="修改" :center="true" :visible.sync="dialog.modify" width="750px"
               @close="$refs['modify-dialog'].reset()" @open="$nextTick(()=>$refs['modify-dialog'].refresh(userObj))">
      <modify-user @close="dialog.modify = false" @refreshList="refreshData" ref="modify-dialog"></modify-user>
    </el-dialog>
    <!--修改用户角色-->
    <el-dialog title="角色分配" :center="true" :visible.sync="dialog.moduleRole" width="900px"
               @open="$nextTick(()=>$refs['module-role'].refresh(userObj))">
      <module-role @close="dialog.moduleRole = false" @refreshList="refreshData" ref="module-role"></module-role>
    </el-dialog>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Auth, Layout, Dependencies} from "~/core/decorator";
  import {entUserService} from "~/server/services/enterprise-manage-services/ent-user.service";
  import {PageUtil} from "~/utils/page.util";
  // import { DepartmentInfoService } from "~/services/datasync-service/department-info.service";
  import DataForm from "~/components/common/data-form.vue";
  import DataBox from "~/components/common/data-box.vue";
  import ModifyUser from "~/components/enterprise-manage/user-manage/modify-user.vue";
  import AddUser from "~/components/enterprise-manage/user-manage/add-user.vue";
  import ModuleRole from "~/components/enterprise-manage/user-manage/module-role.vue";

  @Layout("workspace")
  @Component({
    components: {
      ModifyUser,
      DataForm,
      DataBox,
      AddUser,
      ModuleRole
    }
  })
  export default class UserManage extends Vue {
    @Dependencies(entUserService) private entUserService: entUserService;
    @Dependencies(PageUtil) private pageUtil: PageUtil;
    private userDataSet = null;
    private userModel: any = {
      account: "",
      realName: "",
      tel: ""
    };
    private userObj: any = {};
    private dialog: any = {
      add: false,
      modify: false,
      moduleRole: false
    };

    /**
     * 启用
     */
    enableClick(scope, type) {
      this.$confirm('确认启用吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.sysUserService.updateStatus({status: '1', userId: scope.row.userId}).subscribe(data => {
          this.$message({
            type: 'success',
            message: '启用成功!'
          })
          this.pageUtil.reset()
          this.refreshData()
        }, ({msg}) => {
          this.$message.error(msg);
        })
      }).catch(() => {
      })
    }

    /**
     * 停用
     */
    disableClick(scope, type) {
      this.$confirm('确认停用吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.sysUserService.updateStatus({status: '0', userId: scope.row.userId}).subscribe(data => {
          this.$message({
            type: 'success',
            message: '停用成功!'
          })
          this.pageUtil.reset()
          this.refreshData()
        }, ({msg}) => {
          this.$message.error(msg);
        })
      }).catch(() => {
      })
    }

    /*重置密码
    **/
    resetPassword(scope) {
      this.$confirm('确认重置密码吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.sysUserService.resetPassword(scope.row.userId).subscribe(data => {
          this.$message({
            type: 'success',
            message: '重置成功!'
          })
          this.pageUtil.reset();
          this.refreshData();
        }, ({msg}) => {
          this.$message.error(msg);
        })
      }).catch(() => {
      })
    }

    refreshData() {
      this.sysUserService.getUserList(this.userModel, this.pageUtil).subscribe(
        data => {
          this.userDataSet = data.list;
        },
        ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    deactivated() {
      for (let v in this.dialog) {
        this.dialog[v] = false;
      }
    }

    mounted() {
      this.refreshData()
    }
  }
</script>

<style lang="less">
  .tree-header {
    padding: 10px 20px;
    border-bottom: 1px solid #e4e4e4;
  }
</style>
