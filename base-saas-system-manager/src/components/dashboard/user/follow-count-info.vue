
<template>
  <section class="component follow-count-info">
    <el-card>
      <div slot="header" class="card-header row between-span">
        <span>跟催量总览</span>
        <span>单位 : 笔</span>
      </div>
      <data-grid class="data-grid" :labelWidth="0" contentAlign="center">
        <data-grid-item :span="12">
          <el-row class="height-line">
            <el-col :span="12" class="grid-item-label">
              <span class="circle">日</span>
              <span>今日外呼 / 催记数</span>
            </el-col>
            <el-col :span="12">
              {{followCountData.currentDayCalled}}/{{followCountData.currentDayCount}}
            </el-col>
          </el-row>
        </data-grid-item>
        <data-grid-item :span="12">
          <el-row class="height-line">
            <el-col :span="12" class="grid-item-label">
              <span class="circle">周</span>
              <span>本周外呼 / 催记数</span>
            </el-col>
            <el-col :span="12">
              {{followCountData.currentWeekCalled}}/{{followCountData.currentWeekCount}}
            </el-col>
          </el-row>
        </data-grid-item>
        <data-grid-item :span="12">
          <el-row class="height-line">
            <el-col :span="12" class="grid-item-label">
              <span class="circle">月 </span>
              <span>本月外呼 / 催记数</span>
            </el-col>
            <el-col :span="12">
              {{followCountData.currentMonthCalled}}/{{followCountData.currentMonthCount}}
            </el-col>
          </el-row>
        </data-grid-item>
        <!--<data-grid-item :span="6">
          <div class="col">
            <div>当日在线时长（h）</div>
            <div>{{followCountData.onlineTime}}</div>
          </div>
        </data-grid-item>
        <data-grid-item :span="6">
          <div class="col">
            <div>当日离线时长（h）</div>
            <div>{{followCountData.offlineTime}}</div>
          </div>
        </data-grid-item>-->
      </data-grid>
    </el-card>

  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Layout, Dependencies } from "~/core/decorator";
// import { HomePageService } from "~/services/report-service/home-page.service";
import { CommonService } from "~/utils/common.service";
import { DataGrid, DataGridItem } from "@zct1989/vue-component";
@Component({
  components: {
    DataGrid,
    DataGridItem
  }
})
export default class FollowCountInfo extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;

  private followCountData = {
    currentDayCalled: 0,
    currentWeekCalled: 0,
    currentMonthCalled: 0,
    currentDayCount: 0,
    currentWeekCount: 0,
    currentMonthCount: 0,
    onlineTime: 0,
    offlineTime: 0
  };

  /**
   * 更新案件还款数据
   */
  refreshData() {
    // this.homePageService.getFollowCountInfo().subscribe(data => {
    //   this.followCountData = data;
    // });
  }

  mounted() {
    this.refreshData();
  }
}
</script>

<style lang="less" scoped>
.component.follow-count-info {
  .circle {
    display: inline-block;
    border-radius: 100%;
    background-color: #52b7ea;
    width: 18px;
    height: 18px;
    line-height: 18px;
    color: #fff;
    text-align: center;
  }

  .grid-item-label {
    text-align: left;
    padding-left: 40px;
  }
  .height-line {
    line-height: 31.5px;
  }
}
</style>
