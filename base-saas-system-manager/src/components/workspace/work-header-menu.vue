<template>
  <section class="component work-header-menu">
    <el-menu :default-active="data.activeIndex" class="el-menu" mode="horizontal" @select="handleSelect">
      <el-menu-item :index="String(index)" v-for="(item,index) in moduleListResource" :key="index">{{item.sysName}}</el-menu-item>
    </el-menu>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { State, Mutation, Action, namespace } from "vuex-class";
import { entMenuService } from "~/services/systemweb-services/entMenu.service";
import { Dependencies } from "~/core/decorator";
const workspaceModule = namespace("workspace");
@Component({
  components: {
  }
})
export default class WorkMenu extends Vue {
  @Dependencies(entMenuService) private entMenuService: entMenuService;
  @Action("updateUserLoginData") updateUserLoginData;
  @Action("updateModuleData") updateModuleData;
  @State updateUsermoduleListResource;
  private data: any = {
    activeIndex: '0'
  };
  private module:any=[];
  /**
   * 获取头部模块
   */
  get moduleListResource() {
    let menus:any = this.updateUsermoduleListResource
    this.module=menus;
    return menus;
  }
  handleSelect (key, keyPath) {
    let data=this.module[key[0]]
    this.updateModuleData({data});
    this.getModueList(this.module[key[0]].sysCode)
  }
  getModueList(entModuleCode) {
    this.entMenuService.getEntMenuByCode(entModuleCode).subscribe(
      data => {
        this.updateUserLoginData({ data });
        this.$router.push('/dashboard/admin')
      }, ({ msg }) => {
        this.$message.error(msg);
      }
    );
  }
}
</script>

<style lang="less" scoped>
.work-menu.component {
  height: 100%;
  max-height: 100%;
  overflow: auto;
}
.el-menu{
  background: transparent;
}
.el-menu--horizontal>.el-menu-item.is-active{
  background-color: transparent;
  color:#fff
}
</style>
