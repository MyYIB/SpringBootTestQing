<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入学生姓名" suffix-icon="el-icon-search" v-model="username"></el-input>
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

    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="学生学号" width="220"></el-table-column>
      <el-table-column prop="username" label="学生姓名" width="220"></el-table-column>
      <el-table-column prop="nickname" label="手机号码" width="220"></el-table-column>
      <el-table-column prop="email" label="学生性别"></el-table-column>
      <el-table-column prop="email" label="班级"></el-table-column>
      <el-table-column label="操作" width="220" align="center">
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

    <el-dialog title="新增学生" :visible.sync="UserdialogForm" width="30%">
      <el-form :model="UserForm" >
        <el-form-item label="学生学号" :label-width="formLabelWidth">
          <el-input v-model="UserForm.no" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学生姓名" :label-width="formLabelWidth">
          <el-input v-model="UserForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学生电话" :label-width="formLabelWidth">
          <el-input v-model="UserForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学生性别" :label-width="formLabelWidth">
          <el-select v-model="UserForm.sex" placeholder="请选择">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学院" :label-width="formLabelWidth">
          <el-select v-model="UserForm.institution" placeholder="请选择">
            <el-option
                v-for="item in insList"
                :key="item.name"
                :label="item.name"
                :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级" :label-width="formLabelWidth">
          <el-select v-model="UserForm.class" placeholder="请选择">
            <el-option
                v-for="item in claList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
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
  name: "Teacher",
  data(){
    return {
      email: '', //  搜索的邮箱
      address: '',    //  搜索的地址
      UserdialogForm: false,  //  新增用户的表单
      multipleSelection: [],
      UserForm: {
        no: "",
        name: "",
        phone: "",
        sex:"",
        institution:"",
        class:""
      },
      formLabelWidth: '80px',
      tableData: [],
      total: 0,
      pageNum: 1, //当前第几页
      pageSize: 4,  // 当前页的数量
      username: "",
      headerBg:'headerBg',
      options:[{
        value: 1,
        label:'男'
      },{
        value: 2,
        label: '女'
      }],
      insList: [{name:'软件学院'}, {name:'材料学院'}],
      claList: [],

    }
  },
  methods:{
    load() {
      request.get("user/page", {
        params: {
          pageNum: this.pageNum,
          username: this.username,
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

  },
  created() {
    this.load();
  }
}
</script>
<style>

</style>