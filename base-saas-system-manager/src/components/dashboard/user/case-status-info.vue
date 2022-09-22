<template>
  <section class="component case-status-info">
    <el-card>
      <div slot="header" class="card-header row between-span">
        <span>案件状态总览</span>
        <span>单位 : 笔</span>
      </div>
      <data-grid class="data-grid" :labelWidth="0" contentAlign="center">
        <data-grid-item :span="12">
          <el-row>
            <el-col :span="12" class="grid-item-label">
              <span class="circle">未</span>
              <span>未催收案件数</span>
            </el-col>
            <el-col :span="12">
              {{caseStatusData.toFollowCaseCount}}
            </el-col>
          </el-row>
        </data-grid-item>
        <data-grid-item :span="12">
          <el-row>
            <el-col :span="12" class="grid-item-label">
              <span class="circle">催</span>
              <span>催收中案件数</span>
            </el-col>
            <el-col :span="12">
              {{caseStatusData.followingCaseCount}}
            </el-col>
          </el-row>
        </data-grid-item>
        <data-grid-item :span="12">
          <el-row>
            <el-col :span="12" class="grid-item-label">
              <span class="circle">承</span>
              <span>承诺还款案件数</span>
            </el-col>
            <el-col :span="12">
              {{caseStatusData.commitmentBackCaseCount}}
            </el-col>
          </el-row>
        </data-grid-item>
        <data-grid-item :span="4">
          <div class="col">
            <div>今日结清</div>
            <div>{{caseStatusData.finishCaseToday}}</div>
          </div>
        </data-grid-item>
        <data-grid-item :span="4">
          <div class="col">
            <div>今日流入</div>
            <div>{{caseStatusData.flowInCaseToday}}</div>
          </div>
        </data-grid-item>
        <data-grid-item :span="4">
          <div class="col">
            <div>今日流出</div>
            <div>{{caseStatusData.flowOutCaseToday}}</div>
          </div>
        </data-grid-item>
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
export default class CaseStatusInfo extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;

  private caseStatusData: any = {
    toFollowCaseCount: 0,
    followingCaseCount: 0,
    commitmentBackCaseCount: 0,
    flowInCaseToday: 0,
    finishCaseToday: 0,
    flowOutCaseToday: 0
  };

  /**
   * 更新案件还款数据
   */
  refreshData() {
    // this.homePageService.getCaseStatusInfo().subscribe(data => {
    //   this.caseStatusData = data;
    // });
  }

  mounted() {
    this.refreshData();
  }
}
</script>

<style lang="less" scoped>
.component.case-status-info {
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
}
</style>
