<template>
  <div  class="layout full" style="overflow:auto;">
    <router-view/>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
@Component({
  components: {}
})
export default class DefalutLayout extends Vue {
}
</script>

<style>

</style>
