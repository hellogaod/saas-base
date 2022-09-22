<template>
  <section class="component module-role">
    <div style="display:flex">
      <el-col class="module">
        <el-row class="title">公司及部门</el-row>
        <div class="module-tree">
          <el-tree align="left" show-checkbox :default-checked-keys="checkedData" :data="treeResource" highlight-current :default-expanded-keys="[0]" node-key="id" ref="tree"  @check="onCheck" @current-change="onCurrentNodeChange"></el-tree>
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
import { webOrganizationService } from "~/services/systemweb-services/organization.service";
import { sysUserPermissionService } from "~/services/systemweb-services/sysUserDataPermission.service";
import { Prop, Emit, Watch } from "vue-property-decorator";
import { Getter } from "vuex-class";
import { CommonService } from "~/utils/common.service";

@Component({
  components: {
    DataForm,
    DataBox
  }
})
export default class ModulePermission extends Vue {
  @Dependencies(webOrganizationService) private webOrganizationService: webOrganizationService;
  @Dependencies(sysUserPermissionService) private sysUserPermissionService: sysUserPermissionService;
  @Emit("refreshList")
  refreshList() {}
  @Emit("close")
  close() {}
  // 树所有资源
  private treeResource: Array<any> = [];
  // 当前选中的node的数据
  private nodeData: any = {};
  // 当前页面tree对象
  private tree: any = {};
  private submitLoading: Boolean = false;

  private dataSet: Array<any> = [];
  private checkedData:any=[];
  private data:any={};
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

  refresh(Obj) {
    this.checkedData=[];
    let orgId:any=Obj.orgId;
    this.data=Obj;
    this.webOrganizationService.getOrgByOrgId(orgId,Obj.userId,Obj.entMenuId).subscribe(
      data => {
        this.separateData(data);
      },
      ({ msg }) => {
        this.$message.error(msg);
      }
    );
  }
  // 处理丢失菜单权限的控件资源
  onCheck(data, { checkedKeys }) {
    this.checkedData=checkedKeys
    // 子菜单点击
    if (data.type === 1) {
      let flag = checkedKeys.includes(data.id);
      if (this.nodeData.id === data.id) this.onCurrentNodeChange(data);
    } else {
      data.children.forEach(item => this.onCheck(item, { checkedKeys }));
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
    let tmpTreeResource = data.filter(x => [0,1].includes(x.orgType)).map(v => {
      if(v.checkked===1){//选中
        this.checkedData.push(v.orgId)
      }
      return {
        label: v.orgName,
        pid: v.parentOrgId,
        id: v.orgId,
        type: v.orgType
      };
    });
    this.treeResource = CommonService.generateTreeData(tmpTreeResource);
  }
  /**
   * 取消操作
   */
  chooseTreeCancel() {
    this.nodeData=[];
    this.$emit("close");
  }
  /**
   * 确定操作
   */
  chooseTreeCommit() {
    let str:any="";
    let arr:any=[];
    for(var i=0;i<this.checkedData.length;i++){
      if(i===this.checkedData.length-1){
        str+="'"+this.checkedData[i]+"'"
      }else{
        str+="'"+this.checkedData[i]+"',"
      }
    }
    let data:any={
      "roleId":this.data.roleId,//角色id
      "userId":this.data.userId, //用户id
      "menuId":this.data.id,//菜单 id
      "orgPath":str,//组织树ids
      "isDesensite":this.data.desensite?this.data.desensite:0, //1是0否
      "type":1,//1-更新数据权限，2-更新脱敏
      "sysCode":this.$store.state.module.sysCode
    }
    this.sysUserPermissionService.addDataPermission(data).subscribe(data => {
      this.$message.success("操作成功!");

    }, ({ msg }) => {
      this.$message.error(msg);
    })
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
