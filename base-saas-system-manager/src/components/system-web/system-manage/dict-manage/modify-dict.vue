<template>
  <section class="component modify-dict">
    <el-form :model="modifyModel" :rules="rules" ref="modify-form" label-width="90px">
      <el-form-item label="字典编码" prop="itemCode">
        <el-input v-model="modifyModel.itemCode" :maxlength="20" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="字典名称" prop="itemName">
        <el-input v-model="modifyModel.itemName" :maxlength="20"></el-input>
      </el-form-item>
      <!-- <el-form-item label="状态" align="left" prop="status">
        <el-radio-group v-model="modifyModel.status">
          <el-radio :label=1>启用</el-radio>
          <el-radio :label=0>停用</el-radio>
        </el-radio-group>
      </el-form-item> -->
      <el-form-item label="备注" prop="remark">
        <el-col :span="18">
          <el-input type="textarea" v-model="modifyModel.remark" :rows="3" :maxlength="100"></el-input>
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
import { webDictService } from "~/services/systemweb-services/dict.service";
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

  private modifyModel: any = {
    companyCode: this.$store.state.companyCode,
    itemCode: "",
    itemName: "",
    remark: '',
    status: 1,
    id: ''
  };
  private rules: any = {
    itemCode: [{ required: true, message: "请输入字典编码", trigger: "blur" },{
            message: '请输入英文或数字',
            trigger: 'blur',
            pattern: /^[A-Za-z0-9]+$/
          }],
    itemName: [{ required: true, message: "请输入字典名称", trigger: "blur" },
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
    let modifyForm: any = this.$refs["modify-form"];
    modifyForm.resetFields();
  }
  refresh(obj) {
    this.webDictService.getDictItemById(obj.id).subscribe(
      data => {
        this.modifyModel=data
      }, ({ msg }) => {
        this.$message.error(msg);
      }
    );
  }
  commit() {
    let modifyForm: any = this.$refs["modify-form"];
    modifyForm.validate(valid => {
      if (!valid) return false;
      this.webDictService.updateDictItem(this.modifyModel).subscribe(
        data => {
          this.$message.success("保存成功!");
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
.modify-dict {
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
