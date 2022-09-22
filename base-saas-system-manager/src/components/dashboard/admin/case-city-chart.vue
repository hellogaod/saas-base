<template>
  <section class="component case-city-chart">
    <el-card class="box-card">
      <div slot="header" class="card-header">
        <span>催收中案件数据</span>
      </div>
      <ve-map height="350px" :legend-visible="false" :data="caseCityChart.data" :settings="caseCityChart.setting"></ve-map>
    </el-card>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Layout, Dependencies } from "~/core/decorator";
import { VeMap } from "v-charts";
import CaseTypeSelect from "~/components/dashboard/case-type-select.vue";
import DateSelect from "~/components/dashboard/date-select.vue";
import { dashboardQueryType, dashboardTimeType } from "~/config/enum.config";
// import { HomePageService } from "~/services/report-service/home-page.service";
import { CommonService } from "~/utils/common.service";

@Component({
  components: {
    VeMap,
    DateSelect,
    CaseTypeSelect
  }
})
export default class CaseCityChart extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;

  // 图表设置
  private caseCityChart = {
    data: {
      columns: ["name", "value"],
      rows: []
    },
    setting: {
      selectData: true,
      label: false,
      position: "china",
      labelMap: {
        value: "案件数"
      }
    }
  };

  /**
   * 获取城市案件数据
   */
  updateCaseCityData() {
    // this.homePageService
    //   .getCollectionedDate()
    //   .subscribe(({ totalProvinceCollectionCount }) => {
    //     this.caseCityChart.data.rows = totalProvinceCollectionCount;
    //   });
  }

  mounted() {
    this.updateCaseCityData();
  }
}
</script>

<style lang="less" scoped>
.search-container {
  height: 26px;
}
</style>
