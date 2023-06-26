<template>
<div>
  <div style="margin: 10px 0">
    <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username"></el-input>
    <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email"
              class="ml-5"></el-input>
    <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address"
              class="ml-5"></el-input>
    <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    <el-button type="warning" class="ml-5" @click="reset()">重置</el-button>
  </div>

  <div style="margin: 10px 0">
    <el-button type="primary" @click="addNewUser">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
    <el-popconfirm
        class="ml-5"
        confirm-button-text='确定'
        cancel-button-text='我再想想'
        icon="el-icon-info"
        icon-color="red"
        title="您确定批量删除这些数据吗？"
        @confirm="delBatch"
    >
      <el-button type="danger" slot="reference" >批量删除 <i class="el-icon-remove-outline"></i></el-button>
    </el-popconfirm>
    <el-upload action="http://localhost:9090/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
      <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
    </el-upload>
    <el-button type="primary" @click="exportExcel" class="ml-5">导出 <i class="el-icon-top"></i></el-button>
  </div>

  <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="id" label="ID" width="80"></el-table-column>
    <el-table-column prop="username" label="用户名" width="140"></el-table-column>
    <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
    <el-table-column prop="email" label="邮箱"></el-table-column>
    <el-table-column prop="phone" label="电话"></el-table-column>
    <el-table-column prop="address" label="地址"></el-table-column>
    <el-table-column label="操作" width="200" align="center">
      <template slot-scope="scope">
        <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='我再想想'
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除吗？"
            @confirm="del(scope.row.id)"
        >
          <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <div style="padding: 10px 0">
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[4, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>

  <el-dialog title="新增用户" :visible.sync="UserdialogForm" width="30%">
    <el-form :model="UserForm" >
      <el-form-item label="用户名" :label-width="formLabelWidth">
        <el-input v-model="UserForm.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称" :label-width="formLabelWidth">
        <el-input v-model="UserForm.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" :label-width="formLabelWidth">
        <el-input v-model="UserForm.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话" :label-width="formLabelWidth">
        <el-input v-model="UserForm.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址" :label-width="formLabelWidth">
        <el-input v-model="UserForm.address" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="UserdialogForm = false">取 消</el-button>
      <el-button type="primary" @click="newUser()">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "User",
  data(){
    return {
      email: '', //  搜索的邮箱
      address: '',    //  搜索的地址
      UserdialogForm: false,  //  新增用户的表单
      multipleSelection: [],
      UserForm: {
        username: "",
        nickname: "",
        email: "",
        phone: "",
        address: ""
      },
      formLabelWidth: '80px',
      tableData: [],
      total: 0,
      pageNum: 1, //当前第几页
      pageSize: 4,  // 当前页的数量
      username: "",
      headerBg:'headerBg'
    }
  },
  methods:{
    load() {
      request.get("user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      })
          .then(res => {
            console.log(res)
            this.tableData = res.data
            this.total = res.total
          })
    },
    newUser() {
      request.post("user/addNewUser", this.UserForm).then(res => {
        if (res.code == 200) {
          this.$notify({title: '成功', message: '添加或修改用户成功', type: 'success'});
          this.UserdialogForm = false
          this.load()
        } else {
          this.$notify({title: '失败', message: '添加或修改用户失败', type: 'error'});
        }
      })
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    reset() {
      this.email = ''
      this.address = ''
      this.username = ''
      this.load()
    },
    addNewUser() { //  添加新用户
      this.UserdialogForm = true
      this.UserForm = {}
    },
    handleEdit(row) {  //  修改数据
      this.UserForm = Object.assign({}, row)
      this.UserdialogForm = true
    },

    del(id) {   //  删除单个数据
      request.get("user/delete" + "?id=" + id).then(res => {
        if (res) {
          this.$notify({title: '删除成功', message: '删除用户成功', type: 'success'});
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {  //  批量删除
      let ids = this.multipleSelection.map(v => v.id)
      request.post("user/delBatch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    exportExcel(){   //  导出文件
      window.open("http://localhost:9090/user/export")
    },
    handleExcelImportSuccess() {  //  导入文件
      this.$notify.success("导入文件成功")
      this.load()
    }
  },
  created() {
    this.load();
  }
}
</script>
<style>
.headerBg {
  background: #eee !important;
}
</style>