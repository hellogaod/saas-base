<template>
  <section class="component case-type-select">
    <el-checkbox-group v-model="checkList">
      <el-checkbox :label="1"  size="mini">内催</el-checkbox>
      <el-checkbox :label="2"  size="mini">委外</el-checkbox>
    </el-checkbox-group>
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Watch, Prop, Emit, Model } from "vue-property-decorator";

@Component({
  components:{}
})
export default class CaseTypeSelect extends Vue {
  @Model("updateCaseType") caseType;

  @Emit("updateCaseType")
  updateCaseType(type) {}
  @Emit("onCaseTypeChange")
  emitCaseTypeCahange(type) {}

  get checkList(){
    return this.caseType
  }

  set checkList(val){
    this.updateCaseType(val)
    this.emitCaseTypeCahange(val)
  }
}
</script>

<style lang="less">
</style>
