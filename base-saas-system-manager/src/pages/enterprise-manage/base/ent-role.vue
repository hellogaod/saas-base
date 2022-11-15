<template>
  <!--消费金融-角色管理-->
  <section class="page role-manage">
    <data-form :model="roleModel" @onSearch="refreshData" :page="pageUtil">
      <template slot="default-input">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleModel.roleName"></el-input>
        </el-form-item>
      </template>
      <template slot="default-button">
        <el-button @click="dialog.add = true">新增角色</el-button>
      </template>
    </data-form>
    <data-box :data="roleDataSet" :page="pageUtil" @onPageChange="refreshData">
      <template slot="columns">
        <!--数据列区域-->
        <el-table-column prop="roleName" label="角色名称" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="status" label="状态" :min-width="$helper.getColumnWidth(2)">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 1">启用</span>
            <span v-if="scope.row.status === 0">停用</span>
          </template>
        </el-table-column>
        <el-table-column prop="remark" label="备注" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>

        <el-table-column prop="updateTime" label="修改时间" :min-width="$helper.getColumnWidth(4)">
          <template slot-scope="scope">
            <span>{{$dateutils.dateTimeFormat(scope.row.updateTime)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" :min-width="$helper.getColumnWidth(5)">
          <template slot-scope="scope">
            <el-button type="text" @click="enableClick(scope,0)"
                       v-if="scope.row.status === 0">启用
            </el-button>
            <el-button type="text" @click="disableClick(scope,0)"
                       v-if="scope.row.status === 1">停用
            </el-button>
            <el-button type="text"
                       @click="dialog.modify = true, roleObj = scope.row">修改
            </el-button>
            <el-button type="text"
                       @click="dialog.module = true, roleObj = scope.row">权限分配
            </el-button>
          </template>
        </el-table-column>
      </template>
    </data-box>
    <!--新增角色弹出框-->
    <el-dialog title="新增角色" :center="true" :visible.sync="dialog.add" width="500px" @close="$refs['add-role'].reset()">
      <add-role @close="dialog.add = false" @refreshList="refreshData" ref="add-role"></add-role>
    </el-dialog>

    <!--修改角色弹出框-->
    <el-dialog title="修改角色" :center="true" :visible.sync="dialog.modify" width="500px"
               @open="$nextTick(()=>$refs['modify-role'].refresh(roleObj))">
      <modify-role ref="modify-role" @close="dialog.modify = false" @refreshList="refreshData"></modify-role>
    </el-dialog>

    <!--修改权限分配-->
    <el-dialog title="权限分配" :center="true" :visible.sync="dialog.module" width="300px"
               @open="$nextTick(()=>$refs['module-role'].refresh(roleObj))">
      <module-role ref="module-role" @close="dialog.module = false" @refreshList="refreshData"></module-role>
    </el-dialog>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Auth, Layout, Dependencies} from "~/core/decorator";
  import {entRoleService} from "~/server/services/enterprise-manage-services/ent-role.service";
  import {PageUtil} from "~/utils/page.util";
  import DataForm from "~/components/common/data-form.vue";
  import DataBox from "~/components/common/data-box.vue";
  import AddRole from "~/components/enterprise-manage/role-manage/add-role.vue";
  import ModifyRole from "~/components/enterprise-manage/role-manage/modify-role.vue";
  import ModuleRole from "~/components/enterprise-manage/role-manage/module-role.vue";

  @Layout("workspace")
  @Component({
    components: {
      AddRole,
      ModifyRole,
      ModuleRole,
      DataForm,
      DataBox
    }
  })
  export default class RoleManage extends Vue {
    @Dependencies(entRoleService) private entRoleService: entRoleService;
    @Dependencies(PageUtil) private pageUtil: PageUtil;
    private roleModel: any = {
      roleName: ""
    };
    private roleDataSet = null;
    private roleObj: any = {};
    private dialog: any = {
      add: false,
      modify: false,
      module: false
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
        this.sysRoleService.updateStatus({status: '1', roleId: scope.row.roleId}).subscribe(data => {
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
        this.sysRoleService.updateStatus({status: '0', roleId: scope.row.roleId}).subscribe(data => {
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

    mounted() {
      this.refreshData();
    }

    refreshData() {
      this.sysRoleService.getRoleList(this.roleModel, this.pageUtil).subscribe(data => {
        this.roleDataSet = data.list
      }, ({msg}) => {
        this.$message.error(msg);
      })
    }

    deactivated() {
      for (let v in this.dialog) {
        this.dialog[v] = false;
      }
    }
  }
</script>

<style lang="less">
</style>
