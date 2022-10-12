<template>
  <section class="component data-editor">
    <!-- <editor id="editor_id" :height="height" :width="width" :content="editorText" :loadStyleMode="false"></editor> -->
    <quill-editor class="editor-example quill-editor bubble"
                  ref="myTextEditor"
                  :content="content"
                  :options="editorOption"
                  @change="onEditorChange($event)"
                  @blur="onEditorBlur($event)" @focus="onEditorFocus($event)">
    </quill-editor>

    <!-- 文件上传input 将它隐藏-->
    <el-upload class="upload-demo" :action="uploadUrl" :before-upload='beforeUpload' :data="uploadData"
               :on-success='uploadSuccess' :headers="headers"
               ref="upload" style="display:none">
      <el-button size="small" type="primary" id="imgInput" v-loading.fullscreen.lock="fullscreenLoading"
                 element-loading-text="插入中,请稍候">点击上传
      </el-button>
    </el-upload>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Prop, Watch, Emit} from "vue-property-decorator";
  import {NetService} from "~/utils/net.service";
  import {fileService} from "~/server/controller";
  import AppConfig from "~/config/app.config";
  import hljs from 'highlight.js';
  import {State} from "vuex-class";

  @Component({
    components: {}
  })
  export default class DataEditor extends Vue {
    @State userToken;

    @Emit("editorContent")
    editorContent(html, text) {
    }

    @Prop({default: ''})
    editorText: string;

    @Prop({default: '300px'})
    height: string;

    @Watch("editorText")
    editorTextChange() {
      this.content = this.editorText
    }

    public content: String = "";
    private editorOption: any = {
      modules: {
        toolbar: [
          ['bold', 'italic', 'underline', 'strike'],
          ['blockquote', 'code-block'],
          [{'header': 1}, {'header': 2}],
          [{'list': 'ordered'}, {'list': 'bullet'}],
          [{'script': 'sub'}, {'script': 'super'}],
          [{'indent': '-1'}, {'indent': '+1'}],
          [{'direction': 'rtl'}],
          [{'size': ['small', false, 'large', 'huge']}],
          [{'header': [1, 2, 3, 4, 5, 6, false]}],
          [{'font': []}],
          [{'color': []}, {'background': []}],
          [{'align': []}],
          ['clean'],
          ['link', 'image']
        ]
      }
    }

    private uploadData: any = {}
    private photoUrl: any = ''         // 上传图片地址
    private uploadType: any = ''    // 上传的文件类型（图片、视频）
    private fullscreenLoading: any = false
    private addRange: any = new Array()

    get editor() {
      return (this.$refs.myTextEditor as any).quill
    }


    get uploadUrl() {
      return [
        AppConfig.url.server,
        NetService.generateRequestUrl(
          fileService.fileUploadController.upload
        )
      ].join("/");
    }

    // 图片上传前
    qnUpload(file) {
      this.fullscreenLoading = true
    }

    // 获取上传文件头部信息
    get headers() {
      return {
        "X-UserToken": this.userToken
      };
    }


    // 图片上传之前调取的函数
    beforeUpload(file) {
      return this.qnUpload(file)
    }

    // 图片上传成功回调   插入到编辑器中
    uploadSuccess(res, file) {
      this.fullscreenLoading = false;
      let vm = this;
      let quill = (this.$refs.myTextEditor as any).quill;
      // console.log("上传成功回调地址：" + JSON.stringify(res));

      if (res != undefined && res.responseBody != undefined && res.responseBody.fileList.length > 0 && res.responseBody.fileList[0].Name) {  // 将文件上传后的URL地址插入到编辑器文本中
        let value = res.responseBody.fileList[0].Name;
        // 获取光标所在位置
        let selection = quill.getSelection(true).index;
        // 插入图片  res.info为服务器返回的图片地址
        quill.insertEmbed(selection ? selection : 0, 'image', value)
        // 调整光标到最后
        // quill.setSelection(length + 1)
      } else {
        (<any>this).$message.error(`${vm.uploadType}插入失败`)
      }
      (this.$refs['upload'] as any).clearFiles()    // 插入成功后清除input的内容
    }

    // 点击图片ICON触发事件
    imgHandler(state) {
      this.addRange = (this.$refs.myTextEditor as any).quill.getSelection()
      if (state) {
        let fileInput: any = document.getElementById('imgInput');
        fileInput.click() // 加一个触发事件
      }
      this.uploadType = 'image'
    }


    // 编辑器光标离开 将编辑器内容发射给父组件
    onEditorBlur(editor) {
      this.$emit('getValue', this.content)
    }

    // 编辑器获得光标
    onEditorFocus(editor) {
      editor.enable(true)   // 实现达到上限字符可删除
    }

    // 编辑器文本发生变化
    onEditorChange({editor, html, text}) {
      this.content = html
      this.editorContent(html, text)

    }

    // 清除编辑器内容
    callMethod() {
      this.content = ''
    }

    // 页面加载后执行 为编辑器的图片图标和视频图标绑定点击事件
    mounted() {
      // 为图片ICON绑定事件  getModule 为编辑器的内部属性
      (this.$refs.myTextEditor as any).quill.getModule('toolbar').addHandler('image', this.imgHandler)
    }

  }
</script>

<style lang="less">
  .data-editor {
    width: 100%;
    margin-bottom: 20px;
  }

  .ql-toolbar.ql-snow + .ql-container.ql-snow {
    border-top: 0px;
    padding-bottom: 10px;
  }

  .quill-code {
    border: none;
    height: auto;
  }

  .hljs {
    width: 100%;
    margin: 0;
    padding: 1rem;
    border: 1px solid #ccc;
    border-top: none;
    border-radius: 0;
    height: 100px;
    overflow-y: auto;
    resize: vertical;
    display: block;
    overflow-x: auto;
    background: #fff;
    color: #4d4d4c;
    word-break: break-all;
    word-wrap: break-word;
    box-sizing: border-box;
  }
</style>
