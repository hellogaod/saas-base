<template>
  <svg class="svg-icon" aria-hidden="true" :style="iconStyle">
    <use :xlink:href="iconClass"></use>
  </svg>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Prop} from "vue-property-decorator";

  // 加载图标库文件
  const requireAll = requireContext => requireContext.keys().map(requireContext)
  const req = require.context('../../assets/icons', false, /\.svg$/)
  requireAll(req)

  @Component({
    components: {}
  })
  export default class SvgIcon extends Vue {
    @Prop({required: true})
    iconName: String;
    @Prop({default: 18})
    iconSize: String;
    @Prop() color: string;

    get iconClass() {
      return `#icon-${this.iconName}`;
    }

    get iconStyle() {
      let style: any = {
        fontSize: `${this.iconSize}px`
      }
      if (this.color) {
        style = {...style, fill: `${this.color}`}
      }
      return style
    }
  }
</script>

<style lang="less" scoped>
  .svg-icon {
    width: 1em;
    height: 1em;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
  }
</style>
