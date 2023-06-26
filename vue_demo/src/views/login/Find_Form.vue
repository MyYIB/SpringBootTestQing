<template>
  <div class="find-form">
    <el-header class="header" height="45px">
      <h2 class="title">找回密码</h2>
    </el-header>
    <div class="form">
      <el-form label-position="left" :model="findForm"  :rules="rules" label-width="100px" >
        <el-form-item  v-model="findForm.userAccount" label="账号" prop="userAccount" >
          <el-input  autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" >
          <el-input v-model="findForm.userPhone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="验证码" >
          <el-col span="10">
            <el-input v-model="findForm.code" width="300px" autocomplete="off"></el-input>
          </el-col>
          <el-col span="8">
            <el-button style="margin-left: 60px" v-preventReClick>获取验证码</el-button>
          </el-col>
        </el-form-item>
        <el-form-item style="margin-bottom: 10px;margin-left: 130px">
          <el-button >取消</el-button>
          <el-button type="primary"  v-preventReClick @click="">确定</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "FindPassword",
  data() {
    return{
      //表单是否可见
      dialogForm:false,
      //记住我
      rememberMe: true,
      findForm: {
        userAccount: '',
        userPhone: '',
        code:'',
        newPassword: '',
      },
      //表单的验证规则
      rules: {
        userAccount: [
          {required: true, message: '请输入帐号', trigger: 'blur'}
        ],
        passWord: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      },
    }
  },
  methods: {
    login() {
      this.$router.push('home');
    },
    findPassword() {
      this.$router.push('findPassword');
    }
  }
}
</script>

<style scoped>
.find-form .header {
  position: relative;
  text-align: left;
  border: none;
  padding: 0;
}

.find-form .header .title {
  margin: 0;
}


.find-form .form {
  text-align: left;
}

.find-form .form .retrieve-password {
  text-decoration: none;
  float: right;
  color: #005980;
}

</style>