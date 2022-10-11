<template>
  <section class="component add-organization" @click="treeShow=false">
    <el-form :model="addModel" :rules="rules" ref="add-form" label-width="90px">
      <el-form-item label="类型" prop="orgType">
        <el-select v-model="addModel.orgType" @change="changeOrgType">
          <el-option v-for="{value,label} in orgTypeList" :key="value" :label="label" :value="value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="上级组织" prop="parentOrgName"
                    :rules="addModel.parentOrgName ? {required: false} : {required: true, message: '请选择上级组织', trigger: 'blur'}">
        <el-input v-model="addModel.parentOrgName" :readonly="true" @click.native.stop="toggleTreeShow"></el-input>
        <div class="tree-wrapper" v-show="treeShow">
          <el-tree ref="add_tree" :data="orgList" :props="defaultProps" @node-click="handleNodeClick"
                   :expand-on-click-node=false :filter-node-method="filterNode"></el-tree>
        </div>
      </el-form-item>
      <el-form-item :label="addModel.orgType === 0 ? '企业名称' : '组织名称'" prop="orgName">
        <el-input v-model="addModel.orgName" :maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="联系人" prop="orgManager">
        <el-input v-model="addModel.orgManager" :maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="联系人电话" prop="orgTel">
        <el-input v-model="addModel.orgTel" :maxlength="11"></el-input>
      </el-form-item>
      <el-form-item label="固定电话" prop="orgShortTel">
        <el-input v-model="addModel.orgShortTel" :maxlength="12"></el-input>
      </el-form-item>
      <el-form-item :label="addModel.orgType === 0 ? '企业状态' : '组织状态'" align="left" prop="status">
        <el-radio-group v-model="addModel.status">
          <el-radio :label=1>启用</el-radio>
          <el-radio :label=0>停用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label-width="0px">
        <el-row type="flex" justify="center">
          <el-button @click="close">取消</el-button>
          <el-button @click="commit">确定</el-button>
        </el-row>
      </el-form-item>
    </el-form>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Dependencies} from "~/core/decorator";
  import {webOrganizationService} from "~/server/services/enterprise-manage-services/organization.service";
  import {Emit, Watch} from "vue-property-decorator";

  @Component({
    components: {}
  })
  export default class AddBussiness extends Vue {
    @Dependencies(webOrganizationService) private webOrganizationService: webOrganizationService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    private orgTypeList: any = [{value: 1, label: '部门'}, {value: 0, label: '公司'}]
    private orgList: any = []//包括部门和公司
    private orgPathArr: Array<any> = []
    private defaultProps: any = {
      children: 'children',
      label: 'orgName'
    }
    private treeShow: boolean = false

    private addModel: any = {
      orgType: "",
      parentOrgName: '',
      parentOrgId: '',
      orgPath: '',
      orgName: "",
      orgManager: '',
      orgTel: '',
      orgShortTel: '',
      status: 1
    };

    private rules: any = {
      orgType: [{required: true, message: "请选择机构类型", trigger: "blur"}],
      orgName: [
        {required: true, message: "请输入组织名称", trigger: "blur"},
        {message: "请输入中文英文或数字", trigger: "blur", pattern: /^[\u4e00-\u9fa5a-zA-Z0-9]+$/}
      ],
      orgManager: [
        {required: true, message: "请输入联系人", trigger: "blur"},
        {message: "请输入中文英文或数字", trigger: "blur", pattern: /^[\u4e00-\u9fa5a-zA-Z0-9]+$/}
      ],
      orgTel: [
        {required: true, message: "请输入联系电话", trigger: "blur"},
        {message: "联系电话格式不正确", trigger: "blur", pattern: /^1(3|4|5|7|8)\d{9}$/}
      ],
      orgShortTel: [{message: "固定电话格式不正确", trigger: "blur", pattern: /0\d{2,3}-\d{7,8}/}]
    };

    reset() {
      let addForm: any = this.$refs["add-form"];
      addForm.resetFields();
      this.treeShow = false;
    }

    commit() {
      let addForm: any = this.$refs["add-form"];
      addForm.validate(valid => {
        if (!valid) return false;
        if (this.addModel.orgType === "") {
          this.$message.error('请选择机构类型');
          return false;
        }
        this.webOrganizationService.saveOrg(this.addModel).subscribe(
          data => {
            this.$message.success("新增成功!");
            this.refreshList();
            this.close();
          }, ({msg}) => {
            this.$message.error(msg);
          }
        );
      });
    }

    /**
     * 初始请求
     */
    refresh() {
      this.webOrganizationService.getOrgList().subscribe(data => {
        this.orgList = data;
      })
    }

    /**
     * 组织树点击赋值
     */
    handleNodeClick(data, node, me) {
      this.orgPathArr = [];
      this.addModel.parentOrgName = data.orgName;
      this.addModel.parentOrgId = data.orgId;
      this.recursionParent(node);
      this.treeShow = false;
    }

    /**
     * 获取所选的orgPath
     */
    recursionParent(node) {
      if (!node.parent) {
        return
      }
      this.orgPathArr.unshift(node.data.orgId)
      this.recursionParent(node.parent)
    }

    @Watch("orgPathArr")
    chooseOrgPath() {
      this.addModel.orgPath = this.orgPathArr.join('|');
    }

    /**
     * 点击上级组织 组织树显示隐藏
     */
    toggleTreeShow() {
      this.treeShow = !this.treeShow;
    }

    /**
     * 添加的类型切换
     * 文本以及树的数据需要更改
     */
    changeOrgType() {
      this.addModel.parentOrgName = '';
      this.addModel.parentOrgId = '';
      this.addModel.orgPath = '';
      (this.$refs.add_tree as any).filter(this.addModel.orgType);
    }

    /**
     * tree过滤
     * 选择公司 只保留公司级数据
     * 选择部门 保留公司级和部门级数据
     */
    filterNode(value, data) {
      if (value === 0) {//公司
        return data.orgType === 0;
      } else {
        return true
      }
    }
  }
</script>

<style>
  .add-organization .el-input, .add-organization .el-select, .add-organization .tree-wrapper {
    width: 80% !important;
  }

  .add-organization .el-select > .el-input {
    width: 100% !important;
  }

  .add-organization .tree-wrapper {
    position: absolute;
    border: 1px solid #CDD6D5;
    box-sizing: border-box;
    position: absolute;
    top: 31px;
    background: #fff;
    z-index: 50;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
    border-radius: 4px;
  }

  .add-organization .el-form-item__content {
    position: relative;
  }

</style>
