<template>
  <section class="component task-box">
    <data-box ref="databox" row-key="id" :data="taskDataSet" :page="pageService" @onPageChange="refreshData">
      <template slot="columns">
        <el-table-column prop="type" label="类型" :min-width="$helper.getColumnWidth(3)">
          <template slot-scope="scope">
            {{scope.row.type | dictConvert}}
          </template>
        </el-table-column>
        <el-table-column prop="taskStatus" label="任务状态" align="center" :min-width="$helper.getColumnWidth(3)">
          <template slot-scope="scope">
            {{scope.row.taskStatus | dictConvert}}
          </template>
        </el-table-column>
        <el-table-column prop="taskDescribe" label="任务描述" align="left" :show-overflow-tooltip="true" :min-width="$helper.getColumnWidth(6)">
        </el-table-column>
        <el-table-column prop="beginDate" label="开始时间" :min-width="$helper.getColumnWidth(5)">
          <template slot-scope="scope">
            {{scope.row.beginDate|dateTimeFormat}}
          </template>
        </el-table-column>
        <el-table-column prop="endDate" label="结束时间" :min-width="$helper.getColumnWidth(5)">
          <template slot-scope="scope">
            {{scope.row.endDate|dateTimeFormat}}
          </template>
        </el-table-column>
        <el-table-column  label="耗时" :min-width="$helper.getColumnWidth(5)">
          <template slot-scope="scope">
            {{getTime(scope.row.endDate, scope.row.beginDate)}}
          </template>
        </el-table-column>
        <el-table-column label="操作" :min-width="$helper.getColumnWidth(3)">
          <template slot-scope="scope">
            <el-button size="small" @click="downLoad(scope.row.remark)" type="text" v-if="scope.row.type === 295 && scope.row.taskStatus === 299">下载</el-button>
          </template>
        </el-table-column>
      </template>
    </data-box>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Dependencies } from "~/core/decorator";
// import { TaskBoxService } from "~/services/business-service/task-box.service";
import { PageService } from "~/utils/page.service";
import { CommonService } from "~/utils/common.service";
import { State, Mutation, Action, namespace } from "vuex-class";
import DataBox from "~/components/common/data-box.vue";
import DataForm from "~/components/common/data-form.vue";
import { messageState } from "~/config/enum.config";
import { Prop,Watch } from "vue-property-decorator";

const workspaceModule = namespace("workspace");

@Component({
  components: {
    DataBox,
    DataForm
  }
})
export default class MessageBox extends Vue {
  // @Dependencies(TaskBoxService) taskBoxService: TaskBoxService;
  @Dependencies(PageService) pageService: PageService;
  @workspaceModule.Action updateUnReadCount;
  private taskDataSet: Array<any> = [];
  private downLoad: any = CommonService.downloadFile;
  private currentMessage: any = "";
  private interval: any;
  @Prop() visible
  @Watch("visible")
  onVisibleChange(value){
    if(value){
      this.refreshData()
    }
  }
   /**
   * 毫秒转时分秒
   */
  getTime(end, start) {
    let hour, minute, second;
    let timestamp = (end - start) / 1000;
    if (timestamp < 60) {
      hour = 0;
      minute = 0;
      second = timestamp;
    } else if (timestamp >= 60 && timestamp < 3600) {
      hour = 0;
      minute = Math.trunc(timestamp / 60);
      second = timestamp % 60;
    } else {
      hour = Math.trunc(timestamp / 3600);
      let oo = timestamp % 3600; // 获取除小时外剩余秒
      if (oo < 60) {
        minute = 0;
        second = Math.trunc(oo);
      } else {
        minute = Math.trunc(oo / 60);
        second = oo % 60;
      }
    }
    return `${hour}时${minute}分${second}秒`;
  }
  /**
   * 刷新数据
   */
  refreshData() {
    // this.taskBoxService
    //   .query(this.pageService)
    //   .subscribe(data => {
    //     this.taskDataSet = data;
    //   });
  }
  /**
   * 清除定时器
   */
  resetInterval() {
    window.clearInterval(this.interval);
  }
  start() {
    this.interval = setInterval(() => {
      this.taskDataSet.forEach(x => {
        if (!x.endDate) {
          x.flag = true;
        }
        if (x.flag) {
          x.endDate = Date.now();
        }
      });
    }, 5000);
  }
  /**
   * 初始化
   */
  mounted() {
    this.refreshData()
    this.start()
  }
}
</script>

<style lang="less">
.task-box.component {
  .data-box {
    .el-table__row {
      &.unread {
        color: #33aacc !important;
      }
      &.read {
        color: gray !important;
      }
    }
  }
}
</style>
