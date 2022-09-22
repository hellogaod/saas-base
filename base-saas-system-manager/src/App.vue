<template>
  <div id="app" v-loading.fullscreen="!ready">
    <keep-alive :include="getCacheLayout">
      <template v-if="ready">
        <component :is="layout"></component>
      </template>
    </keep-alive>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { State } from "vuex-class";
import DefaultLauout from "~/layouts/default.layout.vue";
import WorkspaceLayout from "~/layouts/workspace.layout.vue";
@Component({
  components: {
    default: DefaultLauout,
    workspace: WorkspaceLayout
  }
})
export default class App extends Vue {
  @State ready: boolean;
  // @State theme: string;
  @State layout: string;
  @State userToken: string;

  get getCacheLayout(){
    let cache:Array<string> = []

    if(this.userToken){
      cache.push("WorkSpaceLayout")
    }

    return cache
  }

  created() {}
}
</script>

<style lang="less">
@import "normalize-css/normalize.css";
@import "@zct1989/vue-component/dist/index.css";
@import "assets/styles/layout.less";
@import "assets/styles/common.less";
@import "assets/styles/default.less";
@import "assets/styles/theme.less";
</style>
