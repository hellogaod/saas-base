<template>
  <section class="component data-box">
    <div class="table">
      <el-table :row-style="rowStyle" :row-class-name="rowClass" :emptyText="emptyText" :height="tableHeight()"
                :max-height="maxHeight" tooltip-effect="dark" ref="table" :data="data" border stripe
                :highlight-current-row="selectionRow!=undefined" @current-change="onCurrentChange"
                @selection-change="onSelectionChange" @sort-change="onSortChange" @row-click="onRowClick">
        <el-table-column v-if="selectionList" type="selection" width="40">
        </el-table-column>
        <el-table-column v-if="!hiddenIndex" type="index" :label="indexLabel" width="50">
        </el-table-column>
        <slot name="columns"></slot>
      </el-table>
    </div>
    <div v-if="page&&data&&data.length>0" class="page row end-span">
      <el-pagination size="small" :layout="page.layout" :total="page.total" :current-page.sync="page.pageIndex"
                     :page-size="page.pageSize" @size-change="pageSizeChange"
                     @current-change="pageIndexChange"></el-pagination>
    </div>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Watch, Prop, Emit} from "vue-property-decorator";
  import {PageUtil} from "~/utils/page.util";
  import {SortUtil} from "~/utils/sort.util";

  @Component({
    components: {}
  })
  export default class DataBox extends Vue {
    // 数据列表
    @Prop() data: Array<any>;
    // 分页数据
    @Prop() page: PageUtil;
    // 分页数据
    @Prop() sort: SortUtil;
    // 选择列表
    @Prop() selectionList: any;
    // 选择项
    @Prop() selectionRow: any;
    @Prop({default: 528})
    maxHeight: number;
    @Prop() height: any;
    @Prop({type: Function})
    rowStyle: Function;

    @Prop({type: Function})
    rowClass: Function;

    @Prop({type: Boolean})
    hiddenIndex;

    @Prop({
      type: String,
      default: "序号"
    })
    indexLabel;

    // 分页信息改变消息
    @Emit("onPageChange")
    pageConfigChange(page) {
    }

    // 更新选择项列表
    @Emit("update:selectionList")
    updateSelectionList(list) {
    }

    // 更新当前选择项
    @Emit("update:selectionRow")
    updateSelectionRow(row) {
    }

    public table: any;

    // 监听当前选中行变化
    @Watch("selectionRow")
    onSelectionRowChange(val: string, oldVal: string) {
      if (this.table.store.states.currentRow !== val) {
        this.$nextTick(() => {
          this.table.setCurrentRow(val);
        });
      }
    }

    // 监听当前选中列表变化
    @Watch("selectionList")
    onSelectionListChanged(val: Array<any>, oldVal: Array<any>) {
      if (!val) {
        return;
      }
      //  等待data 渲染完毕之后再进行选中行赋值
      this.$nextTick(() => {
        // 待删除检测
        val.forEach(row => {
          let target = this.table.store.states.selection.find(x => x === row);
          if (!target) {

            this.table.toggleRowSelection(row, true);

          }
        });

        // 待删除检测
        this.table.store.states.selection.forEach(row => {
          let target = this.table.store.states.selection.find(x => x === row);
          if (!target) {
            this.table.toggleRowSelection(row, false);
          }
        });
      })
    }

    tableHeight() {
      if (this.height === "") {
        return this.maxHeight;
      } else {
        return this.height;
      }
    }

    get emptyText() {
      return this.data ? "暂无数据" : " ";
    }

    /**
     * 组件初始化
     */
    mounted() {
      this.table = <any>this.$refs["table"];
    }

    /**
     * 监听绑定数据变化
     */
    @Watch("data")
    onDataChanged(newVal: string, oldVal: string) {
    }

    /**
     * 页码数量变化回调
     */
    pageSizeChange(value) {
      /*
      *消除在切换页码数量 加载第一页的bug
      *此操作将加载当前页  从少到多 会传上次最大页面
      */
      // this.page.pageIndex = 1;
      this.page.pageSize = value;
      this.pageConfigChange(this.page);
    }

    /**
     * 页码位置变化回调
     */
    pageIndexChange(value) {
      this.page.pageIndex = value;
      this.pageConfigChange(this.page);
    }

    /**
     *  选择列表变化回调
     */
    onSelectionChange(list) {
      if (this.selectionList) {
        this.updateSelectionList(list);
      }
    }

    /**
     * 排序改变
     */
    onSortChange({column, prop, order}) {
      if (this.sort) {
        this.sort.update(prop, order);
        this.pageConfigChange(this.page);
      }
    }

    /**
     *  选择项变化回调
     */
    onCurrentChange(row) {
      this.updateSelectionRow(row);
    }

    /**
     *  清楚选择项
     */
    clearSelection() {
      this.table.clearSelection();
      this.table.setCurrentRow();
    }

    /**
     * 点击一行
     */
    onRowClick(row, event, column) {
      this.$emit('onRowClick', row)
    }
  }
</script>

<style lang="less" scoped>
  .data-box {
    & > * {
      padding: 10px;
    }
  }
</style>
<style lang="less">
  .data-box.component {
    .el-table {
      thead th {
        height: 26px;
        line-height: 26px;
        background-color: #f5f5f5;
        color: #8c8c8c;
        font-size: 12px;
        padding: 0;
        text-align: center;
      }
    }
  }
</style>
