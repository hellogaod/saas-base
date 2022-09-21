<template>
  <div class="component work-header row between-span middle-span">
    <div class="row start-span middle-span logo-container">
      <img src="@/assets/images/common/logo.png"/>
    </div>
    <div class="middleMenu">
      <work-header-menu></work-header-menu>
    </div>
    <div class="row end-span middle-span">
      <slot>
        <!-- <work-task></work-task>
        <work-message></work-message> -->
        <work-user></work-user>
      </slot>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import WorkUser from "~/components/workspace/work-user.vue";
import WorkMessage from "~/components/workspace/work-message.vue";
import WorkTask from "~/components/workspace/work-task.vue";
import WorkHeaderMenu from "~/components/workspace/work-header-menu.vue";
@Component({
  components: {
    WorkUser,
    WorkTask,
    WorkMessage,
    WorkHeaderMenu
  }
})
export default class WorkHeader extends Vue {
}
</script>

<style lang="less" scoped>
.component.work-header {
  flex-basis: 60px;
  height: 60px;
  min-height: 60px;

  -moz-user-select: none;/*支持Firefox浏览器*/
  -webkit-user-select: none;/*支持Chrome，Opera，Safari等浏览器*/
  -ms-user-select: none;/*支持IE浏览器*/
  user-select: none;

  .logo-container{
    width: 200px;
  }
  .logo {
    padding: 10px;
  }
  .middleMenu{
    position: absolute;
    left: 200px;
  }
}
</style>
