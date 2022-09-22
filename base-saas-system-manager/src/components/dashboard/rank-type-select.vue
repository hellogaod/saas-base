<template>
  <section class="component date-select">
    <el-select v-model="rankType" @change="onRankTypeChange"  size="mini">
      <el-option label="按回款金额排名" :value="0"></el-option>
      <el-option label="按回款案件数排名" :value="1"></el-option>
      <el-option label="按回款率排名" :value="2"></el-option>
    </el-select>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Layout, Dependencies } from "~/core/decorator";
import { Watch, Prop, Emit, Model } from "vue-property-decorator";

@Layout("workspace")
@Component({
  components: {}
})
export default class RankTypeSelect extends Vue {
  @Model("updateRankType")
  currentRankType;
 
  @Emit("updateRankType")
  updateRankType(date) {}
  
  @Emit("onRankTypeChange")
  emitRankTypeChange(date) {}

  get rankType() {
    return this.currentRankType;
  }

  set rankType(val) {
    this.updateRankType(val);
  }

  onRankTypeChange(){
    this.emitRankTypeChange(this.rankType);
  }
}
</script>

<style lang="less">

</style>
