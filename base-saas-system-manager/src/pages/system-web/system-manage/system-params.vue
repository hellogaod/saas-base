<template>
  <!--消费金融-系统参数配置-->
  <section class="page system-params">
    <div class="container ">
      <label class="headline" v-show="paramsModel.threeParams.length > 0">第三方支付</label>
      <div class="item-wrapper" v-for="item in paramsModel.threeParams" :key="item.otherName">
        <div class="otherTitle">
          <label>{{item.otherName}}</label>
          <button class="editBtn" @click="changeStatus(item)">{{item.editStatus ? '修改': '保存'}}</button>
        </div>
        <div class="content">
          <div class="form-group" v-for="itm in item.paramDetail" :key="itm.paraName">
            <label class="input-label"><p>{{itm.remark}}</p><p>{{itm.paraName}}:</p></label>
            <div class="input-wrapper">
              <el-input v-model="itm.paraValue" :disabled="item.editStatus"></el-input>
            </div>
          </div>
        </div>
      </div>
      <label class="headline" v-show="paramsModel.systemParams.length > 0">配置</label>
      <div class="item-wrapper" v-for="item in paramsModel.systemParams" :key="item.otherName">
        <div class="otherTitle">
          <label>{{item.otherName}}</label>
          <button class="editBtn" @click="changeStatus(item)">{{item.editStatus ? '修改': '保存'}}</button>
        </div>
        <div class="content">
          <div class="form-group" v-for="itm in item.paramDetail" :key="itm.paraName">
            <label class="input-label"><p>{{itm.remark}}</p><p>({{itm.paraName}}):</p></label>
            <div class="input-wrapper">
              <el-input v-model="itm.paraValue" :disabled="item.editStatus"></el-input>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Auth, Layout, Dependencies } from "~/core/decorator";
import { PageService } from "~/utils/page.service";
// import { Action, State, Mutation, Getter, namespace } from "vuex-class";
import { webSystemParamsService } from "~/services/systemweb-services/sysParams.service";

@Layout("workspace")
@Component({
  components: {}
})
export default class SystemManage extends Vue {
  @Dependencies(webSystemParamsService) private webSystemParamsService: webSystemParamsService;

  private paramsModel:any = {
    threeParams: [],
    systemParams: []
  }

  private labelPosition: string = 'right'

  mounted() {
    this.refreshData()
  }

  refreshData() {
    this.webSystemParamsService.getCompnaySysPara().subscribe(data => {
      data.forEach((item) => {
        item.editStatus = true
      })
      this.paramsModel.threeParams = data.filter(item => item.type == '1');//三方数据配置
      this.paramsModel.systemParams = data.filter(item => item.type == '0');//系统数据配置
    }, ({ msg }) => {
      this.$message.error(msg);
    })
  }
  /**
   * 修改/保存
   */
  changeStatus(item) {
    if (!item.editStatus) { //调保存接口
      this.saveSysPara(item)
      return
    }
    item.editStatus = !item.editStatus
  }
  /**
   * 保存
   */
  saveSysPara(item) {
    console.log(item)

    this.webSystemParamsService.saveSysPara(item).subscribe(data => {
      this.$message.success("保存成功");
      item.editStatus = true
    }, ({ msg }) => {
      this.$message.error(msg);
    })
  }

}
</script>

<style scoped>
.system-params .container{
  height: 100%;
  padding: 10px 20px;
  background: #F0F3F9;
}
.headline{
  height: 40px;
  line-height: 50px;
  font-size: 15px;
  font-weight: bold;
  color: #333;
}
.item-wrapper {
  padding:10px;
  background: #fff;
  box-shadow: 0 5px 5px #D5D8DC;
  border-bottom: 1px solid #c0c4cc;
}
.otherTitle{
  height: 40px;
  line-height: 40px;
  position: relative;
}
.otherTitle>label{
  font-size: 12px;
  font-weight: bold;
  color: #333;
}
.editBtn {
  position: absolute;
  top: 5px;
  right: 0px;
  width: 60px;
  height: 25px;
  line-height: 25px;
  border-radius: 5px;
  border: none;
  background: #4093ff;
  font-size: 14px;
  color: #fff;
}
.form-group {
  display: flex;
  height: 40px;
  line-height: 20px;
  margin-bottom: 15px;
}
.form-group .input-label {
    width: 350px;
    font-size: 14px;
    font-weight: 600;
    color: #333;
    text-align: right;
    padding-right: 20px;
    margin: 0;
}
.form-group .input-label p{
  margin: 0;
  padding: 0;
}
.form-group .input-wrapper {
  flex: 1;
}
.form-group .input-wrapper .el-input{
  width: 60%;
  height: 40px!important;
}
</style>
<style>
  .system-params .el-input .el-input__inner{
    height: 40px!important;
    line-height: 40px!important;
    font-size: 14px;
  }
  .system-params .el-input.is-disabled .el-input__inner{
    border-color:transparent;
    background: #fff;
    cursor: pointer;
    color:#606266;
  }
</style>

