<template>
  <section class="component module-role">
    <div style="display:flex">
      <el-col class="module">
        <el-row class="title">菜单模块</el-row>
        <div class="module-tree">
          <el-tree align="left" :default-checked-keys="checkedData" :data="treeResource" show-checkbox highlight-current
                   :default-expanded-keys="[0]" node-key="entMenuId" ref="tree" @current-change="onCurrentNodeChange"
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
  import {sysRoleService} from "~/server/services/enterprise-manage-services/sysRole.service";
  import {sysRoleMenuService} from "~/server/services/enterprise-manage-services/sysRoleMenu.service";
  import {Prop, Emit, Watch} from "vue-property-decorator";
  import {DataTransferUtil} from "~/utils/datatransfer.util";

  @Component({
    components: {
      DataForm,
      DataBox
    }
  })
  export default class ModulePermission extends Vue {
    @Dependencies(sysRoleService) private sysRoleService: sysRoleService;
    @Dependencies(sysRoleMenuService) private sysRoleMenuService: sysRoleMenuService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    // 表格所有资源
    private tableResource: Array<any> = [];
    // 树所有资源
    private treeResource: Array<any> = [];
    // 当前选中的node的数据
    private nodeData: any = {};
    // 当前页面tree对象
    private tree: any = {};
    private menuList: Array<any> = [];

    private dataSet: Array<any> = [];
    private checkedData: any = [];

    private roleMenu: any = {};
    private roleId: any = "";

    get selectionList() {
      return this.dataSet.filter(v => v._checked);
    }

    set selectionList(selection: Array<any>) {
      let selectionIds = selection.map(v => v.id);
      // 设置表格中数据的选择属性
      this.dataSet.forEach(v => (v._checked = selectionIds.includes(v.id)));

      // 如果有选，树上的节点也被勾选
      if (selectionIds.length > 0) {
        this.tree.setChecked(this.nodeData.id, true);
      }
    }

    /**
     * 当前选中节点改变的时候
     */
    private onCurrentNodeChange(node) {
      this.nodeData = node;
      this.dataSet = [];
      this.dataSet = this.tableResource.filter(x => x.parentId === node.id);

    }

    refresh(obj) {
      this.checkedData = [];
      this.roleId = obj.roleId;
      this.sysRoleMenuService.getMenuByRoleId({
        roleId: obj.roleId,
        sysCode: this.$store.state.module.sysCode
      }).subscribe(
        data => {
          this.menuList = data.filter(x => [1].includes(x.menuType));
          this.separateData(data);
        }, ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    // 处理丢失菜单权限的控件资源
    onCheck(data, {checkedKeys}) {

      this.checkedData = checkedKeys
      // 子菜单点击
      if (data.type === 1) {
        let flag = checkedKeys.includes(data.id);
        if (this.nodeData.id === data.id) this.onCurrentNodeChange(data);
      } else {
        data.children.forEach(item => this.onCheck(item, {checkedKeys}));
      }

    }

    mounted() {
      // 挂载之后获取树组件对象
      this.tree = this.$refs.tree as any;
    }

    /**
     * 分离数据 菜单数据 控件资源
     */
    private separateData(data: Array<any>) {
      // 提取菜单资源
      let tmpTreeResource = data.filter(x => [1].includes(x.menuType)).map(v => {
        if (v.checked === 1) {//选中
          this.checkedData.push(v.entMenuId)
        }
        return {
          label: v.menuName,
          pid: v.parentId,
          id: v.id,
          type: v.menuType,
          entMenuId: v.entMenuId
        };
      });
      this.treeResource = DataTransferUtil.generateTreeData(tmpTreeResource);
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

      //1. 把checkMenuids转换成checkIds
      var checkedIds: any = [];
      this.menuList.forEach(item => {
        if (this.checkedData.includes(item.entMenuId)) {
          checkedIds.push(item.id)
        }
      });


      //2. 根据checkids，找出半选中的ids
      var halfcheckedDataIds: any = [];
      this.menuList.forEach(item => {
        if (checkedIds.includes(item.id) && !checkedIds.includes(item.parentId)) {
          halfcheckedDataIds.push(item.parentId)
        }
      });

      //3. 半选中，实际获取的是menuId，而不是id，所以这里要重新获取menuid
      var halfCheckedMenuids: any = [];
      this.menuList.forEach(item => {
        if (halfcheckedDataIds.includes(item.id)) {
          halfCheckedMenuids.push(item.entMenuId);
        }
      })

      this.roleMenu.menuIds = this.checkedData;
      this.roleMenu.roleId = this.roleId;
      this.roleMenu.halfMenuIds = halfCheckedMenuids;

      // console.log("所有节点：" + JSON.stringify(this.menuList));
      // console.log("选中节点：" + JSON.stringify(this.checkedData));
      // console.log("半选中：" + JSON.stringify(halfCheckedMenuids));

      this.saveRoleMenu();
    }

    saveRoleMenu() {
      this.sysRoleMenuService.addRoleMenu(this.roleMenu).subscribe(
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
