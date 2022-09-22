<template>
    <section class="component edit-menu moduleAdd">
        <el-form :model="editModel" :rules="rules" ref="add-form" label-width="90px">
            <el-form-item label="父级菜单:" align="left" prop="parentName" v-show="parentMenu">
                <el-select  class="inpurClass" v-model="editModel.parentId" @change="changeParentName"> 
                    <el-option v-for="(item,index) in editModel.options" :key="index" :label="item.menuName" :value="item.id">{{item.menuName}}</el-option>               
                </el-select>
            </el-form-item>
            <el-form-item label="菜单类型:" align="left" prop="menuType">
                <el-select class="inpurClass" ref="menuType" v-model="editModel.menuType"  disabled>
                    <el-option label="菜单" :value=1></el-option>
                    <el-option label="按钮" :value=2></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="菜单名称" prop="menuName">
                <el-input v-model="editModel.menuName" :maxlength="10"></el-input>
            </el-form-item>
            <el-form-item label="数据脱敏:" align="left" prop="desensitizeStatus" v-if="editModel.menuType !==2" v-show="show">
                <el-select v-model="editModel.desensitizeStatus">
                    <el-option label="是" :value=1></el-option>
                    <el-option label="否" :value=0></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="权限分配:" align="left" prop="authStatus" v-show="show" v-if="editModel.menuType !==2">
                <el-select v-model="editModel.authStatus">
                    <el-option label="是" :value=1></el-option>
                    <el-option label="否" :value=0></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="菜单序列" prop="sequence">
                <el-input v-model="editModel.sequence" name="sequence" oninput="if(value.length>3)value=value.slice(0,3)" onkeyup="this.value=this.value.replace(/\s+/g,'')" type="number" min="1"></el-input>
            </el-form-item>
            <el-form-item label="菜单URL" prop="url" v-show="show">
                <el-input v-model="editModel.url" onkeyup="this.value=this.value.replace(/\s+/g,'')" maxlength="150"></el-input>
            </el-form-item>
            <el-form-item style="margin-top: 15px;position:relative" label="菜单图标:" prop="icon">
                <el-input placeholder="" v-model="editModel.icon" class="input-with-select inpurClass" readonly="">
                    <el-button slot="append" icon="el-icon-search" style="margin-bottom:0" @click="dialog.iconDialog = true"></el-button>
                </el-input>
                <i id="newIcon" :class="editModel.icon"></i>
            </el-form-item>
            <el-form-item label="描述" prop="remark">
                <el-col :span="18">
                    <el-input type="textarea" class="inpurClass" v-model="editModel.remark" :rows="3" :maxlength="50"></el-input>
                </el-col>
            </el-form-item>
            <el-form-item label-width="0px">
                <el-row type="flex" justify="center">
                    <el-button @click="close">取消</el-button>
                    <el-button @click="commit">确定</el-button>
                </el-row>
            </el-form-item>
        </el-form>
        <el-dialog title="选择图标" :append-to-body='true' class="iconDialog" v-model="editModel.icon" :center="true" :visible.sync="dialog.iconDialog" width="700px">
            <icon-dialog @close="dialog.iconDialog = false" ref="icon-dialog" @iconRefresh="iconRefresh"></icon-dialog>
        </el-dialog>
    </section>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import { Dependencies } from "~/core/decorator";
import { State, Getter } from "vuex-class";
import { Emit, Prop } from "vue-property-decorator";
import { ModuledetailService } from "~/services/manager-services/moduleDetail.service";
import { debug } from "util";
import iconDialog from "~/components/manager/system-manage/module-permission/icon-dialog.vue";
@Component({
    components: {
        iconDialog
    }
})
export default class AddModule extends Vue {
    @Dependencies(ModuledetailService)
    private moduledetailService: ModuledetailService;
    @State companyList: any;
    @State userData: any;
    @Emit("refreshList")
    refreshList() {}
    @Emit("close")
    close() {}
    private dialog: any = {
        iconDialog: false
    };
    private show: boolean = true;
    private flag: any = {};
    private parentMenu: boolean = false;
    private editModel: any = {
        menuName: "",
        menuType: "",
        desensitizeStatus: "0",
        authStatus: "0",
        sequence: "",
        url: "",
        remark: "",
        parentId: "",
        id: '',
        parentName: '',
        sysCode: "",
        status: 1,
        icon: "",
        options:[{
            parentName:''
        }]
    };
    private rules: any = {
        id: [
            { required: false, message: "请选择一项", trigger: "blur" }
        ],
        menuName: [
            { required: true, message: "请输入模块名称", trigger: "blur" }
        ],
        menuType: [{ required: true, message: "请选择一项", trigger: "blur" }],
        desensitizeStatus: [
            { required: true, message: "请选择一项", trigger: "blur" }
        ],
        authStatus: [
            { required: true, message: "请选择一项", trigger: "blur" }
        ],
        sequence: [{ required: true, message: "请选择一项", trigger: "blur" }],
        url: [
            { required: true, message: "不能为空", trigger: "blur" },
            {
                message: "请输入除汉字的字符",
                trigger: "blur",
                pattern: /^[^\u4e00-\u9fa5]{0,}$/
            }
        ]
    };
    reset() {
        let addForm: any = this.$refs["add-form"];
        addForm.resetFields();
    }
   //菜单类型切换
    // changeMenu(obj) {
    //     if (obj === 1) {
    //         this.show = false;
    //         this.editModel.desensitizeStatus = "0";
    //         this.editModel.authStatus = "0";
    //         this.rules.url[0].required = false;
    //     } else {
    //         this.rules.url[0].required = true;
    //         this.show = true;
    //         this.editModel.desensitizeStatus = "";
    //         this.editModel.authStatus = "";
    //     }
    // }
    //父菜单
    changeParentName(obj){
        this.editModel.parentId = obj;
        for(var i=0;i<this.editModel.options.length;i++){
            if(this.editModel.options[i].id===this.editModel.parentId){
                this.editModel.parentName=this.editModel.options[i].menuName
            }
        }
    }
    refresh(obj) {
        this.editModel.sysCode = this.$route.params.sysCode;
        if (obj.pid == "#") {
            this.parentMenu = false;
            this.show = false;
            this.flag=1
            this.rules.url[0].required = false;
        } else {
            this.parentMenu = true;
            this.show = true;
            this.rules.url[0].required = true;
            if(obj.menuType==2){
                this.flag=2
            }else{
                this.flag=1
            }
        }
        //获取一级菜单
        this.moduledetailService.getMenuById(obj.id).subscribe(
           data => {
                this.editModel.menuName=data.menuName;
                this.editModel.menuType=data.menuType;
                this.editModel.desensitizeStatus=data.desensitizeStatus;
                this.editModel.authStatus=data.authStatus;
                this.editModel.sequence=data.sequence;
                this.editModel.remark=data.remark;
                this.editModel.parentId=data.parentId;
                this.editModel.id=data.id;
                this.editModel.parentName=data.parentName;
                this.editModel.sysCode=data.sysCode;
                this.editModel.status=data.status;
                this.editModel.icon=data.icon;
                this.editModel.url=data.url;
                //获取父级级菜单
                this.moduledetailService.getOneMenu(this.flag, this.editModel.sysCode).subscribe(
                    data => {
                        this.editModel.options = data;
                        for(var i=0;i<this.editModel.options.length;i++){
                            if(this.editModel.options[i].id===this.editModel.parentId){
                                this.editModel.parentId=this.editModel.options[i].id
                            }
                        }
                    },
                    ({ msg }) => {
                        this.$message.error(msg);
                    }
                );
            },
            ({ msg }) => {
                this.$message.error(msg);
                }
            );
    }
    //新增
    commit() {
        let addForm: any = this.$refs["add-form"];
        addForm.validate(valid => {
            if (!valid) return false;
            this.moduledetailService.editMenu(this.editModel).subscribe(
                data => {
                    this.$message.success("修改成功!");
                    this.refreshList();
                    this.close();
                },
                ({ msg }) => {
                    this.$message.error(msg);
                }
            );
        });
    }
    iconRefresh(name) {
        this.editModel.icon = name;
    }
}
</script>

<style lang="less">
.moduleAdd .inpurClass,.moduleAdd .el-input,.moduleAdd el-select {
    width: 300px !important;
}
.moduleAdd .el-select .el-input .el-input--suffix input {
    width: 300px !important;
}
.moduleAdd .input-with-select .el-input__inner {
    background: #eee;
}
.moduleAdd .el-button--default {
    margin-top: -1px !important;
    padding: 0 10px !important;
    min-width: 50px !important;
}
.moduleAdd .v-modal {
    z-index: 2004 !important;
}
#newIcon {
    display: inline-block !important;
    position: relative;
    right: 80px;
    border: 1px solid #eee;
    padding: 5px;
}
</style>
