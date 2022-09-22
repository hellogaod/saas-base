<template>
  <section class="component case-record-chart">
    <el-card class="box-card">
      <div slot="header" class="card-header row between-span">
        <span>催收数据</span>
        <div class="row end-span middle-span">
          <date-select hiddenDateType @onDateChange="updateCaseRecordData" :currentDateType.sync="caseRecordModel.timeType" :currentDate.sync="caseRecordModel.date"></date-select>
        </div>
      </div>
      <ve-line height="350px" legend-position="bottom" :data="caseRecordChart.data" :settings="caseRecordChart.setting"></ve-line>
    </el-card>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Layout, Dependencies } from "~/core/decorator";
import { VeLine } from "v-charts";
import CaseTypeSelect from "~/components/dashboard/case-type-select.vue";
import DateSelect from "~/components/dashboard/date-select.vue";
import { dashboardQueryType, dashboardTimeType } from "~/config/enum.config";
// import { HomePageService } from "~/services/report-service/home-page.service";
import { CommonService } from "~/utils/common.service";

@Component({
  components: {
    VeLine,
    DateSelect,
    CaseTypeSelect
  }
})
export default class CaseRecordChart extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;

  private caseRecordModel = {
    date: new Date(),
    timeType: dashboardTimeType.month
  };

  private caseRecordChart = {
    data: {
      columns: ["date", "callCount", "followCount"],
      rows: new Array()
    },
    setting: {
      labelMap: {
        callCount: "外呼数量",
        followCount: "催记数量"
      }
    }
  };

  /**
   * 更新案件还款数据
   */
  updateCaseRecordData() {
    // this.homePageService
    //   .getRecordReport(this.caseRecordModel)
    //   .subscribe(({ callCountList, followCountList, callTotalCount, followTotalCount, callAvgCount, followAvgCount }) => {
    //     // 数据集合
    //     let rows: Array<any> = [];
    //     // 设置x轴维度数据
    //     let axis = CommonService.getDateList(
    //       this.caseRecordModel.date,
    //       dashboardTimeType.month
    //     );
    //     // // 生成图标诗句
    //     axis.forEach((item, index) => {
    //       rows.push({
    //         date: item,
    //         callCount: callCountList[index],
    //         followCount: followCountList[index]
    //       });
    //     });
    //
    //     this.caseRecordChart.setting.labelMap = {
    //       callCount: `外呼数量（总计：${callTotalCount}个，平均：${callAvgCount}个）`,
    //       followCount: `催记数量（总计：${followTotalCount}个，平均：${followAvgCount}个）`
    //     };
    //
    //     this.caseRecordChart.data.rows = rows;
    //   });
  }

  mounted() {
    this.updateCaseRecordData();
  }
}
</script>

<style lang="less" scoped>

</style>
