<template>
  <!--消费金融-字典管理-->
  <section class="page dict-manage">
    <data-form :model="dictModel" @onSearch="refreshData">
      <template slot="default-input">
        <el-form-item label="字典名称" prop="itemName">
          <el-input v-model="dictModel.itemName"></el-input>
        </el-form-item>
        <el-form-item label="字典编码" prop="itemCode">
          <el-input v-model="dictModel.itemCode"></el-input>
        </el-form-item>
      </template>
      <template slot="default-button">
        <el-button @click="dialog.addLarge = true" v-auth="'3d30a34a-ae8e-4bbe-9e9b-9f91774326bd'">添加大类</el-button>
      </template>
    </data-form>
    <data-box :data="dictDataSet" :page="pageService" @onPageChange="refreshData" ref="data-box" @onRowClick="onRowClick">
      <template slot="columns">
        <!--数据列区域-->
        <el-table-column prop="itemCode" label="字典编码" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="itemName" label="字典名称" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="remark" label="备注" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="status" label="状态" :min-width="$helper.getColumnWidth(2)">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 1">启用</span>
            <span v-if="scope.row.status === 0">停用</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="最后更新时间" :min-width="$helper.getColumnWidth(4)">
          <template slot-scope="scope">
            <span>{{scope.row.updateTime|dateTimeFormat}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" :min-width="$helper.getColumnWidth(7)">
          <template slot-scope="scope">
            <el-button v-auth="'098e3024-60ea-47cb-8f16-3bfe63799bda'" type="text" @click="enableClick(scope,0)" v-if="scope.row.status === 0">启用</el-button>
            <el-button v-auth="'098e3024-60ea-47cb-8f16-3bfe63799bda'" type="text" @click="disableClick(scope,0)" v-if="scope.row.status === 1">停用</el-button>
            <el-button v-auth="'e0a6ede2-0256-4033-a322-6bdc92c76243'" type="text" @click="dialog.modifyLarge = true, dictObj = scope.row">修改</el-button>
            <el-button v-auth="'7d4073ef-b434-4aaf-8b2f-1683d102cc26'" type="text" @click="dialog.addDetailed = true, dictObj = scope.row">添加字典明细</el-button>
          </template>
        </el-table-column>
      </template>
    </data-box>
    <data-box v-show="dictDetailDataSet" :data="dictDetailDataSet" :page="pageDetailService" @onPageChange="refreshDetailData">
      <template slot="columns">
        <!--数据列区域-->
        <el-table-column prop="detailCode" label="字典编码" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="detailName" label="字典名称" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="remark" label="备注" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="status" label="状态" :min-width="$helper.getColumnWidth(2)">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 1">启用</span>
            <span v-if="scope.row.status === 0">停用</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="最后更新时间" :min-width="$helper.getColumnWidth(4)">
          <template slot-scope="scope">
            <span>{{scope.row.updateTime|dateTimeFormat}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" :min-width="$helper.getColumnWidth(7)">
          <template slot-scope="scope">
            <el-button v-auth="'098e3024-60ea-47cb-8f16-3bfe63799bda'" type="text" @click="enableClick(scope,1)" v-if="scope.row.status === 0">启用</el-button>
            <el-button v-auth="'098e3024-60ea-47cb-8f16-3bfe63799bda'" type="text" @click="disableClick(scope,1)" v-if="scope.row.status === 1">停用</el-button>
            <el-button v-auth="'e0a6ede2-0256-4033-a322-6bdc92c76243'" type="text" @click="dialog.modifyDetailed = true, dictDetailObj = scope.row">修改</el-button>
          </template>
        </el-table-column>
      </template>
    </data-box>
    <!--新增字典大类-->
    <el-dialog title="新增字典大类" :center="true" :visible.sync="dialog.addLarge" width="500px" @close="$refs['add-addLarge'].reset()">
      <add-dict @close="dialog.addLarge = false" ref="add-addLarge" @refreshList="refreshData"></add-dict>
    </el-dialog>

    <!--修改字典大类-->
    <el-dialog title="修改字典大类" :center="true" :visible.sync="dialog.modifyLarge" width="500px" @close="$refs['modify-modifyLarge'].reset()" @open="$nextTick(()=>$refs['modify-modifyLarge'].refresh(dictObj))">
      <modify-dict ref="modify-modifyLarge" @close="dialog.modifyLarge = false" @refreshList="refreshData"></modify-dict>
    </el-dialog>

    <!--修改字典明细-->
    <el-dialog title="修改字典明细" :center="true" :visible.sync="dialog.modifyDetailed" width="500px" @close="$refs['modify-modifyDetailed'].reset()" @open="$nextTick(()=>$refs['modify-modifyDetailed'].refresh(dictDetailObj))">
      <modify-dictDetail ref="modify-modifyDetailed" @close="dialog.modifyDetailed = false" @refreshList="refreshDetailData"></modify-dictDetail>
    </el-dialog>

    <!--添加字典明细-->
    <el-dialog title="添加字典明细" :center="true" :visible.sync="dialog.addDetailed" width="500px" @close="$refs['addDetailed-dict'].reset()" @open="$nextTick(()=>$refs['addDetailed-dict'].refresh(dictObj))">
      <add-dictDetail ref="addDetailed-dict" @close="dialog.addDetailed = false" @refreshList="refreshData"></add-dictDetail>
    </el-dialog>

  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import { Auth, Layout, Dependencies } from "~/core/decorator";
  import { webDictService } from "~/services/systemweb-service/dict.service";
  import { PageService } from "~/utils/page.service";
  import DataForm from "~/components/common/data-form.vue";
  import DataBox from "~/components/common/data-box.vue";
  import AddDict from "~/components/system-web/system-manage/dict-manage/add-dict.vue";
  import ModifyDict from "~/components/system-web/system-manage/dict-manage/modify-dict.vue";
  import AddDictDetail from "~/components/system-web/system-manage/dict-manage/add-dictDetail.vue";
  import ModifyDictDetail from "~/components/system-web/system-manage/dict-manage/modify-dictDetail.vue";

  @Layout('workspace')
  @Component({
    components: {
      AddDict,
      ModifyDict,
      AddDictDetail,
      ModifyDictDetail,
      DataForm,
      DataBox
    }
  })
  export default class BussinessManage extends Vue {
    @Dependencies(webDictService) private webDictService: webDictService;
    @Dependencies(PageService) private pageService: PageService;
    @Dependencies(PageService) private pageDetailService: PageService;
    private dictModel: any = {
      itemName: '',
      itemCode: ''
    };
    private dictDetailModel: any = {
      itemCode: ''
    };
    private dictDataSet = null;
    private dictDetailDataSet = null;
    private dictObj: any = {};
    private dictDetailObj: any = {};
    private dialog: any = {
      addLarge: false,
      modifyLarge: false,
      modifyDetailed: false,
      addDetailed: false
    };
    /**
     * 启用
     */
    enableClick(scope,type) {
      this.$confirm('确认启用吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if(type===0){
          this.webDictService.updateDictItemStatus({ status: '1',id: scope.row.id }).subscribe(data => {
            this.$message({
              type: 'success',
              message: '启用成功!'
            })
            this.pageService.reset()
            this.refreshData()
          }, ({ msg }) => {
            this.$message.error(msg);
          })
        }else{
          this.webDictService.updateDictDetailStatus({ status: '1',id: scope.row.id }).subscribe(data => {
            this.$message({
              type: 'success',
              message: '启用成功!'
            })
            this.pageDetailService.reset()
            this.refreshDetailData()
          }, ({ msg }) => {
            this.$message.error(msg);
          })
        }
      }).catch(() => {})
    }
    /**
     * 停用
     */
    disableClick(scope,type) {
      this.$confirm('确认停用吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if(type===0){
          this.webDictService.updateDictItemStatus({ status: '0', id: scope.row.id }).subscribe(data => {
            this.$message({
              type: 'success',
              message: '停用成功!'
            })
            this.pageService.reset()
            this.refreshData()
          }, ({ msg }) => {
            this.$message.error(msg);
          })
        }else{
          this.webDictService.updateDictDetailStatus({ status: '0', id: scope.row.id }).subscribe(data => {
            this.$message({
              type: 'success',
              message: '停用成功!'
            })
            this.pageDetailService.reset()
            this.refreshDetailData()
          }, ({ msg }) => {
            this.$message.error(msg);
          })
        }
      }).catch(() => {})
    }
    mounted() {
      this.refreshData()
    }
    refreshData() {
      this.pageService.layout = "total,  prev, pager, next";
      this.pageService.pageSize = 6;
      this.webDictService.getDictItemList(this.dictModel, this.pageService).subscribe(data => {
        this.dictDataSet = data.list
        this.dictDetailDataSet = null
      }, ({ msg }) => {
        this.$message.error(msg);
      })
    }
    refreshDetailData() {
      this.pageDetailService.layout = "total,  prev, pager, next";
      this.pageDetailService.pageSize = 6;
      this.webDictService.getDictDetailList(this.dictDetailModel,this.pageDetailService).subscribe(data => {
        this.dictDetailDataSet = data.list
      }, ({ msg }) => {
        this.$message.error(msg);
      })
    }
    deactivated() {
      for (let v in this.dialog) {
        this.dialog[v] = false;
      }
    }
    onRowClick(row) {
      this.dictDetailModel.itemCode=row.itemCode
      this.refreshDetailData()
    }
  }
</script>

<style lang="less">

</style>
