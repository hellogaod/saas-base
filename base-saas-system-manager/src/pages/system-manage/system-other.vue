<template>
  <!--消费金融-参数管理-->
  <section class="page system-other">
    <data-form :model="otherModel" @onSearch="refreshData">
      <template slot="default-input">
        <el-form-item label="三方名称" prop="otherName">
          <el-input v-model="otherModel.otherName"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="otherModel.status" placeholder="请选择" clearable>
            <el-option label="启用" :value=1></el-option>
            <el-option label="停用" :value=0></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="otherModel.type" placeholder="请选择" clearable>
            <el-option label="基础配置" :value=0></el-option>
            <el-option label="三方配置" :value=1></el-option>
          </el-select>
        </el-form-item>
      </template>
      <template slot="default-button">
        <el-button @click="dialog.add = true">添加</el-button>
      </template>
    </data-form>
    <data-box :data="otherDataSet" :page="pageUtil" @onPageChange="refreshData" ref="data-box">
      <template slot="columns">
        <!--数据列区域-->
        <el-table-column prop="otherName" label="三方名称" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="type" label="类型" :min-width="$helper.getColumnWidth(2)" show-overflow-tooltip>
          <template slot-scope="scope">
            <span v-if="scope.row.type === 1">三方配置</span>
            <span v-if="scope.row.type === 0">基础配置</span>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" :min-width="$helper.getColumnWidth(2)">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 1">启用</span>
            <span v-if="scope.row.status === 0">停用</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" :min-width="$helper.getColumnWidth(2)">
          <template slot-scope="scope">
            <el-button type="text" @click="enableClick(scope)" v-if="scope.row.status === 0">启用</el-button>
            <el-button type="text" @click="disableClick(scope)" v-if="scope.row.status === 1">停用</el-button>
            <el-button type="text" @click="dialog.modify = true, otherObj = scope.row">修改</el-button>
          </template>
        </el-table-column>
      </template>
    </data-box>
    <!--新增参数维护-->
    <el-dialog title="新增参数" :center="true" :visible.sync="dialog.add" width="1000px"
               @close="$refs['add-other'].reset()">
      <add-other @close="dialog.add = false" ref="add-other" @refreshList="refreshData"></add-other>
    </el-dialog>

    <!--修改参数-->
    <el-dialog title="修改参数" :center="true" :visible.sync="dialog.modify" width="1000px"
               @close="$refs['modify-other'].reset()" @open="$nextTick(()=>$refs['modify-other'].refresh(otherObj))">
      <modify-other ref="modify-other" @close="dialog.modify = false" @refreshList="refreshData"></modify-other>
    </el-dialog>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Auth, Layout, Dependencies} from "~/core/decorator";
  import {OtherService} from "~/server/services/system-manage-services/other.service";
  import {PageUtil} from "~/utils/page.util";
  import DataForm from "~/components/common/data-form.vue";
  import DataBox from "~/components/common/data-box.vue";
  import AddOther from "~/components/system-manage/other-manage/add-other.vue";
  import ModifyOther from "~/components/system-manage/other-manage/modify-other.vue";

  @Layout('workspace')
  @Component({
    components: {
      AddOther,
      ModifyOther,
      DataForm,
      DataBox
    }
  })
  export default class OtherManage extends Vue {
    @Dependencies(OtherService) private otherService: OtherService;
    @Dependencies(PageUtil) private pageUtil: PageUtil;
    private otherModel: any = {
      otherName: '',
      status: '',
      type: ''
    };
    private otherDataSet = null;
    private otherObj: any = {};
    private dialog: any = {
      add: false,
      modify: false
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
        this.otherService.updateStatus({status: '1', id: scope.row.id}).subscribe(data => {
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
        this.otherService.updateStatus({status: '0', id: scope.row.id}).subscribe(data => {
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
      this.otherService.getOtherList(this.otherModel, this.pageUtil).subscribe(data => {
        this.otherDataSet = data.list
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
