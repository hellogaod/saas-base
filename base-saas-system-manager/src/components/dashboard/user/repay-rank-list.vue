<template>
  <section class="component case-repay-chart">
    <el-card>
      <div slot="header" class="card-header row between-span">
        <span>回款金额排名</span>
        <div class="row end-span middle-span">
          <date-select hiddenDate @onDateTypeChange="refreshData" @onDateChange="refreshData" :currentDateType.sync="repayCaseModel.timeType"></date-select>
          <div v-show="repayRank" class="padding-left">
            我的排名: {{repayRank}}
          </div>
        </div>
      </div>
      <data-box :height="300" :data="repayDataSet" hiddenIndex>
        <template slot="columns">
          <el-table-column label="排名" width="50px">
            <template slot-scope="scope">
              <span>{{scope.$index+1}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="collectionName" label="姓名" :min-width="$helper.getColumnWidth(3)">
          </el-table-column>
          <el-table-column prop="backRate" label="回款率" :min-width="$helper.getColumnWidth(3)">
            <template slot-scope="scope">
              <span>{{(scope.row.backRate*100).toFixed(2)+'%'}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="backMoney" label="回款案件金额(元)" align="right" :min-width="$helper.getColumnWidth(6)">
          </el-table-column>
          <el-table-column prop="backCount" label="回款户数" :min-width="$helper.getColumnWidth(4)">
          </el-table-column>
        </template>
      </data-box>
    </el-card>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Layout, Dependencies } from "~/core/decorator";
import { VeHistogram } from "v-charts";
import CaseTypeSelect from "~/components/dashboard/case-type-select.vue";
import DateSelect from "~/components/dashboard/date-select.vue";
import {  dashboardQueryType,  dashboardTimeType,  dashboardRankType} from "~/config/enum.config";
// import { HomePageService } from "~/services/report-service/home-page.service";
// import { CommonService } from "~/utils/common.service";
import DataBox from "~/components/common/data-box.vue";
import { PageService } from "~/utils/page.service";
import RankTypeSelect from "~/components/dashboard/rank-type-select.vue";

@Component({
  components: {
    VeHistogram,
    DateSelect,
    CaseTypeSelect,
    DataBox,
    RankTypeSelect
  }
})
export default class RepayRankList extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;
  @Dependencies(PageService) pageService: PageService;

  private repayDataSet: any = null;
  private repayRank: any = null;
  private repayCaseModel = {
    timeType: dashboardTimeType.year
  };

  /**
   * 更新案件还款数据
   */
  refreshData() {
    // this.homePageService
    //   .getCollectedCaseBackRank(this.repayCaseModel)
    //   .subscribe(({ backAmtModels, collectRank }) => {
    //     this.repayDataSet = backAmtModels;
    //     this.repayRank = collectRank;
    //   });
  }

  mounted() {
    this.refreshData();
  }
}
</script>

<style lang="less" scoped>
.padding-left {
  padding-left: 10px;
}
</style>
