<template>
  <!--消费金融-组织架构管理-->
  <section class="page organzation-manage">
    <data-form :model="organizationModel" :hiddenSearch=true>
      <template slot="default-button">
        <el-button @click="dialog.add = true">添加</el-button>
      </template>
    </data-form>
    <tree-grid :columns="columns" :tree-structure="true" :data-source="dataSource"
               @handleOperation="handleOperation"></tree-grid>
    <!--新增组织弹出框-->
    <el-dialog title="添加" :center="true" :visible.sync="dialog.add" width="500px"
               @close="$refs['add-organization'].reset()" @open="$nextTick(()=>$refs['add-organization'].refresh())">
      <add-organization @close="dialog.add = false" ref="add-organization"
                        @refreshList="refreshData"></add-organization>
    </el-dialog>
    <!--修改组织弹出框-->
    <el-dialog title="修改" :center="true" :visible.sync="dialog.modify" width="500px"
               @close="$refs['modify-organization'].reset()"
               @open="$nextTick(()=>$refs['modify-organization'].refresh(organizationObj, dataSource))">
      <modify-organization @close="dialog.modify = false" ref="modify-organization"
                           @refreshList="refreshData"></modify-organization>
    </el-dialog>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Auth, Layout, Dependencies} from "~/core/decorator";
  import DataForm from "~/components/common/data-form.vue";
  import TreeGrid from "~/components/common/tree-grid.vue";
  import AddOrganization from "~/components/enterprise-manage/organization-manage/add-organization.vue";
  import ModifyOrganization from "~/components/enterprise-manage/organization-manage/modify-organization.vue";
  import {webOrganizationService} from "~/server/services/enterprise-manage-services/organization.service";

  @Layout("workspace")
  @Component({
    components: {
      DataForm,
      TreeGrid,
      AddOrganization,
      ModifyOrganization
    }
  })
  export default class OrganizationManage extends Vue {
    @Dependencies(webOrganizationService) private webOrganizationService: webOrganizationService;

    private organizationDataSet = null;
    private organizationModel = {};
    private organizationObj: any = {};
    private dialog: any = {
      add: false,
      modify: false
    };

    private columns: Array<any> = [
      {
        text: '名称',
        dataIndex: 'orgName'
      },
      {
        text: '联系人',
        dataIndex: 'orgManager'
      },
      {
        text: '电话',
        dataIndex: 'orgTel'
      },
      {
        text: '固定电话',
        dataIndex: 'orgShortTel'
      },
      {
        text: '类型',
        dataIndex: 'orgType'
      },
      {
        text: '状态',
        dataIndex: 'status'
      },
      {
        text: '操作',
        dataIndex: 'operation',

      }
    ]

    private dataSource: Array<any> = []

    refresh(formName) {
      this.$nextTick(() => {
        let form: any = this.$refs[formName];
        form.refresh();
      });
    }

    deactivated() {
      for (let v in this.dialog) {
        this.dialog[v] = false;
      }
    }

    mounted() {
      this.refreshData()
    }

    /**
     * 页面加载
     */
    refreshData() {
      this.webOrganizationService.getOrgList().subscribe(data => {
        this.dataSource = data
      }, ({msg}) => {
        this.$message.error(msg);
      })
    }

    /**
     * 接收treeGrid事件
     */
    handleOperation(obj, index) {
      if (index === 0) {// 修改
        this.edit(obj);
      } else if (index === 1) {//启用/停用
        this.updateOrgStatus(obj);
      }
    }

    /**
     * 修改
     */
    edit(obj) {
      console.log(obj);
      this.organizationObj = obj;
      this.dialog.modify = true;
    }

    /**
     * 启用/停用
     */
    updateOrgStatus(obj) {
      let msg = obj.status == '1' ? '停用' : '启用';
      status = obj.status == '1' ? '0' : '1';
      let params = {
        orgId: obj.orgId,
        status: status
      }
      this.$confirm(`确定${msg}吗？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        this.webOrganizationService.updateOrgStatus(params).subscribe(data => {
          this.refreshData();
          this.$message({
            type: 'success',
            message: `${msg}成功`
          })
        }, ({msg}) => {
          this.$message.error(msg);
        })

      }).catch(() => {

      })
    }
  }
</script>

<style scoped>
  .organzation-manage {
    max-height: 400px;
    overflow: auto;
  }
</style>
