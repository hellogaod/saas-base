<template>
  <section class="component module-role">
    <div style="display:flex">
      <el-col class="module">
        <el-row class="title">所属模块</el-row>
        <el-row class="title">
          <el-select v-model="selectedModuleId" placeholder="请选择" @change="selecModule">
            <el-option
              v-for="item in moduleList" :key="item.moduleId" :label="item.moduleName" :value="item.moduleId">
            </el-option>
          </el-select>
        </el-row>
        <el-row class="title">模块所属菜单</el-row>
        <div class="module-tree">
          <el-tree align="left" :default-checked-keys="currentCheckedData" :data="treeResource" show-checkbox
                   highlight-current
                   :default-expanded-keys="[0]" node-key="menuId" :props="defaultProps" ref="tree"
                   @current-change="onCurrentNodeChange"
                   @check="onCheck"></el-tree>
        </div>
        <el-row class="title">
          <el-button @click="chooseTreeCancel">取消</el-button>
          <el-button @click="chooseTreeCommit">确定</el-button>
        </el-row>
      </el-col>
    </div>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import DataForm from "~/components/common/data-form.vue";
  import DataBox from "~/components/common/data-box.vue";
  import {Dependencies} from "~/core/decorator";
  import {entRoleService} from "~/server/services/enterprise-manage-services/ent-role.service";
  import {entRoleMenuService} from "~/server/services/enterprise-manage-services/ent-role-menu.service";
  import {Prop, Emit, Watch} from "vue-property-decorator";

  @Component({
    components: {
      DataForm,
      DataBox
    }
  })
  export default class ModulePermission extends Vue {
    @Dependencies(entRoleService) private entRoleService: entRoleService;
    @Dependencies(entRoleMenuService) private entRoleMenuService: entRoleMenuService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    // 树所有资源
    private treeResource: Array<any> = [];
    // 当前选中的node的数据
    private nodeData: any = {};
    // 当前页面tree对象
    private tree: any = {};
    private menuList: Array<any> = [];

    private moduleList: Array<any> = [];
    private defaultProps: any = {
      children: 'subMenus',
      label: 'menuName'
    }
    private selectedModuleId: any = ""
    private dataSet: Array<any> = [];
    private currentCheckedData: any = [];
    private checkedMenus: Array<any> = [];

    private roleMenu: any = {
      menuIds: [],
      roleId: ""
    };
    private roleId: any = "";

    selecModule(value) {
      let num: number = this.moduleList.findIndex(item => {
        return item.moduleId == value
      })

      this.currentCheckedData = this.checkedMenus[num]
      this.treeResource = this.moduleList[num].menuList

    }

    /**
     * 当前选中节点改变的时候
     */
    private onCurrentNodeChange(node) {
      this.nodeData = node;
      this.dataSet = [];
      this.selectedModuleId = this.moduleList[0].moduleId;

      this.treeResource = this.moduleList[0].menuList
    }

    refresh(obj) {
      this.currentCheckedData = [];
      this.roleId = obj.roleId;
      this.entRoleMenuService.getMenuListByRoleId({
        roleId: obj.roleId
      }).subscribe(
        data => {
          this.moduleList = data

          if (this.moduleList.length > 0) {
            this.selectedModuleId = this.moduleList[0].moduleId;

            this.treeResource = this.moduleList[0].menuList
            this.currentCheckedData = this.moduleList[0].checkedMenuIds

            this.moduleList.forEach((item, index) => {
              this.checkedMenus[index] = item.checkedMenuIds
            })
          }

        }, ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    // 处理丢失菜单权限的控件资源
    onCheck(data, {checkedKeys}) {
      let num: number = this.moduleList.findIndex(item => {
        return item.moduleId == data.moduleId
      })

      this.currentCheckedData = checkedKeys
      this.checkedMenus[num] = checkedKeys
    }

    mounted() {
      // 挂载之后获取树组件对象
      this.tree = this.$refs.tree as any;
    }

    /**
     * 取消操作
     */
    chooseTreeCancel() {
      this.$emit("close");
    }

    /**
     * 确定操作
     */
    chooseTreeCommit() {

      this.roleMenu.menuIds = []

      this.checkedMenus.forEach((item, index) => {
        let menuArray = item
        menuArray.forEach((obj)=>
          this.roleMenu.menuIds.push(obj)
        )
      })

      const map = new Map()
      this.roleMenu.menuIds = this.roleMenu.menuIds.filter(item => !map.has(item) && map.set(item, 1))
      console.log("item:" + JSON.stringify(this.roleMenu.menuIds))

      this.roleMenu.roleId = this.roleId;

      this.saveRoleMenu();
    }


    saveRoleMenu() {
      this.entRoleMenuService.addRoleMenu(this.roleMenu).subscribe(
        data => {
          this.$message.success("保存成功!");
          this.refreshList();
          this.close();
        }, ({msg}) => {
          this.$message.error(msg);
        }
      );
    }
  }
</script>

<style lang="less" scoped>
  .title {
    height: 40px;
    background: #e4e4e4;
    line-height: 40px;
    text-align: center;
    border: 1px solid #dddddd;
    font-size: 0.875rem;
    color: #333;
  }

  .btn-box {
    margin-top: 20px;
    text-align: center;
  }

  .module {
    flex-basis: 500px;
    border: 1px solid #e4e4e4;
    height: 500px;
    width: 500px;
  }

  .module-tree {
    height: 420px;
    overflow: auto;
    -ms-overflow-style: none;
    overflow: -moz-scrollbars-none;
  }

  .module-tree::-webkit-scrollbar {
    width: 0px;
  }
</style>
