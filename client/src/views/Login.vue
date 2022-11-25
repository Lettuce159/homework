<template>
  <body id="poster">
  <el-form class="login-container" label-position="left" label-width="0px">
    <h3 class="login-title">欢迎登录
      <el-button @click="ToRegister()">点我注册</el-button>
    </h3>
    <el-form-item label="">
      <el-input type="text" v-model="loginForm.loginName" autocomplete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item label="">
      <el-input type="password" v-model="loginForm.password" autocomplete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="button" v-on:click="Login()" style="width: 100%;background: #505458; border: none;">登录
      </el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
  import Cookies from 'js-cookie'
  export default {
    name: 'Login',
    data() {
      return {
        loginForm: {
          loginName: '',
          password: ''
        }
      }
    },
    methods: {
      Login() {
        console.log(this.loginForm.loginName);
        console.log(this.loginForm.password);
        Cookies.set('username',this.loginForm.loginName)
        Cookies.set('oldpassword',this.loginForm.password)
        //console.log(Cookies.get('username'))
        this.axios({
          method:'POST',
          url:'/api/user/dologin',
          params:{
            username: this.loginForm.loginName,
            password: this.loginForm.password,
          }
        })
          .then(response => {
            if (response.data == "login successfully") {
              console.log(response.data)
              this.$router.push({path: '/Home'})
            } else {
              console.log(response.data)
            }
          })
          .catch(failResponse => {
          })
      },
      ToRegister() {
        this.$router.push({path: '/Register'})
      }
    }
  }
</script>

<style scoped>
  #poster {
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }

  body {
    margin: 0px;
    padding: 0px;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login-title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>
