<template>
  <section class="component add-other">
    <el-form :model="addModel" :rules="rules" ref="add-form" label-width="90px">
      <el-row>
        <el-col :span="16">
          <el-form-item label="三方名称" prop="otherName">
            <el-input v-model="addModel.otherName" :maxlength="20"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8" align="right">
          <el-button @click="addRow">添加</el-button>
        </el-col>
      </el-row>
      <el-form-item label="类型" align="left" prop="type">
        <el-radio-group v-model="addModel.type">
          <el-radio :label=1>三方配置</el-radio>
          <el-radio :label=0>基础配置</el-radio>
        </el-radio-group>
      </el-form-item>
      <div class="addRow">
        <el-row v-for="(item,index) in addModel.detailList" :key="index">

          <el-col :span="6">
            <el-form-item label="参数编码" align="left" :prop="'detailList.' + index + '.paraCode'"
                          :rules="addRules.paraCode">
              <el-input v-model="item.paraCode" :maxlength="20"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="参数名称" align="left" :prop="'detailList.' + index + '.paraName'"
                          :rules="addRules.paraName">
              <el-input v-model="item.paraName" :maxlength="20"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="4">
            <el-form-item label="排序值" align="left" :prop="'detailList.' + index + '.sortting'"
                          :rules="addRules.sortting">
              <el-input v-model="item.sortting" :maxlength="6"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="参数值" align="left" :prop="'detailList.' + index + '.paraValue'"
                          :rules="addRules.paraValue">
              <el-input v-model="item.paraValue" :maxlength="200"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="备注" align="left" :prop="'detailList.' + index + '.remark'" :rules="addRules.remark">
              <el-input v-model="item.remark" :maxlength="20"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1">
            <el-button style="margin-left:20px" v-if="index===0" disabled type="danger" size="mini"
                       icon="el-icon-delete" circle @click="deleteRow(index)"></el-button>
            <el-button style="margin-left:20px" v-if="index>0" type="danger" size="mini" icon="el-icon-delete" circle
                       @click="deleteRow(index)"></el-button>
          </el-col>
        </el-row>
      </div>
      <el-form-item label-width="0px" class="btns">
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
  import {SysOtherService} from "~/server/services/system-manage-services/sys-other.service";
  import {Emit} from "vue-property-decorator";

  @Component({
    components: {}
  })
  export default class AddOther extends Vue {
    @Dependencies(SysOtherService) private otherService: SysOtherService;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    private addModel: any = {
      otherId: '',
      otherName: "",
      type: 1,
      detailList: [{
        confId: '',
        paraCode: '',
        paraName: '',
        paraValue: '',
        sortting: '',
        remark: ''
      }]
    };
    private rules: any = {
      otherName: [{required: true, message: "请输入三方名称", trigger: "blur"},
        {message: '请输入汉字字母数字组合', trigger: 'blur', pattern: /^[A-Za-z0-9\u4e00-\u9fa5  _-]+$/}]
    };
    private addRules: any = {
      paraCode: [{required: true, message: "请输入参数编码", trigger: "blur"},
        {message: '请输入字母数字组合', trigger: 'blur', pattern: /^[A-Za-z0-9_-]+$/}],
      paraName: [{required: true, message: "请输入参数名称", trigger: "blur"},
        {message: '请输入汉字字母数字组合', trigger: 'blur', pattern: /^[A-Za-z0-9\u4e00-\u9fa5  _-]+$/}],
      paraValue: [{required: true, message: "请输入参数值", trigger: "blur"}],
      sortting: [{message: '请输大于0的数字', trigger: 'blur', pattern: /^[1-9]+$/}],
      remark: [{
        message: '请输入数字字母汉字组合',
        trigger: 'blur',
        pattern: /^[A-Za-z0-9\u4e00-\u9fa5,\.，。！!]+$/
      }]
    }

    reset() {
      let addForm: any = this.$refs["add-form"];
      addForm.resetFields();
      this.addModel.detailList = [{
        confId: '',
        paraCode: '',
        paraName: '',
        paraValue: '',
        sortting: '',
        remark: ''
      }];
    }

    commit() {
      let addForm: any = this.$refs["add-form"];
      addForm.validate(valid => {
        if (!valid) return false;
        this.otherService.saveOtherConfig(this.addModel).subscribe(
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

    created() {

    }

    addRow() {
      this.addModel.detailList.push({
        confId: '',
        paraCode: '',
        paraName: '',
        paraValue: '',
        sortting: '',
        remark: ''
      })
    }

    deleteRow(index) {
      this.addModel.detailList.splice(index, 1)
    }
  }
</script>

<style lang="less">
  .add-other {
    .el-input {
      width: 100% !important;
    }

    .el-checkbox-group {
      overflow: hidden;

      .el-checkbox {
        float: left;
        margin-left: 0;
        width: 40%;
      }
    }

    .btns {
      margin-top: 20px;
    }
  }

  .add-other .el-form-item__error {
    white-space: nowrap !important;
  }
</style>
