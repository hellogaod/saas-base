<template>
  <section class="component module-role">
    <div style="display:flex">
      <el-col class="module">
        <el-row class="title">公司及部门</el-row>
        <div class="module-tree">
          <el-tree align="left" :data="treeResource" highlight-current :default-expanded-keys="[0]" node-key="id" ref="tree" @current-change="onCurrentNodeChange"></el-tree>
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
import { Dependencies } from "~/core/decorator";
import { webOrganizationService } from "~/server/services/enterprise-manage-services/ent-organization.service";
import { Prop, Emit, Watch } from "vue-property-decorator";

@Component({
  components: {
    DataForm,
    DataBox
  }
})
export default class ModulePermission extends Vue {
  @Dependencies(webOrganizationService) private webOrganizationService: webOrganizationService;
  @Emit("refreshList")
  refreshList() {}
  @Emit("close")
  close() {}
  @Emit("getDepartment")
  getDepartment(param) {}
  // 树所有资源
  private treeResource: Array<any> = [];
  // 当前选中的node的数据
  private nodeData: any = {};
  // 当前页面tree对象
  private tree: any = {};
  private submitLoading: Boolean = false;

  private dataSet: Array<any> = [];

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
  }

  refresh() {
    let orgId:any="";
    let userId:any="";
    let menuId:any="";
    this.webOrganizationService.getOrgByOrgId(orgId,userId,menuId).subscribe(
      data => {
         this.separateData(data);
      },
      ({ msg }) => {
        this.$message.error(msg);
      }
    );
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
    let tmpTreeResource = data.filter(x => [0,1].includes(x.orgType)).map(v => {
      return {
        label: v.orgName,
        pid: v.parentOrgId,
        id: v.orgId,
        type: v.orgType
      };
    });
  }
  /**
   * 取消操作
   */
  chooseTreeCancel() {
    this.nodeData=[];
    this.$emit("close","234234");
  }
  /**
   * 确定操作
   */
  chooseTreeCommit() {
    this.getDepartment(this.nodeData);
    // this.$parent.$parent.addParams.department=this.nodeData.label;
    // this.$parent.$parent.addParams.orgId=this.nodeData.id
    this.$emit("close");
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
