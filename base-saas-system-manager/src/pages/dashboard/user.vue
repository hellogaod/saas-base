<template>
  <section class="page dashboard user">
    <!--案件完成进度信息-->
    <el-row class="card-container">
      <el-col :span="9">
        <el-card class="box-card">
          <div slot="header" class="card-header">
            <span>周完成进度</span>
          </div>
          <div class="col between-span">
            <div>案件数完成进度</div>
            <el-progress :percentage="caseWeekProcess"></el-progress>
          </div>
          <div>
            <div>回款完成进度</div>
            <el-progress :percentage="caseWeekProcess"></el-progress>
          </div>
        </el-card>
      </el-col>
      <el-col :span="9">
        <el-card class="box-card">
          <div slot="header" class="card-header">
            <span>月完成进度</span>
          </div>
          <div>
            <div>案件数完成进度</div>
            <el-progress :percentage="caseWeekProcess"></el-progress>
          </div>
          <div>
            <div>回款完成进度</div>
            <el-progress :percentage="caseWeekProcess"></el-progress>
          </div>
        </el-card>
      </el-col>
      <el-col :span="3">
        <el-card class="box-card today">
          <div class="col middle-span between-span full">
            <div class="date">{{today.year}}年{{today.month}}月</div>
            <div class="day">{{today.day}}</div>
            <div class="week">{{today.week}}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="3">
        <el-card class="box-card quick col  middle-span end-span " @click.native="getQuickAccessCaseInfo">
          <div class="row-span">
            <svg-icon class="svg-icon" iconName="quick" :iconSize="80"></svg-icon>
          </div>
          <div class="text-center">快速催收</div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <follow-count-info></follow-count-info>
      </el-col>
      <el-col :span="12">
        <case-status-info></case-status-info>
      </el-col>
      <el-col :span="12">
        <repay-rank-list></repay-rank-list>
      </el-col>
      <el-col :span="12">
        <follow-rank-list></follow-rank-list>
      </el-col>
    </el-row>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Auth, Layout, Dependencies } from "~/core/decorator";
import { State, Action, namespace, Getter } from "vuex-class";
// import { HomePageService } from "~/services/report-service/home-page.service";
import SvgIcon from "~/components/common/svg-icon.vue";
import { weekList } from "~/config/enum.config";
import CaseStatusInfo from "~/components/dashboard/user/case-status-info.vue";
import FollowCountInfo from "~/components/dashboard/user/follow-count-info.vue";
import FollowRankList from "~/components/dashboard/user/follow-rank-list.vue";
import RepayRankList from "~/components/dashboard/user/repay-rank-list.vue";
import { caseType, caseState } from "~/config/enum.config";

const accManageModule = namespace("acc-manage");

@Auth(-1)
@Layout("workspace")
@Component({
  components: {
    SvgIcon,
    CaseStatusInfo,
    FollowCountInfo,
    FollowRankList,
    RepayRankList
  }
})
export default class User extends Vue {
  // @Dependencies(HomePageService) homePageService: HomePageService;
  @State userData;
  @accManageModule.Action openAccCenter;
  private today = {};
  private caseCountData: any = {};

  private getRatio(value: number, total: number) {
    if (value && total && value != 0 && total != 0) {
      return (value / total * 100).toFixed(2);
    } else {
      return 0
    }
  }

  get caseWeekProcess() {
    return this.getRatio(
      this.caseCountData.caseWeekFinishedCount,
      this.caseCountData.caseWeekTotalCount
    );
  }

  get repayWeekProcess() {
    return this.getRatio(
      this.caseCountData.caseWeekFinishedCount,
      this.caseCountData.caseWeekTotalCount
    );
  }

  get caseMonthProcess() {
    return this.getRatio(
      this.caseCountData.caseWeekFinishedCount,
      this.caseCountData.caseWeekTotalCount
    );
  }

  get repayMonthProcess() {
    return this.getRatio(
      this.caseCountData.caseWeekFinishedCount,
      this.caseCountData.caseWeekTotalCount
    );
  }

  /**
   * 获取催收数据信息
   */
  getCollectedCount() {
    // this.homePageService.getCollectedCount().subscribe(data => {
    //   this.caseCountData = data;
    // });
  }

  get caseType() {
    switch (this.userData.type) {
      case 1:
        return caseType.call;
      case 2:
        return caseType.visit;
      default:
        return "";
    }
  }

  getQuickAccessCaseInfo() {
    // this.homePageService.getQuickAccessCaseInfo().subscribe(
    //   data => {
    //     this.openAccCenter({
    //       caseId: data.caseId,
    //       cardId: data.idCard,
    //       personalId: data.personalId,
    //       caseType: this.caseType,
    //       searchModel: {
    //         personalName: "", // 客户姓名
    //         mobileNo: "", // 手机号
    //         mobileNoX: "",
    //         batchNumber: "", // 批次号
    //         caseMoney: { min: "", max: "" },
    //         dayRange: { min: "", max: "" },
    //         orderId: "", // 订单号
    //         collectionStatus: "", // 状态
    //         principalId: "", // 委托方
    //         idCard: "", // 身份证
    //         collectorName: "", // 催收员
    //         deptCode: "", // 机构
    //         assistFlag: "", // 是否协催
    //         assistWay: "", // 协催方式
    //         caseMark: "", // 案件标记
    //         companyCode: "", // 公司code
    //         followupBack: "", // 催收反馈
    //         overdueDays: [], // 逾期天数
    //         cupoName: "",
    //         deptCodes: [],
    //         follFeedbacks: [],
    //         payStatus: "", // 逾期期数
    //         collectionType: 15,
    //         collectionStatusList: "20,21,22,23,171,172",
    //         shopDeptId: "",
    //         realPayMinAmt: "", // 最小还款金额
    //         realPayMaxAmt: "", // 最大还款金额
    //         realPayAmount: { min: "", max: "" } // 还款金额
    //       },
    //       status: [
    //         this.userData.waitCollection,
    //         this.userData.collection,
    //         this.userData.overPaying,
    //         this.userData.earlyPaying,
    //         this.userData.repaid,
    //         this.userData.partRepaid
    //       ]
    //     });
    //   },
    //   ({ msg }) => {
    //     this.$message.info(msg);
    //   }
    // );
  }

  /**
   * 获取当天日期信息
   */
  getTodayDate() {
    let date = new Date();
    return {
      year: date.getFullYear(),
      month: (date.getMonth() + 1).toString().padStart(2, "0"),
      day: date
        .getDate()
        .toString()
        .padStart(2, "0"),
      week: weekList[date.getDay()]
    };
  }

  mounted() {
    this.today = this.getTodayDate();
    this.getCollectedCount();
  }
}
</script>

<style lang="less" scoped>
.card-container {
  & > * {
    padding: 10px;
  }
}

.page.dashboard.user {
  .box-card {
    height: 150px;
  }

  .today {
    .day {
      font-size: 40px;
      padding: 18px;
    }
  }

  .quick {
    &:hover {
      background-color: #52b7ea;
      color: #fff;
      .svg-icon {
        color: #fff;
      }
    }
  }
}
</style>

<style lang="less">
.page.dashboard.user {
  .card-container {
    .card-header {
      height: 23px;
      & > *:nth-child(1) {
        font-size: 16px;
      }
      & > *:nth-child(2) {
        font-size: 12px;
      }
    }
  }
  .el-progress-bar {
    padding-right: 60px;
    margin-right: -67px;
  }
  .el-progress__text {
    width: 57px;
    text-align:right;
  }
}
</style>
