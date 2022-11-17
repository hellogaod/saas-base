<template>
  <section class="component modify-other">
    <el-form :model="modifyModel" :rules="rules" ref="modify-form" label-width="90px">
      <el-row>
        <el-col :span="18">
          <el-form-item label="三方名称" prop="otherName">
            <el-input v-model="modifyModel.otherName" :maxlength="20"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6" align="right">
          <el-button @click="modifyRow">添加</el-button>
        </el-col>
      </el-row>
      <el-form-item label="类型" align="left" prop="type">
        <el-radio-group v-model="modifyModel.type">
          <el-radio :label=1>三方配置</el-radio>
          <el-radio :label=0>基础配置</el-radio>
        </el-radio-group>
      </el-form-item>
      <div class="addRow">
        <el-row v-for="(item,index) in modifyModel.detailList" :key="index">
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

    private modifyModel: any = {
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
        {message: '请输入汉字字母数字组合', trigger: 'blur', pattern: /^[A-Za-z0-9\u4e00-\u9fa5  _-]+$/}],
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
      let modifyForm: any = this.$refs["modify-form"];
      modifyForm.resetFields();
    }

    refresh(obj) {
      this.modifyModel.otherId = obj.otherId
      this.otherService.getOtherConfigInfo(obj.otherId).subscribe(
        data => {
          this.modifyModel = data
        }, ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    commit() {
      let modifyForm: any = this.$refs["modify-form"];
      modifyForm.validate(valid => {
        if (!valid) return false;
        this.otherService.updateOtherConfig(this.modifyModel).subscribe(
          data => {
            this.$message.success("修改成功!");
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

    modifyRow() {
      this.modifyModel.detailList.push({
        confId: '',
        paraCode: '',
        paraName: '',
        paraValue: '',
        sortting: '',
        remark: ''
      })
    }

    deleteRow(index) {
      this.modifyModel.detailList.splice(index, 1)
    }
  }
</script>

<style lang="less">
  .modify-other {
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

  .modify-other .el-form-item__error {
    white-space: nowrap !important;
  }
</style>
