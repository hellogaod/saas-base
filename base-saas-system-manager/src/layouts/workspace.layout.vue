<template>
  <div class="layout col full">
    <work-header></work-header>
    <div class="row row-span">
      <work-menu></work-menu>
      <div class="col-span col">
        <work-tab></work-tab>
        <div class="row-span" style="overflow:auto">
          <keep-alive :include="keepLiveList">
            <router-view class="row-span" />
          </keep-alive>
        </div>
      </div>
    </div>
    <!-- <work-reminder></work-reminder> -->
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { State, namespace } from "vuex-class";
import WorkHeader from "~/components/workspace/work-header.vue";
import WorkMenu from "~/components/workspace/work-menu.vue";
import WorkTab from "~/components/workspace/work-tab.vue";
import WorkReminder from "~/components/workspace/work-reminder.vue";
const workspaceModule = namespace("workspace");
import { ReminderService } from '~/utils/reminder.service'

@Component({
  components: {
    WorkHeader,
    WorkMenu,
    WorkTab,
    WorkReminder
  }
})
export default class WorkSpaceLayout extends Vue {
  @workspaceModule.State currentTabs;
  @workspaceModule.State unReadCount;

  mounted() {
    if(this.unReadCount) {
      ReminderService.notify((options)=>this.$createElement(WorkReminder,options))
    } 
  }
  /**
   * 缓存列表
   */
  get keepLiveList() {
    return  this.currentTabs.filter(x => x.url).map(x => {
      let pathList = x.url.split("/");
      let name = pathList[pathList.length - 1];
      return `-${name}`.replace(/\-(\w)/g, ($0, $1) =>
        $1.toUpperCase()
      )
    })
  }
}
</script>

<style>

</style>
