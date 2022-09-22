<template>
  <section class="component case-repay-chart">
    <el-card>
      <div slot="header" class="card-header row between-span">
        <span>跟催量排名</span>
        <div class="row end-span middle-span">
          <date-select hiddenDate @onDateTypeChange="refreshData" @onDateChange="refreshData" :currentDateType.sync="followModel.timeType"></date-select>
          <div v-show="followRank" class="padding-left">
            我的排名: {{followRank}}
          </div>
        </div>
      </div>
      <data-box :height="300" :data="followDataSet" hiddenIndex>
        <template slot="columns">
          <el-table-column label="排名" width="50px">
            <template slot-scope="scope">
              <span>{{scope.$index+1}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="collectionFollowName" label="姓名" :min-width="$helper.getColumnWidth(3)">
          </el-table-column>
          <el-table-column prop="calledCount" label="外呼数" :min-width="$helper.getColumnWidth(3)">
          </el-table-column>
          <el-table-column prop="followedCount" label="催记数" :min-width="$helper.getColumnWidth(6)">
          </el-table-column>
          <el-table-column prop="followedNumber" label="跟单量" :min-width="$helper.getColumnWidth(4)">
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
export default class FollowRankList extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;
  @Dependencies(PageService) pageService: PageService;

  private followDataSet: any = null;
  private followRank: any = null;
  private followModel = {
    timeType: dashboardTimeType.year
  };

  /**
   * 更新案件还款数据
   */
  refreshData() {
    // this.homePageService
    //   .getCollectedFollowedRank(this.followModel)
    //   .subscribe(({ followCountModels, collectRank }) => {
    //     this.followDataSet = followCountModels;
    //     this.followRank = collectRank;
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
