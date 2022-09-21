<template>
  <div class="component work-menu-item" @click.capture="onSubmenuClick">
    <el-submenu :index="data.menuId" class="submenu">
      <el-row slot="title" class="submenu-item">
        <el-col class="submenu-icon" :span="iconSpan">
          <span :class="data.icon"></span>
        </el-col>
        <el-col class="submenu-title" v-show="!menuCollapse" :span="18">{{data.name}}</el-col>
      </el-row>
      <template v-if="data.subMenu">
        <el-menu-item class="menu-item" v-show="!menuCollapse" v-for="item in data.subMenu" :key="item.menuId" :index="item.url">
          <el-row>
            <el-col class="menu-icon" :span="6">
              <span :class="item.icon"></span>
            </el-col>
            <el-col class="menu-title" :span="18">{{item.name}}</el-col>
          </el-row>
        </el-menu-item>
      </template>
    </el-submenu>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Prop, Emit } from "vue-property-decorator";
import { State, namespace } from "vuex-class";
import SvgIcon from "~/components/common/svg-icon.vue";

const workspaceModule = namespace("workspace");

@Component({
  components: {
    SvgIcon
  }
})
export default class WorkMenu extends Vue {
  @Prop() data: any;
  @State menuCollapse;
  @workspaceModule.State currentTab;
  @Emit("select")
  onSelectSubmenu(path, pathArray) {}

  get iconSpan() {
    return !this.menuCollapse ? 6 : 24;
  }

  /**
   * 目录菜单点击处理
   */
  onSubmenuClick(event) {
    // 仅在菜单折叠状态有效
    if (!this.menuCollapse || !this.data) {
      return;
    } else {
      event.preventDefault();
      event.stopPropagation();
    }

    // 如果打开为同一个submenu则不处理
    if (this.currentTab.startsWith(this.data.url)) {
      return;
    }

    // 如果不含有子路由则不处理
    if (!this.data.children || this.data.children.length === 0) {
      return;
    }

    // 获取目标路由
    let [target] = this.data.children;

    if (target) {
      this.onSelectSubmenu(target.url, [this.data.url, target.url]);
    }
  }
}
</script>

<style lang="less" scoped>
.work-menu-item.component {
  flex-basis: 60px;
  min-width: 60px;
  height: 100%;
  max-height: 100%;

  .submenu {
    .submenu-icon,
    .menu-icon {
      text-align: center;
      border-right: 1px solid;
    }
    .submenu-title,
    .menu-title {
      padding-left: 20px;
      font-weight: lighter;
    }
  }
}
</style>

<style lang="less">
.work-menu-item.component {
  .el-menu-item,
  .el-submenu__title {
    padding: 0 !important;
  }
}
</style>
