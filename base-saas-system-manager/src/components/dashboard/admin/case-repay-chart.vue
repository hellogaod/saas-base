<template>
  <section class="component case-repay-chart">
    <el-card class="box-card">
      <div slot="header" class="card-header row between-span">
        <span>案件还款数据</span>
        <div class="row end-span middle-span">
          <date-select @onDateTypeChange="updateCaseAmtAndCount" @onDateChange="updateCaseAmtAndCount" :currentDateType.sync="caseRepayModel.timeType" :currentDate.sync="caseRepayModel.date"></date-select>
        </div>
      </div>
       <ve-histogram height="350px" legend-position="bottom" :data="caseRepayChart.data" :settings="caseRepayChart.setting"></ve-histogram>
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
import { dashboardQueryType, dashboardTimeType } from "~/config/enum.config";
// import { HomePageService } from "~/services/report-service/home-page.service";
import { CommonService } from "~/utils/common.service";

@Component({
  components: {
    VeHistogram,
    DateSelect,
    CaseTypeSelect
  }
})
export default class CaseRepayChart extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;

  private caseRepayModel = {
    timeType: dashboardTimeType.year,
    queryType: [dashboardQueryType.inner, dashboardQueryType.outer],
    date: new Date()
  };

  private caseRepayChart = {
    data: {
      columns: ["date", "hadAmount", "applyAmount", "hadCount", "applyCount"],
      rows: new Array()
    },
    setting: {
      showLine: ["hadAmount","applyAmount"],
      yAxisName: ['金额(元)', '数量(笔)'],
      axisSite: {right:['hadCount','applyCount']},
      labelMap: {
        hadAmount: "已还款案件金额",
        applyAmount: "还款审核中案件金额",
        hadCount: "已还款案件数量",
        applyCount: "还款审核中案件数量"
      }
    }
  };

  /**
   * 更新案件还款数据
   */
  updateCaseAmtAndCount() {
    // this.homePageService
    //   .getCaseAmtAndCount(this.caseRepayModel)
    //   .subscribe(
    //     ({
    //       hadAmountList,
    //       hadCountList,
    //       applyAmountList,
    //       applyCountList,
    //       hadTotalCaseAmount,
    //       hadTotalCaseCount,
    //       applyTotalCaseAmount,
    //       applyTotalCaseCount
    //     }) => {
    //       // 数据集合
    //       let rows: Array<any> = [];
    //       // 设置x轴维度数据
    //       let axis = CommonService.getDateList(
    //         this.caseRepayModel.date,
    //         this.caseRepayModel.timeType
    //       );
    //
    //       // 生成图标诗句
    //       axis.forEach((item, index) => {
    //         rows.push({
    //           date: item,
    //           hadAmount: hadAmountList[index],
    //           hadCount: hadCountList[index],
    //           applyAmount: applyAmountList[index],
    //           applyCount: applyCountList[index]
    //         });
    //       });
    //
    //       this.caseRepayChart.setting.labelMap = {
    //         hadAmount: `已还款案件金额 (总计: ${hadTotalCaseAmount} 元)`,
    //         hadCount: `已还款案件数量 (总计: ${hadTotalCaseCount} 笔)`,
    //         applyAmount: `还款审核中案件金额 (总计: ${applyTotalCaseAmount} 元)`,
    //         applyCount: `还款审核中案件数量 (总计: ${applyTotalCaseCount} 笔)`
    //       };
    //
    //       this.caseRepayChart.data.rows = rows;
    //     }
    //   );
  }

  mounted() {
    this.updateCaseAmtAndCount();
  }
}
</script>

<style lang="less" scoped>

</style>
