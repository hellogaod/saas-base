<template>
  <div class="layout col full">
    <work-header></work-header>
    <div class="row row-span">
      <!--左边菜单栏-->
      <work-menu></work-menu>
      <div class="col-span col">
        <div class="row-span" style="overflow:auto">
          <router-view class="row-span"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import WorkHeader from "~/components/workspace/work-header.vue";
  import WorkMenu from "~/components/workspace/work-menu.vue";

  @Component({
    components: {
      WorkHeader,
      WorkMenu,
    }
  })
  export default class WorkSpaceLayout extends Vue {

  }
</script>

<style>

</style>
