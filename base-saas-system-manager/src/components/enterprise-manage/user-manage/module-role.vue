<template>
  <section class="component module-role">
    <el-form :model="moduleRole" ref="add-form" label-width="100px" align="left">
      <el-row>
        <el-col :span="12">
          <label for="">角色：</label>
          <el-select v-model="roleId" placeholder="请选择" @change="selectRole()">
            <el-option
              v-for="item in roleOptions" :key="item.roleId" :label="item.roleName" :value="item.roleId">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="12">
          <el-form-item label="" prop="userName">
            <div style="text-align:right">用户:{{userData.realName}}</div>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="display:flex">
      <el-col class="module">
        <el-row class="title">菜单模块</el-row>
        <div class="module-tree">
          <el-tree align="left" :data="treeResource" highlight-current :default-expanded-keys="[0]" node-key="id"
                   ref="tree" @current-change="onCurrentNodeChange" @check="onCheck"></el-tree>
        </div>
      </el-col>
      <el-col class="moduleBox">
        <data-box :data="menuDataSet" @onPageChange="refreshData" ref="data-box">
          <template slot="columns">
            <!--数据列区域-->
            <el-table-column prop="menuName" label="菜单名称" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="parentName" label="上级菜单" :min-width="$helper.getColumnWidth(4)"
                             show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="menuType" label="菜单类型" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
              <template slot-scope="scope">
                <span v-if="scope.row.menuType === 1">菜单</span>
                <span v-if="scope.row.menuType === 2">按钮</span>
                <span v-if="scope.row.menuType === 3">组件</span>
              </template>
            </el-table-column>
            <el-table-column prop="status" label="状态" :min-width="$helper.getColumnWidth(2)">
              <template slot-scope="scope">
                <span v-if="scope.row.status === 1">启用</span>
                <span v-if="scope.row.status === 0">停用</span>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" :min-width="$helper.getColumnWidth(5)">
              <template slot-scope="scope">
                <span>{{$dateutils.dateTimeFormat(scope.row.createTime)}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" :min-width="$helper.getColumnWidth(5)">
              <template slot-scope="scope">
                <el-button type="text" v-if="scope.row.desensite === 0"
                           @click="addDataPermission(scope.row,1)">取消脱敏
                </el-button>
                <el-button type="text" v-if="scope.row.desensite === 1"
                           @click="addDataPermission(scope.row,0)">脱敏
                </el-button>
                <el-button type="text" @click="showTree(scope.row)"
                           v-if="scope.row.authStatus === 1">数据权限
                </el-button>
              </template>
            </el-table-column>
          </template>
        </data-box>
        <el-form :model="moduleRole" ref="add-form" label-width="100px" align="left">
          <el-form-item scope="scope" label-width="0px" align="center">
            <el-button @click="close">取消</el-button>
            <el-button @click="SaveCommit">确定</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <!--组织架构弹框-->
      <el-dialog title="组织架构树" :center="true" :visible.sync="treeShow" width="300px" append-to-body
                 @open="$nextTick(()=>$refs['department-tree-dialog'].refresh(Obj))">
        <department-tree @close="treeShow = false" ref="department-tree-dialog"></department-tree>
      </el-dialog>
    </div>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import DataForm from "~/components/common/data-form.vue";
  import DataBox from "~/components/common/data-box.vue";
  import {Dependencies} from "~/core/decorator";
  import {PageUtil} from "~/utils/page.util";
  import {entRoleMenuService} from "~/server/services/enterprise-manage-services/ent-role-menu.service";
  import {entRoleService} from "~/server/services/enterprise-manage-services/ent-role.service";
  import {entMenuService} from "~/server/services/enterprise-manage-services/ent-menu.service";
  import {sysUserPermissionService} from "~/server/services/enterprise-manage-services/ent-user-menu-desensite.service";
  import {entUserService} from "~/server/services/enterprise-manage-services/ent-user.service";
  import {Prop, Emit, Watch} from "vue-property-decorator";
  import {Getter, State} from "vuex-class";
  import DepartmentTree from "~/components/common/department-tree-checked.vue"; // 新增用户
  @Component({
    components: {
      DataForm,
      DataBox,
      DepartmentTree
    }
  })
  export default class ModulePermission extends Vue {
    @Dependencies(PageUtil) private pageUtil: PageUtil;
    @Dependencies(entRoleMenuService) private entRoleMenuService: entRoleMenuService;
    @Dependencies(entRoleService) private entRoleService: entRoleService;
    @Dependencies(entMenuService) private entMenuService: entMenuService;
    @Dependencies(sysUserPermissionService) private sysUserPermissionService: sysUserPermissionService;
    @Dependencies(entUserService) private entUserService: entUserService;

    @Emit("refreshList")
    refreshList() {
    }

    @State userData: any;

    @Emit("close")
    close() {
    }

    public moduleRole: any = {
      rolName: '',
    };
    private treeShow: any = false;
    private roleId: any = "";
    private Obj: any = {};
    private roleOptions: any = [];
    // 表格所有资源
    private tableResource: Array<any> = [];
    // 树所有资源
    private treeResource: Array<any> = [];
    // 当前选中的node的数据
    private nodeData: any = {};
    // 当前页面tree对象
    private tree: any = {};
    private submitLoading: Boolean = false;

    private dataSet: Array<any> = [];
    private menuDataSet: any = null;
    private userId: any = "";
    private orgId: any = "";

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
      this.refreshData(this.nodeData)
    }

    refresh(obj) {
      this.getRoleAll(obj);
      this.userId = obj.userId;
      this.orgId = obj.orgId;
    }

    getRoleAll(obj) {
      this.entRoleService.getRole(obj.userId).subscribe(
        data => {
          this.roleOptions = data.roleAllList
          if (data.userRoleId) {
            this.roleId = data.userRoleId;
          } else {
            this.roleOptions.unshift({
              roleId: '',
              roleName: '请选择'
            })
            this.roleId = "";
          }
          this.selectRole()
        }, ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    selectRole() {
      this.getMenuListByRoleId(this.roleId)
    }

    getMenuListByRoleId(roleId) {
      this.entRoleMenuService.getMenuListByRoleId({
        roleId: roleId,
        sysCode: this.$store.state.module.sysCode
      }).subscribe(
        data => {
          this.separateData(data);
        }, ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    // 处理丢失菜单权限的控件资源
    onCheck(data, {checkedKeys}) {
      // 子菜单点击
      if (data.type === 18) {
        let flag = checkedKeys.includes(data.id);
        this.tableResource
          .filter(x => x.parentId === data.id)
          .forEach(v => (v._checked = flag));
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
      if (data) {
        // 提取菜单资源
        let tmpTreeResource = data.filter(x => [1, 2, 3].includes(x.menuType)).map(v => {
          return {
            label: v.menuName,
            pid: v.parentId,
            id: v.menuId,
            type: v.menuType,
            entMenuId: v.entMenuId,
            menuId: v.menuId
          };
        });
      }
    }

    // 获取右侧菜单数据
    refreshData(nodeData) {
      let data: any = {
        parentId: nodeData.pid === "#" ? "" : nodeData.pid,
        entMenuId: nodeData.entMenuId,
        menuId: nodeData.menuId,
        userId: this.userId,
        sysCode: this.$store.state.module.sysCode,
        roleId: this.roleId
      }
      this.entMenuService.getMenuDetail(data).subscribe(data => {
        this.menuDataSet = data
      }, ({msg}) => {
        this.$message.error(msg);
      })
    }

    // 脱敏
    addDataPermission(scope, type) {
      let data: any = {
        "roleId": this.roleId,//角色id
        "userId": this.userId, //用户id
        "menuId": scope.id,//菜单 id
        "orgPath": "",//组织树ids
        "isDesensite": type ? type : 0, //1是0否
        "type": 2,//1-更新数据权限，2-更新脱敏
        "sysCode": this.$store.state.module.sysCode
      }
      this.sysUserPermissionService.addDataPermission(data).subscribe(data => {
        this.$message.success("操作成功!");
        this.refreshData(this.nodeData)
      }, ({msg}) => {
        this.$message.error(msg);
      })
    }

    showTree(row) {
      row.orgId = this.orgId;
      row.roleId = this.roleId;
      row.userId = this.userId;

      this.Obj = row;
      this.treeShow = true;
    }

    SaveCommit() {
      let data: any = {
        userId: this.userId,
        roleId: this.roleId
      };
      this.entUserService.updateUser(data).subscribe(data => {
        this.$message.success("保存成功!");
        this.refreshList();
        this.close();
      }, ({msg}) => {
        this.$message.error(msg);
      })
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
    flex-basis: 150px;
    border: 1px solid #e4e4e4;
    height: 480px;
    width: 150px;
  }

  .moduleBox {
    flex-basis: 700px;
    border: 1px solid #e4e4e4;
    height: 480px;
    width: 700px;
  }

  .module-tree {
    height: 440px;
    overflow: auto;
    -ms-overflow-style: none;
    overflow: -moz-scrollbars-none;
  }

  .module-tree::-webkit-scrollbar {
    width: 0px;
  }

  .el-form-item__label {
    text-align: left;
  }
</style>
