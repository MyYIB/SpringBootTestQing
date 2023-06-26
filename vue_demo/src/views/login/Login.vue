<template>
  <div class="login-page">
    <div class="login-box">
        <div class="form-box">
          <router-view/>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
    }
  },

  methods: {},
  created() {

  }
}
</script>

<style scoped>
.login-page {
  width: 100%;
  height: 100vh;
  background: #5dd5c8 url(https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/bgimg.png) center bottom no-repeat;
}

.login-page .login-box {
  margin: 0;
  display: grid;
  grid-template-columns: 400px 1fr;
  position: absolute;
  width: 550px;
  height: 324px;
  padding: 0;
  background: #fff;
  box-shadow: 0 6px 13px 0 rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.login-page .login-box  .form-box {
  width: 90%;
  height: 310px;
  margin-top: 50px;
  margin-left: 100px;
}

</style>