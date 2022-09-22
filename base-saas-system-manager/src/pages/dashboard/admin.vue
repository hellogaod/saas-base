<template>
  <section class="page dashboard admin">
    <div class="card-container">
      <el-row :gutter="20">
        <el-col :span="16">
          <el-card style="position: relative">
            <el-row style="padding: 10px;box-sizing: border-box;">
              <el-col :span="18">
                <span style="font-size: 23px;">总览</span>
              </el-col>
              <el-col :span="6" style="font-size: 25px;color: #13227A">
                <span style="color: #666;font-size: 15px;">借款总额:</span>
                {{dashboardModel.loanAmount}}
              </el-col>
            </el-row>
            <ve-ring :data="ringData"></ve-ring>
            <div class="ring_bom">数据截止当前时间</div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="mb_20">
            <ul class="user_card">
              <li>
                <div>
                  <i class="icon el-icon-service mr_10 big"></i>
                  <strong>{{dashboardModel.toDayApplyAmount}}</strong>
                </div>
                <div class="bom_txt">今日授信总额</div>
              </li>
              <li>
                <div>
                  <i class="icon el-icon-star-on mr_10 big"></i>
                  <strong>{{dashboardModel.toDayLoadAmount}}</strong>
                </div>
                <div class="bom_txt">今日借款总额</div>
              </li>
            </ul>
          </el-card>
          <!--<el-card>-->
          <!--<ul class="user_card">-->
          <!--<li>-->
          <!--<div>-->
          <!--<i class="icon el-icon-location mr_10 big"></i>-->
          <!--<strong>100000.00</strong>-->
          <!--</div>-->
          <!--<div class="bom_txt">今日应还总额</div>-->
          <!--</li>-->
          <!--<li>-->
          <!--<div>-->
          <!--<i class="icon el-icon-menu mr_10 big"></i>-->
          <!--<strong>100000.00</strong>-->
          <!--</div>-->
          <!--<div class="bom_txt">今日截至逾期总额</div>-->
          <!--</li>-->
          <!--</ul>-->
          <!--</el-card>-->
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :sdivan="24">
          <el-card>
            <h1>点线图</h1>
            <ve-scatter :data="scatterData" :options="setScatter"></ve-scatter>
          </el-card>
        </el-col>
      </el-row>

    </div>

  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Auth, Layout, Dependencies} from "~/core/decorator";

  import 'v-charts/lib/style.css'
  import VeRing from 'v-charts/lib/ring'
  import VeScatter from 'v-charts/lib/scatter'
  // import {loanManageService} from "~/services/systemweb-services/loanManage.service";

  @Auth(-1)
  @Layout("workspace")
  @Component({
    components: {
      VeRing,
      VeScatter
    }
  })
  export default class Admin extends Vue {
    // @Dependencies(loanManageService) private loanManageService: loanManageService;

    private dashboardModel: any = {};
    private scatterData: any = {
      columns: ['日期', '注册用户', '授信用户', '下单数量'],
      rows: [
        // {'日期': '1/2', '注册用户': 1223, '授信用户': 6, '下单用户': 2344},

      ]
    };
    private ringData: any = {
      columns: ['名称', '数量'],
      rows: [
        {'名称': '', '数量': 0},
        {'名称': '', '数量': 0}
      ]
    };
    private setScatter: any = {
      legend: {
        bottom: '10px',
        data: []
      }
    };

    mounted() {
      this.refreshData()
    }

    refreshData() {
      // this.loanManageService.getDashboardInfo().subscribe(data => {
      //
      //   this.dashboardModel = data
      //
      //   let itemData1 = {'名称': '还款总金额', '数量': 3123}
      //   //饼状图信息
      //   this.ringData.rows.push({'名称': '还款总金额', '数量': data.repaymentAmount})
      //   this.ringData.rows.push({'名称': '未还款金额', '数量': data.unRepaymentAmount})
      //   // {'名称': '还款总金额', '数量': 3123},
      //   // {'名称': '未还款金额', '数量': 3123}
      //   //折线图信息
      //   data.dashboardAcount.forEach((item, index) => {
      //
      //     let rowItem = {'日期': item.days, '注册用户': item.RegisterCount, '授信用户': item.ApplyCount, '下单用户': item.OrderCount}
      //     this.scatterData.rows.push(rowItem)
      //   })
      // })
    }
  }
</script>

<style lang="less" scoped>
  .page.dashboard.admin {
    .card-container {
      padding: 10px;

      & > * {
        padding: 10px;
      }

      .user_card {
        display: flex;
        justify-content: space-around;
        align-items: center;
        height: 170px;

        li {
          list-style: none;
          text-align: center;
        }
      }

      .ring_bom {
        position: absolute;
        bottom: 10px;
        right: 60px;
        color: #999;
        font-size: 15px;
      }
    }
  }
</style>

<style lang="less">
  .page.dashboard.admin {
    .card-container {
      .card-header {
        height: 23px;

        & > *:nth-child(1) {
          font-size: 18px;
        }

        & > *:nth-child(2) {
          font-size: 14px;
        }
      }

      .mb_20 {
        margin-bottom: 25px;
      }

      .mr_10 {
        margin-right: 10px;
      }

      .big {
        font-size: 40px;
        vertical-align: middle;
        color: #13227A;
      }

      strong {
        line-height: normal;
        color: #13227A;
      }

      .bom_txt {
        line-height: 30px;
        font-size: 14px;
        color: #999;
        text-align: left;
      }
    }
  }
</style>
