<!--整个框架的入口布局-->
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
  import {State} from "vuex-class";
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

    @State layout: string;
    @State userToken: string;

    get getCacheLayout() {
      let cache: Array<string> = []

      if (this.userToken) {
        cache.push("WorkSpaceLayout")
      }

      return cache
    }

    created() {
    }
  }
</script>

<style lang="less">
  @import "assets/styles/layout.less";
  @import "assets/styles/common.less";
  @import "assets/styles/default.less";
  @import "assets/styles/theme.less";
</style>
