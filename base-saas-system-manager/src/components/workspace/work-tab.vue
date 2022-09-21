<template>
  <div class="component work-tab">
    <el-tabs type="card" v-model="currentTab" @tab-click="changeTab">
      <el-tab-pane v-for="tab in currentTabs" :key="tab.path" :label="tab.name" :name="tab.url"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { State, Mutation,namespace } from "vuex-class";
const workspaceModule = namespace('workspace')

@Component({
  components: {}
})
export default class WorkTab extends Vue {

  @State userResource
  @Mutation updateTabs
  @workspaceModule.State currentTabs
  @workspaceModule.State('currentTab') _currentTab
  @workspaceModule.Mutation('updateCurrentTab') updateCurrentTab

  get currentTab(){
    return this._currentTab
  }

  set currentTab(value){
    if(this.currentTab !== value){
      this.updateCurrentTab(value)
    }
  }

  changeTab() {
    if (this.$route.path !== this.currentTab) {
      this.$router.push(this.currentTab)
    }
  }
}
</script>

<style scoped>

</style>
