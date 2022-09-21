<template>
  <section class="component config-business">
    <el-form :model="configModel" ref="config-form" label-width="90px">
      <el-form-item label="三方配置:">
        <el-checkbox-group v-model="configModel.otherList">
          <el-checkbox v-for="item in configModel.ConfigList" :key="item.id" :label="item.id" v-if="item.type===1">{{item.otherName}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="系统配置:">
        <el-checkbox-group v-model="configModel.otherList">
          <el-checkbox v-for="item in configModel.ConfigList" :key="item.id" :label="item.id" v-if="item.type===0">{{item.otherName}}</el-checkbox>
        </el-checkbox-group>
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
// import { State, Getter } from "vuex-class";
import { OtherService } from "~/services/manager-service/other.service";
import { EnterpiseService } from "~/services/manager-service/enterprise.service";
import { Emit } from "vue-property-decorator";

@Component({
  components: {}
})
export default class ConfigBusiness extends Vue {
  @Dependencies(OtherService) private otherService: OtherService;
   @Dependencies(EnterpiseService) private enterpiseService: EnterpiseService;
  @Emit("refreshList")
  refreshList() {}
  @Emit("close")
  close() {}

  private configModel: any = {
    ConfigList: [],
    otherList: [],
    companyCode: ''
  };
  reset() {
    this.configModel.ConfigList=[];
    this.configModel.otherList=[];
  }
  refresh(obj) {
    this.configModel.companyCode=obj.companyCode;
    this.otherService.getCompanyConfigList(obj.companyCode).subscribe(
      data => {
        this.configModel.ConfigList=data
        let arr:any=[];
        for(var i=0;i<data.length;i++){
          if(data[i].checked){
            arr.push(data[i].id)
          }
        }
        this.configModel.otherList=arr
      }, ({ msg }) => {
        this.$message.error(msg);
      }
    );
  }
  commit() {
    let otherList:any=[]
    this.configModel.ConfigList.forEach(c => {
      this.configModel.otherList.forEach(o => {
        if(o===c.id){
          otherList.push({
            id: c.id,
            name: c.otherName
          })
        }
      });
    });
    let data={
      companyCode: this.configModel.companyCode,
      otherList: otherList
    }
    this.enterpiseService.updateCompanyOtherConf(data).subscribe(
      data => {
        this.$message.success("修改成功!");
        this.refreshList();
        this.close();
      }, ({ msg }) => {
        this.$message.error(msg);
      }
    );
  }
  mounted() {}
}
</script>

<style lang="less" scoped>
.config-business {
  .el-input{
    width: 80%!important;
  }
  .el-checkbox-group {
    overflow: hidden;
    .el-checkbox{
      float: left;
      margin-left: 0;
      width: 40%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  } 
}
</style>
