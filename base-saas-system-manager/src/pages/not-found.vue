<template>
  <section class="page not-found">
  </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Layout,Dependencies } from "~/core/decorator";

@Layout('default')
@Component({
  components:{}
})
export default class NotFound extends Vue {

}
</script>

<style lang="less">
  .not-found{
    width: 100%;
    height: 100%;
    background: #fff url('../assets/images/common/pre-not-found.png') center no-repeat;
  }
</style>
