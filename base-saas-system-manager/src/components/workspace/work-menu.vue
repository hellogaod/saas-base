<template>
  <section class="component work-menu" :class="[{collapse:menuCollapse}]">
    <div class="collapse-icon">
      <a @click="menuCollapse=!menuCollapse">
        <svg-icon iconName="menu"></svg-icon>
      </a>
    </div>
    <el-menu unique-opened :default-active="$route.fullPath" @select="selectMenuItem" style="padding-bottom: 60px;">
      <work-menu-item v-for="item in menuList" :data="item" :key="item.menuId" @select="selectMenuItem"></work-menu-item>
    </el-menu>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import WorkMenuItem from "~/components/workspace/work-menu-item.vue";
import SvgIcon from "~/components/common/svg-icon.vue";
import { State, Mutation, Action, namespace } from "vuex-class";
const workspaceModule = namespace("workspace");
@Component({
  components: {
    WorkMenuItem,
    SvgIcon
  }
})
export default class WorkMenu extends Vue {
  @State menuResource;
  @State("menuCollapse") _menuCollapse;
  @Mutation updateMenuCollapse;
  @workspaceModule.Action updateTabs;

  get menuCollapse() {
    return this._menuCollapse;
  }

  set menuCollapse(value) {
    this.updateMenuCollapse(value);
  }

  /**
   * 菜单列表
   * 17 - 一级菜单
   * 18 - 二级菜单
   */
  get menuList() {
    let menus = this.menuResource
    return menus;
  }

  /**
   * 选择菜单项
   * 生成缓存项
   */
  selectMenuItem(path, pathArray) {
    // 防止重复点击
    if (this.$route.path === path) {
      return;
    }

    // 路径长度验证
    if (pathArray.length !== 2) {
      return;
    }

    // 获取一级,二级路径
    let [path1, path2] = pathArray;

    // 非统计菜单需要更新tabs
    // if (!this.$route.path.startsWith(path1)) {
    //   let target = this.menuResource.find(x => x.url === path1);
    //   target && this.updateTabs(target.id);
    //   this.updateTabs(target.id);
    // }

    this.$nextTick(() => {
      this.$router.push(path);
    });
  }
}
</script>

<style lang="less" scoped>
.work-menu.component {
  flex-basis: 200px;
  width: 200px;
  height: 100%;
  max-height: 100%;
  overflow: auto;

  &.collapse {
    flex-basis: 60px;
    width: 60px;
  }

  &::-webkit-scrollbar {
    width: 0px;
  }

  .collapse-icon {
    height: 56px;
    line-height: 56px;
    text-align: center;
    border-bottom: 1px solid;
    a {
      display: inline-block;
      width: 56px;
      text-align: center;
    }
  }
}
</style>

<style lang="less">
.component.work-menu {
  .el-menu {
    border-right: 0; // 消除菜单左侧突出边距
  }
  &.collapse .el-submenu__icon-arrow {
    display: none;
  }
}
</style>

