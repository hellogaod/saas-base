<template>
  <section class="component data-form">
    <el-form ref="data-form" :inline="true" :rules="rules" label-position="left" :model="model">
      <div class="row" style="flex-wrap:nowrap;width:100%;">
        <div class="row middle-span col-span">
          <slot name="default-input"></slot>
          <slot name="collapse-input" v-if="showCollapseContext"></slot>
          <el-button v-if="!hiddenSearch" @click="submitForm" class="search-button" style="vertical-align:top">搜索
          </el-button>
          <el-button v-if="showResetButton" @click="resetForm" class="reset-button" style="vertical-align:top">重置
          </el-button>
          <div :style="{width:buttonWrap?'100%':'10px'}"></div>
          <slot name="default-button"></slot>
          <slot name="collapse-button" v-if="showCollapseContext">
            <span></span>
          </slot>
          <div class="col-span" v-show="showAppendContext">
          </div>
          <slot name="append">
          </slot>
        </div>
        <div class="row middle-span center-span collapse-icon" style="flex-basis:40px;" @click="changeCollapseHandle">
          <svg-icon :class="[showCollapseContext ? 'arrow-up':'arrow-down']" v-if="showCollapseIcon"
                    iconName="xiala"></svg-icon>
        </div>
      </div>
    </el-form>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Watch, Prop, Emit} from "vue-property-decorator";
  import {Form} from "element-ui";
  import {PageUtil} from "~/utils/page.util";
  import SvgIcon from "~/components/common/svg-icon.vue";

  @Component({
    components: {
      SvgIcon
    }
  })
  export default class DataForm extends Vue {
    // 验证规则
    @Prop() rules: any;
    // 数据模型
    @Prop() model: any;
    // 数据模型
    @Prop() page: PageUtil;

    //隐藏搜索按钮
    @Prop({
      type: Boolean,
      default: false
    })
    buttonWrap: boolean;

    //隐藏搜索按钮
    @Prop({
      type: Boolean,
      default: false
    })
    hiddenSearch: boolean;
    // 隐藏重置按钮
    @Prop({
      type: Boolean,
      default: false
    })
    hiddenReset: boolean;

    @Watch("rules")
    onRulesChange(val) {
    }

    @Emit("onSearch")
    onSearch() {
    }

    @Emit("handleResetForm") // 手动清空输入框
    handleResetForm() {
      this.submitForm()
    }

    private showCollapseIcon: boolean = false;
    private showResetButton: boolean = false;
    private showCollapseContext: boolean = false;
    private showAppendContext: boolean = false;

    /**
     * 初始化
     */
    mounted() {
      if (this.$slots["collapse-input"] || this.$slots["collapse-button"]) {
        this.showCollapseIcon = true;
      }
      if (this.$slots["collapse-input"] || this.$slots["default-input"]) {
        this.showResetButton = !this.hiddenReset && true;
      }
      if (this.$slots["append"]) {
        this.showAppendContext = true;
      }
    }

    /**
     * 提交输入表单
     */
    submitForm() {
      let dataForm = <Form>this.$refs["data-form"];
      dataForm.validate(success => {
        if (!success) {
          return;
        }

        if (this.page) {
          this.page.reset();
        }

        this.onSearch();
      });
    }

    /**
     * 重置输入表单
     */
    resetForm() {
      let dataForm = <Form>this.$refs["data-form"];
      dataForm.resetFields();
      this.handleResetForm();
    }

    /**
     * 折叠状态改变
     */
    changeCollapseHandle() {
      if (this.showCollapseIcon) {
        this.showCollapseContext = !this.showCollapseContext;
      }
    }
  }
</script>

<style lang="less" scoped>
  .component.data-form {
    padding: 0 10px;
  }

  .arrow-down {
    transform: rotate(0deg);
    transition: transform ease-in 0.2s;
  }

  .arrow-up {
    transform: rotate(180deg);
    transition: transform ease-in 0.2s;
  }

  .collapse-icon {
    height: 30px;
  }
</style>
<style lang="less">
  .component.data-form {
    .el-radio-group {
      padding-left: 10px;
    }
  }
</style>
