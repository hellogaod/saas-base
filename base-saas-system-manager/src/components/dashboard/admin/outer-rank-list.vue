<template>
  <section class="component outer-rank-list">
    <el-card class="box-card">
      <div slot="header" class="card-header row between-span">
        <span>委外方回款排行榜</span>
        <div class="row end-span middle-span">
          <rank-type-select @onRankTypeChange="refreshData" v-model="outerCaseModel.rankType"></rank-type-select>
        </div>
      </div>
      <data-box :height="300" :data="outerDataSet" :page="pageService" hiddenIndex>
        <template slot="columns">
          <el-table-column label="排名" width="50px">
            <template slot-scope="scope">
              <span>{{(scope.$index+1)+pageService.pageSize*(pageService.pageIndex-1)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="姓名" :min-width="$helper.getColumnWidth(3)">
          </el-table-column>
          <el-table-column prop="amount" label="案件总金额(元)" align="right" :min-width="$helper.getColumnWidth(5)">
          </el-table-column>
          <el-table-column prop="count" label="案件总数" :min-width="$helper.getColumnWidth(4)">
          </el-table-column>
          <el-table-column prop="payAmount" label="回款案件金额(元)" align="right" :min-width="$helper.getColumnWidth(6)">
          </el-table-column>
          <el-table-column prop="payCount" label="回款案件数" :min-width="$helper.getColumnWidth(4)">
          </el-table-column>
          <el-table-column prop="payRate" label="回款率" :min-width="$helper.getColumnWidth(3)">
            <template slot-scope="scope">
              <span>{{(scope.row.payRate*100).toFixed(2)+'%'}}</span>
            </template>
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
import { CommonService } from "~/utils/common.service";
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
export default class OuterRankList extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;
  @Dependencies(PageService) pageService: PageService;

  private outerDataSet: any = null;

  private outerCaseModel = {
    timeType: dashboardTimeType.year,
    rankType: dashboardRankType.amount
  };

  /**
   * 更新案件还款数据
   */
  refreshData() {
    // this.homePageService
    //   .getOutsourceRanking(this.outerCaseModel, this.pageService)
    //   .subscribe(data => {
    //     this.outerDataSet = data;
    //   });
  }

  mounted() {
    this.refreshData();
  }
}
</script>

<style lang="less" scoped>

</style>
