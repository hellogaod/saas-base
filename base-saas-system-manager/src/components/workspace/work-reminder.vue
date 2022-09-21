<template>
  <section class="component work-reminder">
    <div v-for="item of messageList" :key="item.id" class="reminderItem" @click="dialog.messageDetail = true,$nextTick(()=>currentMessage=item)" style="height:70px;position:relative;cursor:pointer;">
      <div class="text-left" style="padding-top:10px;">
        <div :style="getReminderStyle(item.state)" style="float:left;margin-left:-5px;padding-right:5px;">【{{item.type|enumConvert('reminderType')}}】</div>
        <div style="color:#28313E">{{item.title}}</div>
      </div>
      <div class="text-right" style="position:absolute;bottom:3px;right:5px;color:#8C8C8C">{{item.createTime | dateFormat}}</div>
    </div>
    <div class="bottom text-right">
      <span style="cursor:pointer" @click="commit('updateMessageBoxVisible', true)">更多...</span>
    </div>
    <el-dialog title="消息详情" :visible.sync="dialog.messageDetail" append-to-body>
      <message-detail :message="currentMessage" @readChange="refreshData"></message-detail>
    </el-dialog>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import store from '~/store';
// import { createNamespacedHelpers } from 'vuex';
// import MessageBox from "~/components/workspace/message/message-box.vue";
import SvgIcon from "~/components/common/svg-icon.vue";
import { Dependencies } from "~/core/decorator";
// import { ReminderMessageService } from "~/services/reminder-service/reminder-message.service";
import MessageDetail from "~/components/workspace/message/message-detail.vue";

@Component({
  components: {
    SvgIcon,
    MessageDetail
  }
})
export default class WorkReminder extends Vue {
  // @Dependencies(ReminderMessageService) reminderMessageService: ReminderMessageService;
  private messagebounced: any = false;
  private messageList: any = [];
  private unReadeMessage: any = 0;
  private currentMessage: any = {};
  private dialog = {
    messageDetail: false,
    close: false
  };
  
  private store:any = store

  get commit(){
    return this.store._modulesNamespaceMap['workspace/'].context.commit
  }
  

  getReminderStyle(type) {
    let reminderTypeStyle = {
      'UnRead': {
        color: '#ff5722'
      },
      'Read': {
        color: '#0092dc'
      }
    }
    return reminderTypeStyle[type]
  }
  refreshData() {
    // this.reminderMessageService.getWorkbenchReminder().subscribe(data => {
    //   this.messagebounced = true
    //   this.unReadeMessage = data.unReadeCount
    //   this.messageList = data.messageList.content
    //   if(this.unReadeMessage === 0) {
    //     this.messagebounced = false
    //   }
    // });
  }
  mounted() {
    
    this.refreshData()
    // 收到消息通知时更新未读消息
    // this.$reminder
    // .addMessageListener()
    // .subscribe(data=>{
    //   this.refreshData()
    // })
    
  }
}
</script>

<style lang="less" scoped>
.component.work-reminder{
  width:280px;
}
  // .component.work-reminder {
  //   width: 280px;
  //   position: fixed;
  //   height: 0px;
  //   z-index: 2000;
  //   box-shadow: 0 0 8px rgba(0, 0, 0, 0.2);
  //   right: 35px;
  //   bottom: 33px;
  //   transition: height 0.5s;
  //   background: #fff;
  //   .title {
  //     height: 30px;
  //     background: #E3F2FD;
  //     line-height: 30px;
  //     padding: 0px 20px;
  //     font-size: 16px;
  //     color: #64B5F6;
  //     text-align: left;
  //   }
  //   .bottom {
  //     height: 24px;
  //     line-height: 24px;
  //     font-size: 12px;
  //     padding: 0 20px;
  //     color: #FF5722;
  //   }
  //   .reminderItem {
  //     background: #fff;
  //     font-size: 12px;
  //     box-sizing: border-box;
  //     padding: 10px 31px 5px;
  //     * {
  //       line-height: 18px;
  //     }
  //     border-bottom: solid 1px lightgrey;
  //   }
  // }
  // .component.work-reminder.active {
  //   height: auto;
  // }
</style>
