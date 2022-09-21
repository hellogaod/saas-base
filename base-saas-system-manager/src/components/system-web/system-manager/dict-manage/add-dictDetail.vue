<template>
  <section class="component add-dict">
    <el-form :model="addModel" :rules="rules" ref="add-form" label-width="90px">
      <el-form-item label="父分类">
        <el-input v-model="addModel.itemParent" :maxlength="20" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="字典编码" prop="detailCode">
        <el-input v-model="addModel.detailCode" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="字典名称" prop="detailName">
        <el-input v-model="addModel.detailName" :maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="状态" align="left" prop="status">
        <el-radio-group v-model="addModel.status">
          <el-radio :label=1>启用</el-radio>
          <el-radio :label=0>停用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-col :span="18">
          <el-input type="textarea" v-model="addModel.remark" :rows="3" :maxlength="100"></el-input>
        </el-col>
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
import { Dependencies } from "~/core/decorator";
import { State, Getter } from "vuex-class";
import { webDictService } from "~/services/systemweb-service/dict.service";
import { Emit } from "vue-property-decorator";

@Component({
  components: {}
})
export default class AddBussiness extends Vue {
  @Dependencies(webDictService) private webDictService: webDictService;
  @State userData: any;
  @Emit("refreshList")
  refreshList() { }
  @Emit("close")
  close() { }

  private addModel: any = {
    itemParent: '',
    companyCode: this.$store.state.companyCode,
    detailCode: "",
    detailName: "",
    remark: '',
    status: 1,
    itemCode: '',
    id: ''
  };
  private rules: any = {
    detailCode: [{ required: true, message: "请输入字典编码", trigger: "blur" },{
            message: '请输入英文或数字',
            trigger: 'blur',
            pattern: /^[A-Za-z0-9]+$/
          }],
    detailName: [{ required: true, message: "请输入字典名称", trigger: "blur" },
          {
            message: '请输入数字字母汉字组合',
            trigger: 'blur',
            pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+$/
          }],
    remark: [{
      message: '请输入数字字母汉字组合',
      trigger: 'blur',
      pattern: /^[A-Za-z0-9\u4e00-\u9fa5,\.，。！!]+$/
    }]
  };
  reset() {
    let addForm: any = this.$refs["add-form"];
    addForm.resetFields();
  }
  refresh(obj) {
    console.log(obj)
    this.addModel.itemParent=obj.itemName
    this.addModel.itemCode=obj.itemCode
  }
  commit() {
    let addForm: any = this.$refs["add-form"];
    addForm.validate(valid => {
      if (!valid) return false;
      this.webDictService.saveDictDetail(this.addModel).subscribe(
        data => {
          this.$message.success("新增成功!");
          this.refreshList();
          this.close();
        }, ({ msg }) => {
          this.$message.error(msg);
        }
      );
    });
  }
  created() {
    
  }
}
</script>

<style lang="less" scoped>
.add-dict {
  .el-input{
    width: 80%!important;
  }
  .el-checkbox-group {
    overflow: hidden;
    .el-checkbox{
      float: left;
      margin-left: 0;
      width: 40%;
    }
  } 
}
</style>
