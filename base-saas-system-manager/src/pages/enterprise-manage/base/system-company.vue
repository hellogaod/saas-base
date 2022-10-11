<template>
  <!--消费金融-公司简介-->
  <section class="page system-company">
    <el-row>
      <el-col :span="24" align="right">
        <el-button class="saveBtn" @click="commit">保存</el-button>
      </el-col>
    </el-row>

    <data-editor :editorText="editorText" :height="height" @editorContent="editorContent"></data-editor>

  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Auth, Layout, Dependencies} from "~/core/decorator";
  import DataEditor from "~/components/common/data-editor.vue";
  import {companyProfile} from "~/server/services/enterprise-manage-services/companyProfile.service";

  @Layout('workspace')
  @Component({
    components: {
      DataEditor,
    }
  })
  export default class companyManage extends Vue {
    @Dependencies(companyProfile) private companyProfile: companyProfile;
    private editorText: any = "";
    private height: any = "400px";

//提交公司配置
    commit() {
      console.log(this.editorText)
      this.companyProfile.saveCompanyProfile(this.editorText).subscribe(
        data => {
          this.$message.success("保存成功!");
        }, ({msg}) => {
          this.$message.error(msg);
        }
      );
    }

    mounted() {
      this.refreshData();
    }

    refreshData() {
      this.companyProfile.getCompanyProfile({}).subscribe(data => {
        this.editorText = data.content;
      }, ({msg}) => {
        this.$message.error(msg);
      })
    }

    /**
     * 接收富文本编辑器的内容
     */
    editorContent(html, text) {
      this.editorText = html;
    }
  }
</script>

<style lang="less">
  .saveBtn {
    margin: 10px;
  }

  .system-company {
    width: 60%;
    margin: 0 auto;
    min-width: 0;
  }

  .system-company .quill-editor {
    height: 600px;
  }

  .system-company .ql-toolbar.ql-snow {
    height: 70px;
  }

  .system-company .ql-container {
    height: 530px;
  }

  .system-company .hljs {
    display: none;
  }
</style>
