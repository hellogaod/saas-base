<template>
  <!--消费金融-企业管理-->
  <section class="page business-manage">
    <data-form :model="businessModel" @onSearch="refreshData">
      <template slot="default-input">
        <el-form-item label="企业名称" prop="companyName">
          <el-input v-model="businessModel.companyName"></el-input>
        </el-form-item>
      </template>
      <template slot="default-button">
        <el-button @click="dialog.add = true">新增企业</el-button>
      </template>
    </data-form>
    <data-box :data="businessDataSet" :page="pageUtil" @onPageChange="refreshData" ref="data-box">
      <template slot="columns">
        <!--数据列区域-->
        <el-table-column prop="companyCode" label="企业编号" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="companyName" label="企业名称" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="shortName" label="企业简称" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="tel" label="联系电话" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="companyManager" label="联系人" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="wechatNo" label="企业微信号" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="status" label="状态" :min-width="$helper.getColumnWidth(2)">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 1">启用</span>
            <span v-if="scope.row.status === 0">停用</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="修改时间" :min-width="$helper.getColumnWidth(4)">
          <template slot-scope="scope">
            <span>{{$dateutils.dateTimeFormat(scope.row.updateTime)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" :min-width="$helper.getColumnWidth(7)">
          <template slot-scope="scope">
            <el-button type="text" @click="enableClick(scope)" v-if="scope.row.status === 0">启用</el-button>
            <el-button type="text" @click="disableClick(scope)" v-if="scope.row.status === 1">停用</el-button>
            <el-button type="text" @click="dialog.modify = true, bussinessObj = scope.row">修改</el-button>
            <el-button type="text" @click="dialog.config = true, bussinessObj = scope.row">配置三方信息</el-button>
          </template>
        </el-table-column>
      </template>
    </data-box>
    <!--新增企业弹出框-->
    <el-dialog title="新增企业" :center="true" :visible.sync="dialog.add" width="500px"
               @close="$refs['add-business'].reset()">
      <add-business @close="dialog.add = false" ref="add-business" @refreshList="refreshData"></add-business>
    </el-dialog>

    <!--修改企业弹出框-->
    <el-dialog title="修改企业" :center="true" :visible.sync="dialog.modify" width="500px"
               @close="$refs['modify-business'].reset()"
               @open="$nextTick(()=>$refs['modify-business'].refresh(bussinessObj))">
      <modify-business ref="modify-business" @close="dialog.modify = false"
                       @refreshList="refreshData"></modify-business>
    </el-dialog>

    <!--配置三方信息弹出框-->
    <el-dialog title="配置三方信息" :center="true" :visible.sync="dialog.config" width="500px"
               @close="$refs['config-business'].reset()"
               @open="$nextTick(()=>$refs['config-business'].refresh(bussinessObj))">
      <config-business ref="config-business" @close="dialog.config = false"
                       @refreshList="refreshData"></config-business>
    </el-dialog>

  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Auth, Layout, Dependencies} from "~/core/decorator";
  import {EnterpiseService} from "~/server/services/system-manage-services/enterprise.service";
  import {PageUtil} from "~/utils/page.util";
  import DataForm from "~/components/common/data-form.vue";
  import DataBox from "~/components/common/data-box.vue";
  import AddBusiness from "~/components/system-manage/business-manage/add-business.vue";
  import ModifyBusiness from "~/components/system-manage/business-manage/modify-business.vue";
  import ConfigBusiness from "~/components/system-manage/business-manage/config-business.vue";

  @Layout('workspace')
  @Component({
    components: {
      AddBusiness,
      ModifyBusiness,
      ConfigBusiness,
      DataForm,
      DataBox
    }
  })
  export default class BussinessManage extends Vue {
    @Dependencies(EnterpiseService) private enterpiseService: EnterpiseService;
    @Dependencies(PageUtil) private pageUtil: PageUtil;
    private businessModel: any = {
      companyName: ''
    };
    private businessDataSet = null;
    private businessObj: any = {};
    private dialog: any = {
      add: false,
      modify: false,
      config: false
    };

    /**
     * 启用
     */
    enableClick(scope) {
      this.$confirm('确认启用吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.enterpiseService.updateStatus({status: '1', companyCode: scope.row.companyCode}).subscribe(data => {
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
    disableClick(scope) {
      this.$confirm('确认停用吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.enterpiseService.updateStatus({status: '0', companyCode: scope.row.companyCode}).subscribe(data => {
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
      this.refreshData()
    }

    refreshData() {
      this.enterpiseService.getSysEnterpriseList(this.businessModel, this.pageUtil).subscribe(data => {
        this.businessDataSet = data.list
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
