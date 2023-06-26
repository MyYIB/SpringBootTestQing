<template>
    <div class="login-form">
      <el-header class="header" height="45px">
        <h2 class="title">登录</h2>
      </el-header>
      <div class="form">
        <el-form label-position="left" :model="loginForm"  status-icon :rules="rules" ref="loginForm">
          <el-form-item  prop="userAccount">
            <el-input v-model="loginForm.userAccount" autocomplete="on" placeholder="请输入帐号"
                      @keyup.enter.native="submitForm('loginForm')" prefix-icon="el-icon-user"></el-input>
          </el-form-item>
          <el-form-item prop="passWord" style="margin-bottom: 10px;">
            <el-input type="password" show-password v-model="loginForm.passWord" autocomplete="off" placeholder="请输入密码"
                      @keyup.enter.native="submitForm('loginForm')" prefix-icon="el-icon-lock"></el-input>
          </el-form-item>
          <el-form-item style="margin-bottom: 5px;">
            <el-checkbox v-model="rememberMe">记住我</el-checkbox>
            <label @click="findPassword" class="retrieve-password">找回密码</label>
          </el-form-item>
          <el-form-item style="margin-bottom: 10px;">
            <el-button type="primary" class="submit" v-preventReClick @click="login">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      //表单是否可见
      dialogForm:false,
      //记住我
      rememberMe: true,
      //登录表单
      loginForm: {
        userAccount: '',
        passWord: '',
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
    login(){
      this.$router.push('home');
    },
    findPassword(){
      this.$router.push('findPassword');
    }
  }
}
</script>

<style scoped>
.login-form .header {
  position: relative;
  text-align: left;
  border: none;
  padding: 0;
}

.login-form .header .title {
  margin: 0;
}


.login-form .form {
  text-align: left;
}

.login-form .form .retrieve-password {
  text-decoration: none;
  float: right;
  color: #005980;
}

.login-form .form .submit {
  width: 100%;
}

</style>