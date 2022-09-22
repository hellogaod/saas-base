<template>
  <section class="component case-group-chart">
    <el-card class="box-card">
      <div slot="header" class="card-header row between-span">
        <span>案件催收反馈数据</span>
        <div class="row end-span middle-span">
          <date-select @onDateTypeChange="updateCaseGourpData" @onDateChange="updateCaseGourpData" :currentDateType.sync="caseGroupModel.timeType" :currentDate.sync="caseGroupModel.date"></date-select>
        </div>
      </div>
      <el-row>
        <el-col :span="12">
          <ve-pie height="350px" legend-position="bottom" :data="caseAmountChart.data" :tooltip="caseAmountToo" :settings="caseAmountChart.setting"></ve-pie>
        </el-col>
        <el-col :span="12">
          <ve-ring height="350px" legend-position="bottom" :data="caseCountChart.data" :settings="caseCountChart.setting"></ve-ring>
        </el-col>
      </el-row>
    </el-card>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Layout, Dependencies } from "~/core/decorator";
import { VePie, VeRing } from "v-charts";
import CaseTypeSelect from "~/components/dashboard/case-type-select.vue";
import DateSelect from "~/components/dashboard/date-select.vue";
import { dashboardQueryType, dashboardTimeType } from "~/config/enum.config";
// import { HomePageService } from "~/services/report-service/home-page.service";
// import { CommonService } from "~/utils/common.service";

@Component({
  components: {
    VePie,
    VeRing,
    DateSelect,
    CaseTypeSelect
  }
})
export default class CaseGroupChart extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;

  private caseGroupModel = {
    timeType: dashboardTimeType.year,
    queryType: [dashboardQueryType.inner, dashboardQueryType.outer],
    date: new Date()
  };

  private caseAmountChart = {
    data: {
      columns: ["name", "value"],
      rows: []
    },
    setting: {
      label: {
        normal: {
          fontSize: 10
        }
      },
      labelLine: {
        normal: {
          length: 5,
          length2: 5
        }
      },
      radius: 80
    }
  };

  private caseAmountToo = {
    formatter: '{c}元 ({d}%)'
  }

  private caseCountChart = {
    data: {
      columns: ["name", "value"],
      rows: []
    },
    setting: {
      label: {
        normal: {
          fontSize: 10
        }
      },
      labelLine: {
        normal: {
          length: 5,
          length2: 5
        }
      },
      radius: [50, 80]
    }
  };

  /**
   * 更新案件还款数据
   */
  updateCaseGourpData() {
    // this.homePageService
    //   .getCaseGroupInfo(this.caseGroupModel)
    //   .subscribe(({ totalAmtList, totalCountList }) => {
    //     totalAmtList.forEach(v => {
    //       v.value = isNaN(parseFloat(v.value))?'0.00':parseFloat(v.value).toFixed(2)
    //     });
    //     this.caseAmountChart.data.rows = totalAmtList;
    //     this.caseCountChart.data.rows = totalCountList;
    //   });
  }

  mounted() {
    this.updateCaseGourpData();
  }
}
</script>

<style lang="less" scoped>

</style>
