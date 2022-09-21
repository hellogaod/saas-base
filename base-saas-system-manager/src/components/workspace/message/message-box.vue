<template>
  <section class="component message-box">
    <div class="row end-span" style="margin-top:-20px;margin-right:10px">
      <el-button @click="deleteMessages">删除</el-button>
    </div>
    <data-box :selection-list.sync="selectionList" :row-class="getRowClass" ref="databox" row-key="id" :data="messageDataSet" :page="pageService" @onPageChange="refreshData">
      <template slot="columns">
        <el-table-column prop="type" label="消息类型" :min-width="$helper.getColumnWidth(5)">
          <template slot-scope="scope">
            {{scope.row.type|enumConvert('reminderType') }}
          </template>
        </el-table-column>
        <el-table-column prop="title" label="消息标题" align="left" :show-overflow-tooltip="true" :min-width="$helper.getColumnWidth(8)">
          <template slot-scope="scope">
            {{scope.row.title}}
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="日期" :min-width="$helper.getColumnWidth(4)">
          <template slot-scope="scope">
            {{scope.row.createTime|dateFormat}}
          </template>
        </el-table-column>
        <el-table-column label="查看详情" :min-width="$helper.getColumnWidth(4)">
          <template slot-scope="scope">
            <el-button size="small" @click="openMessageDetail(scope.row)" type="text">查看</el-button>
          </template>
        </el-table-column>
      </template>
    </data-box>
    <el-dialog title="消息详情" :visible.sync="dialog.messageDetail" append-to-body>
      <message-detail :message="currentMessage"></message-detail>
    </el-dialog>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Dependencies } from "~/core/decorator";
// import { ReminderMessageService } from "~/services/reminder-service/reminder-message.service";
import { PageService } from "~/utils/page.service";
import { State, Mutation, Action, namespace } from "vuex-class";
import MessageDetail from "~/components/workspace/message/message-detail.vue";
import DataBox from "~/components/common/data-box.vue";
import DataForm from "~/components/common/data-form.vue";
import { messageState } from "~/config/enum.config";
import { Prop,Watch } from "vue-property-decorator";

const workspaceModule = namespace("workspace");
@Component({
  components: {
    DataBox,
    DataForm,
    MessageDetail
  }
})
export default class MessageBox extends Vue {
  // @Dependencies(ReminderMessageService)
  // reminderMessageService: ReminderMessageService;
  @Dependencies(PageService) pageService: PageService;
  @workspaceModule.Action updateUnReadCount;
  @workspaceModule.Mutation updateMessageBoxVisible;
  @State userData: any;
  @Prop() visible
  @Watch("visible")
  onVisibleChange(value){
    if(value){
      this.refreshData()
    }
  }
  private messageDataSet: Array<any> = [];
  private selectionList = [];
  private currentMessage: any = "";
  private dialog = {
    messageDetail: false
  };

  /**
   * 获取行样式
   */
  getRowClass(scope, index) {
    return scope.row.state === messageState.unRead.value ? "unread" : "read";
  }

  /**
   * 刷新数据
   */
  refreshData() {
    // this.reminderMessageService
    //   .getReminderMessages(this.pageService)
    //   .subscribe(data => {
    //     this.messageDataSet = data;
    //     let type = data.map(x => x.type === 'CASE_IMPORT')
    //     if (type.length > 0) {
    //       this.updateMessageBoxVisible(true)
    //     }
    //   });
  }

  /**
   * 打开消息详情
   */
  openMessageDetail(message) {
    this.dialog.messageDetail = true;
    this.$nextTick(() => {
      this.currentMessage = message;
    });
  }

  /**
   * 批量删除消息
   */
  deleteMessages() {
    if(!this.selectionList.length) {
      this.$message('请选择要删除的消息')
    }
    let messageIdList = this.selectionList.map((x: any) => x.id);
    // this.reminderMessageService.batchDelete(messageIdList).subscribe(
    //   () => {
    //     this.pageService.reset();
    //     this.refreshData();
    //     this.updateUnReadCount();
    //   },
    //   ({ msg }) => this.$message.error(msg)
    // );
  }

  /**
   * 初始化
   */
  mounted() {
    this.refreshData();
  }
}
</script>

<style lang="less">
.message-box.component {
  .data-box {
    .el-table__row {
      &.unread {
        color: #33aacc !important;
      }
      &.read {
        color: gray !important;
      }
    }
  }
}
</style>
