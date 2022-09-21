<template>
  <!--消费金融-日志管理 操作日志-->
  <div>
  <section class="page system-operationLog">
    <data-form :model="systemLogModel" @onSearch="refreshData">
      <template slot="default-input">
        <el-form-item label="用户名" prop="loginAccount">
          <el-input v-model="systemLogModel.loginAccount"></el-input>
        </el-form-item>
      </template>
       <template slot="default-input">
        <el-form-item label="方法名" prop="method">
          <el-input v-model="systemLogModel.method"></el-input>
        </el-form-item>
      </template>
       <template slot="default-input">
       <el-form-item label="状态:" prop="status">
        <el-select v-model="systemLogModel.status">
          <el-option label="成功" :value=0></el-option>
          <el-option label="失败" :value=1></el-option>
        </el-select>
        </el-form-item>
      </template>
      <template slot="collapse-input">
       <el-form-item label="操作时间:" prop="promotionTime" class="noticePicker">
         <el-date-picker value-format="yyyy-MM-dd" v-model="systemLogModel.promotionTime" type="daterange" range-separator="至"  start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions2">
    </el-date-picker>
      </el-form-item>
      </template>
    </data-form>
    <data-box :data="systemLogDataSet" :page="pageService" @onPageChange="refreshData">
      <template slot="columns">
        <!--数据列区域-->
        <el-table-column prop="loginAccount" label="用户名" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="method" label="方法名" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="methodArgs" label="参数" :min-width="$helper.getColumnWidth(5)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="methodDesc" label="方法描述" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="exceptionCode" label="异常编码" :min-width="$helper.getColumnWidth(2)" show-overflow-tooltip>
          <template slot-scope="scope">
            <span v-if="scope.row.exceptionCode!= '0'">{{scope.row.exceptionCode}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="exceptionDescription" label="异常描述" :min-width="$helper.getColumnWidth(2)" show-overflow-tooltip>
        </el-table-column>
        <el-table-column label="异常详情"  prop="status" :min-width="$helper.getColumnWidth(2)" show-overflow-tooltip>
           <template slot-scope="scope">
           <el-button v-if="scope.row.status === '1'" type="text" @click="dialog.detalis = true,logObj = scope.row">查看</el-button>
          </template>
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
    <!--详情弹出框-->
    <el-dialog title="日志详情" :center="true" :visible.sync="dialog.detalis" width="800px" @open="$nextTick(()=>$refs['detalis-logs'].refresh(logObj))">
      <system-view ref="detalis-logs" @close="dialog.detalis = false" @refreshList="refreshData"></system-view>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Auth, Layout, Dependencies } from "~/core/decorator";
import { WeblogService } from "~/services/systemweb-service/weblog.service";
import { PageService } from "~/utils/page.service";
import DataForm from "~/components/common/data-form.vue";
import DataBox from "~/components/common/data-box.vue";
import systemView from "~/pages/system-web/system-manage/system-view.vue";
// import { State, Getter } from "vuex-class";

@Layout("workspace")
@Component({
  components: {
    DataForm,
    DataBox,
    systemView
  }
})
export default class GoodsManage extends Vue {
    @Dependencies(WeblogService) private WeblogService: WeblogService;
    @Dependencies(PageService) private pageService: PageService;
    private systemLogModel: any = {
        loginAccount: "",
        method:"",
        status:"",
        promotionTime:"",       
        startTime: '',
        endTime: '',
    };
    private logObj: any = {};
    private dialog: any = {
        detalis: false
    };
    private systemLogDataSet = null;
    mounted() {
        this.refreshData();
    }
    goToPage(path,obj) {
      this.$router.push({name: 'system-view',params: {obj: obj}});
    }
    //时间
    private pickerOptions2:any= {
   
    }
    refreshData() {
      this.systemLogModel.startTime=this.systemLogModel.promotionTime[0];
      this.systemLogModel.endTime=this.systemLogModel.promotionTime[1];
      this.systemLogModel.operateType=1
        this.WeblogService.getLogList(this.systemLogModel, this.pageService,).subscribe(data => {
            this.systemLogDataSet = data.list
        }, ({ msg }) => {
            this.$message.error(msg);
        })
    }
}
</script>

<style lang="less">
.noticePicker .el-input{
  width: 180px !important;
}
</style>
