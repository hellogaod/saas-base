<!--头部布局-系统模块信息-->
<template>
  <section class="component work-header-menu">
    <el-menu :default-active="data.activeIndex" class="el-menu" mode="horizontal" @select="handleSelect">
      <el-menu-item :index="String(index)" v-for="(item,index) in moduleListResource" :key="index">{{item.moduleName}}
      </el-menu-item>
    </el-menu>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {State, Mutation, Action, namespace} from "vuex-class";
  import {entMenuService} from "~/server/services/enterprise-manage-services/ent-menu.service";
  import {Dependencies} from "~/core/decorator";

  @Component({
    components: {}
  })
  export default class WorkMenu extends Vue {
    @Dependencies(entMenuService) private entMenuService: entMenuService;
    @Action("updateUserLoginData") updateUserLoginData;
    @Action("updateModuleData") updateModuleData;
    @State modules;
    private data: any = {
      activeIndex: '0'
    };
    private moduleList: any = [];

    /**
     * 获取头部模块
     */
    get moduleListResource() {
      let modules: any = this.modules

      this.moduleList = modules;
      return modules;
    }

    handleSelect(key, keyPath) {
      let data = this.moduleList[key[0]]

      this.updateModuleData({data})
    }
  }
</script>

<style lang="less" scoped>
  .work-menu.component {
    height: 100%;
    max-height: 100%;
    overflow: auto;
  }

  .el-menu {
    background: transparent;
  }

  .el-menu--horizontal > .el-menu-item.is-active {
    background-color: transparent;
    color: #fff
  }
</style>
