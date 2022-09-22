<template>
  <!--消费金融-日志管理 登录日志-->
  <section class="page system-log">
    <data-form :model="systemLogModel" @onSearch="refreshData">
      <template slot="default-input">
        <el-form-item label="登录账号" prop="loginAccount">
          <el-input v-model="systemLogModel.loginAccount"></el-input>
        </el-form-item>
      </template>
      <template slot="default-input">
       <el-form-item label="终端:" prop="terminalType">
        <el-select v-model="systemLogModel.terminalType">
          <el-option label="PC端" :value=0></el-option>
          <el-option label="安卓" :value=1></el-option>
          <el-option label="ios" :value=2></el-option>
        </el-select>
        </el-form-item>
      </template>
      <template slot="default-input">
       <el-form-item label="登录状态:" prop="status">
        <el-select v-model="systemLogModel.status">
          <el-option label="成功" :value=0></el-option>
          <el-option label="失败" :value=1></el-option>
        </el-select>
        </el-form-item>
      </template>
    </data-form>
    <data-box :data="systemLogDataSet" :page="pageService" @onPageChange="refreshData">
      <template slot="columns">
        <!--数据列区域-->
        <el-table-column prop="loginAccount" label="登录账号" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="terminalType" label="终端" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
           <template slot-scope="scope">
            <span v-if="scope.row.terminalType === '0'">PC端</span>
            <span v-if="scope.row.terminalType === '1'">安卓</span>
            <span v-if="scope.row.terminalType === '2'">ios</span>
          </template>
        </el-table-column>
        <el-table-column prop="operateIp" label="IP地址" :min-width="$helper.getColumnWidth(2)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="status" label="状态" :min-width="$helper.getColumnWidth(2)" show-overflow-tooltip>
           <template slot-scope="scope">
            <span v-if="scope.row.status === '0'">成功</span>
            <span v-if="scope.row.status === '1'">失败</span>
          </template>
        </el-table-column>
        <el-table-column prop="operateTime" label="登录时间" :min-width="$helper.getColumnWidth(2)" show-overflow-tooltip>
            <template slot-scope="scope">
            <span>{{scope.row.operateTime|dateFormat}}</span>
          </template>
        </el-table-column>
      </template>
    </data-box>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Auth, Layout, Dependencies } from "~/core/decorator";
import { WeblogService } from "~/services/systemweb-services/weblog.service";
import { PageService } from "~/utils/page.service";
import DataForm from "~/components/common/data-form.vue";
import DataBox from "~/components/common/data-box.vue";
import { State, Getter } from "vuex-class";

@Layout("workspace")
@Component({
  components: {
    DataForm,
    DataBox,
  }
})
export default class GoodsManage extends Vue {
    @Dependencies(WeblogService) private WeblogService: WeblogService;
    @Dependencies(PageService) private pageService: PageService;
    private systemLogModel: any = {
        loginAccount: "",
        terminalType:"",
        status:""
    };
    private systemLogDataSet = null
    mounted() {
        this.refreshData();
    }
    refreshData() {
       this.systemLogModel.operateType=0
        this.WeblogService.getLogList(this.systemLogModel, this.pageService).subscribe(data => {
            this.systemLogDataSet = data.list
        }, ({ msg }) => {
            this.$message.error(msg);
        })
    }
}
</script>

<style lang="less">
</style>
