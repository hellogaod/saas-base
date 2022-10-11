<template>
  <!--消费金融-日志管理 登录日志查看-->
  <div>
    <section class="page system-view">
      <div v-html="data"></div>
    </section>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Auth, Layout, Dependencies} from "~/core/decorator";
  import {WeblogService} from "~/server/services/enterprise-manage-services/weblog.service";
  import {PageUtil} from "~/utils/page.util";
  import {Prop, Emit, Watch} from "vue-property-decorator";

  @Layout("workspace")
  @Component({
    components: {}
  })
  export default class Weblog extends Vue {
    @Dependencies(WeblogService) private WeblogService: WeblogService;
    @Dependencies(PageUtil) private pageUtil: PageUtil;

    @Emit("refreshList")
    refreshList() {
    }

    @Emit("close")
    close() {
    }

    private data: any = {};

    returnToPage(path) {
      this.$router.push(path)
    }

    refresh(obj) {
      let id = obj.id;
      this.WeblogService.getErrorLogById(id).subscribe(data => {
        this.data = data
        if (!data) {
          this.data = "<p style='text-align:center'>暂无内容</p>"
        }
      }, ({msg}) => {
        this.$message.error(msg);
      })
    }
  }
</script>

<style lang="less">
  .system-view div {
    padding: 0 15px 15px;
    font-size: 14px;
    line-height: 25px;
    overflow: auto;
    max-height: 500px;
  }

  .returnbtn {
    height: 30px;
    margin: 0 20px 10px 20px;
    text-align: left !important;
  }

  .returnbtn button {
    padding: 8px 5px !important;
  }
</style>
