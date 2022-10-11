<template>
  <el-table
    :data="datas"
    border
    :row-style="showTr" class="treeGrid">
    <el-table-column v-for="(column, index) in columns" :key="column.dataIndex" :align="index === 0 ? 'left' : 'center'"
                     header-align="center"
                     :label="column.text">
      <template slot-scope="scope">
        <span v-if="spaceIconShow(index)" v-for="(space, levelIndex) in scope.row._level" :key="levelIndex"
              class="ms-tree-space"></span>
        <span class="expanded-button" v-if="toggleIconShow(index,scope.row)" @click="toggle(scope.$index)">
          <i v-if="!scope.row._expanded" class="el-icon-caret-right" aria-hidden="true"></i>
          <i v-if="scope.row._expanded" class="el-icon-caret-bottom" aria-hidden="true"></i>
        </span>
        <span v-else-if="index===0" class="ms-tree-space"></span>
        {{columns[index].dataIndex == 'orgType' || columns[index].dataIndex == 'status' ? '' :
        scope.row[column.dataIndex]}}
        <span v-if="columns[index].dataIndex == 'orgType'">{{scope.row.orgType == 1 ? '部门' : '公司'}}</span>
        <span v-if="columns[index].dataIndex == 'status'">{{scope.row.status == 1 ? '启用' : '停用'}}</span>
        <el-button v-if="columns[index].dataIndex == 'operation' && columns[index].btns.length > 0"
                   v-for="(btnItem, btnIndex) in columns[index].btns" :key="btnIndex"
                   size="mini"
                   :type=btnItem.type
                   @click="handleOperation(scope.row, btnIndex)">
          {{!btnItem.toggle ? btnItem.text : (scope.row.status == '1' ? '停用' : '启用')}}
        </el-button>
      </template>
    </el-table-column>
    <!-- <el-table-column label="操作" v-if="treeType === 'normal'" width="260">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="primary"
          @click="handleModify(scope.row)">
          修改
        </el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleStatus(scope.row.orgId, scope.row.status)">
         {{scope.row.status == '1' ? '停用' : '启用'}}
        </el-button>
      </template>
    </el-table-column> -->
  </el-table>
</template>
<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Emit, Prop, Watch} from "vue-property-decorator";
  import {DataTransferUtil} from "~/utils/datatransfer.util";

  @Component({
    components: {}
  })
  export default class TreeGrid extends Vue {

    @Prop({
      required: true,
      type: Array
    })
    dataSource

    @Prop({
      required: true,
      type: Array
    })
    columns

    private treeStructure: Boolean = true;//
    private treeType: String = 'normal';
    private defaultExpandAll: Boolean = true;
    private data: Array<any> = [];

    /**
     * 显示行
     */
    showTr(row, index) {
      row = row.row;
      let show = (row._parent ? (row._parent._expanded && row._parent._show) : true)
      row._show = show
      return show ? '' : 'display:none;'
    }

    /**
     * 展开下级
     */
    toggle(trIndex) {
      let me = this
      let record = me.data[trIndex]
      record._expanded = !record._expanded
    }

    /**
     * 显示层级关系的空格和图标
     */
    spaceIconShow(index) {
      let me = this
      if (me.treeStructure && index === 0) {
        return true
      }
      return false
    }

    /**
     * 点击展开和关闭的时候，图标的切换
     */
    toggleIconShow(index, record) {
      let me = this
      if (me.treeStructure && index === 0 && record.children && record.children.length > 0) {
        return true
      }
      return false
    }

    /**
     * 操作
     */
    handleOperation(obj, index) {
      this.$emit('handleOperation', obj, index)
    }

    mounted() {

    }

    // computed
    get datas() {
      var a = DataTransferUtil.treeToArray(this.dataSource, null, null, this.defaultExpandAll);
      this.data = a
      return a
    }
  }
</script>
<style>
  .treeGrid {
    margin: 0 20px;
    box-sizing: content-box;
    width: auto !important;
  }

  .ms-tree-space {
    position: relative;
    top: 1px;
    display: inline-block;
    font-family: 'Glyphicons Halflings';
    font-style: normal;
    font-weight: 400;
    line-height: 1;
    width: 16px;
    height: 14px;
  }

  .ms-tree-space::before {
    content: ""
  }

  .treeGrid .el-table th, .el-table tr {
    height: 35px;
    line-height: 35px;
  }

  .treeGrid.el-table td.is-left {
    padding-left: 10px !important;
  }
</style>
