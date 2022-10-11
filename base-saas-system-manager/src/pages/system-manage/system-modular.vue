<template>
  <!--消费金融-系统模块管理-->
  <div>
    <section class="page system-modular">
      <data-form :model="modularModel" @onSearch="refreshData">
        <template slot="default-input">
          <el-form-item label="模块名称" prop="sysName">
            <el-input v-model="modularModel.sysName"></el-input>
          </el-form-item>
        </template>
        <template slot="default-button">
          <el-button @click="dialog.add = true">添加</el-button>
        </template>
      </data-form>
      <data-box :data="modulDataSet" :page="pageUtil" @onPageChange="refreshData">
        <template slot="columns">
          <!--数据列区域-->
          <el-table-column prop="sysName" label="模块名称" :min-width="$helper.getColumnWidth(3)" show-overflow-tooltip>
          </el-table-column>
          <el-table-column prop="status" label="状态" :min-width="$helper.getColumnWidth(2)">
            <template slot-scope="scope">
              <span v-if="scope.row.status === 1">启用</span>
              <span v-if="scope.row.status === 0">停用</span>
            </template>
          </el-table-column>
          <el-table-column prop="remark" label="备注" :min-width="$helper.getColumnWidth(4)" show-overflow-tooltip>
          </el-table-column>
          <el-table-column prop="updateTime" label="最后更新时间" :min-width="$helper.getColumnWidth(4)">
            <template slot-scope="scope">
              <span>{{$dateutils.dateTimeFormat(scope.row.updateTime)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" :min-width="$helper.getColumnWidth(5)">
            <template slot-scope="scope">
              <el-button type="text" @click="dialog.modify = true, modulObj = scope.row">修改</el-button>
              <el-button type="text" @click="enableClick(scope)" v-if="scope.row.status === 0">启用</el-button>
              <el-button type="text" @click="disableClick(scope)" v-if="scope.row.status === 1">停用</el-button>
              <el-button type="text"
                         @click="goToPage('/sys-manage/modular-permission',scope.row.sysCode),modulObj = scope.row">业务功能
              </el-button>
            </template>
          </el-table-column>
        </template>
      </data-box>
      <!--添加模块-->
      <el-dialog title="添加模块" :center="true" :visible.sync="dialog.add" width="600px"
                 @open="$nextTick(()=>$refs['add-modular'].reset())">
        <add-modular @close="dialog.add = false" ref="add-modular" @refreshList="refreshData"></add-modular>
      </el-dialog>
      <!--编辑模块-->
      <el-dialog title="编辑模块" :center="true" :visible.sync="dialog.modify" width="600px"
                 @open="$nextTick(()=>$refs['modify-modular'].refresh(modulObj))">
        <modify-modular @close="dialog.modify = false" ref="modify-modular" @refreshList="refreshData"></modify-modular>
      </el-dialog>
    </section>
    <!--业务功能页面-->
    <div>
      <router-view/>
    </div>
  </div>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Auth, Layout, Dependencies} from "~/core/decorator";
  import {ModuleService} from "~/server/services/system-manage-services/module.service";
  import {PageUtil} from "~/utils/page.util";
  import DataForm from "~/components/common/data-form.vue";
  import DataBox from "~/components/common/data-box.vue";
  import addModular from "~/components/system-manage/system-modular/add-modular.vue";
  import modifyModular from "~/components/system-manage/system-modular/modify-modular.vue";

  @Layout('workspace')
  @Component({
    components: {
      DataForm,
      DataBox,
      addModular,
      modifyModular
    }
  })
  export default class sysModuleDetail extends Vue {
    @Dependencies(ModuleService) private moduleService: ModuleService;
    @Dependencies(PageUtil) private pageUtil: PageUtil;

    private modularModel: any = {
      sysName: '',
      status: '',
      updateTime: ''
    };
    private modulDataSet = null;
    private modulObj: any = {};
    private name: any = {};
    private dialog: any = {
      add: false,
      modify: false,
      module: false
    };

    mounted() {
      this.refreshData();
    }

    refreshData() {
      this.moduleService.getModuleList(this.modularModel, this.pageUtil).subscribe(data => {
        this.modulDataSet = data.list
      }, ({msg}) => {
        this.$message.error(msg);
      })
    }

    /**
     * 启用
     */
    enableClick(scope) {
      this.$confirm('确认启用吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.moduleService.updateStatus({status: '1', sysCode: scope.row.sysCode}).subscribe(data => {
          this.$message({
            type: 'success',
            message: '启用成功!'
          })
          this.pageUtil.reset()
          this.refreshData()
        }, ({msg}) => {
          this.$message.error(msg);
        })
      }).catch(() => {
      })
    }

    /**
     * 停用
     */
    disableClick(scope) {
      this.$confirm('确认停用吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.moduleService.updateStatus({status: '0', sysCode: scope.row.sysCode}).subscribe(data => {
          this.$message({
            type: 'success',
            message: '停用成功!'
          })
          this.pageUtil.reset()
          this.refreshData()
        }, ({msg}) => {
          this.$message.error(msg);
        })
      }).catch(() => {
      })
    }

    goToPage(path, obj) {
      this.$router.push('/sys-manage/modular-permission/' + obj);
    }
  }
</script>

<style lang="less">
  .returnbtn {
    height: 30px;
    margin: 0 20px 20px 20px;
    text-align: left !important;
  }

  .returnbtn button {
    padding: 8px 5px !important;
  }
</style>
