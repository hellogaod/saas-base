<template>
  <div class="component message-detail">
    <data-grid :labelWidth="100" row-key="orapId" contentAlign="left">
      <data-grid-item label="消息类型：" :span="6" :min-width="$helper.getColumnWidth(2)">{{message.type|enumConvert('reminderType')}}</data-grid-item>
      <data-grid-item label="日期：" :span="6" :min-width="$helper.getColumnWidth(2)">{{message.createTime|dateFormat}}</data-grid-item>
      <data-grid-item label="消息标题：" :span="12" :min-width="$helper.getColumnWidth(2)">
        {{message.title}}
      </data-grid-item>
      <data-grid-item label="消息内容：" :span="12" :min-width="$helper.getColumnWidth(2)">{{message.content}}</data-grid-item>
    </data-grid>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Dependencies } from "~/core/decorator";
// import { ReminderMessageService } from "~/services/reminder-service/reminder-message.service";
import { Watch, Prop, Emit } from "vue-property-decorator";
import { State, Mutation, Action, namespace } from "vuex-class";
import { DataGrid, DataGridItem } from "@zct1989/vue-component";
import { messageState } from "~/config/enum.config";

const workspaceModule = namespace("workspace")

@Component({
  components: {
    DataGrid,
    DataGridItem
  }
})
export default class MessageDetail extends Vue {
  // @Dependencies(ReminderMessageService)
  // reminderMessageService: ReminderMessageService;
  @Prop({ required: true })
  message;

  @workspaceModule.Action updateUnReadCount
  @Emit("readChange")
  readChange() {

  }
  @Watch("message")
  onMessageChange(val, oldVal) {
    if (!val) {
      return;
    }

    if (val.state === messageState.unRead.value) {
      // this.reminderMessageService.setMessageRead(val.id).subscribe(() => {
      //   val.state = messageState.read.value;
      //   this.updateUnReadCount()
      //   this.readChange()
      // });
    }
  }
}
</script>
