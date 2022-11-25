<template>
  <div id="poster">
    <el-form :model="ruleForm" ref="ruleForm" status-icon :rules="rules" label-width="0px" label-position="left"
             class="register-container">
      <h3 class="register-title">欢迎注册
        <el-button @click="ToLogin()">登录</el-button>
      </h3>
      <el-form-item label="" prop="LoginName">
        <el-input type="text" v-model="ruleForm.LoginName" placeholder="请输入用户名" autocomplete="off"
                  prefix-icon="el-icon-user-solid"></el-input>
      </el-form-item>
      <el-form-item label="" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off" placeholder="请输入密码"
                  prefix-icon="el-icon-lock"></el-input>
      </el-form-item>
      <el-form-item label="" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="请确认密码"
                  prefix-icon="el-icon-lock"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="button"  style="background: #505458;border: none;" @click="submitForm('ruleForm')">注册
        </el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "Register",
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          LoginName: '',
          password: '',
          checkPass: ''
        },
        rules: {
          LoginName: [
            {required: true, message: "请输入用户名", trigger: 'blur'}
          ],
          password: [
            {validator: validatePass, trigger: 'blur'}
            ],
          checkPass: [
            {validator: validatePass2, trigger: 'blur'}
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            this.$message({
              type: 'success',
              message: '注册成功'
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
       /* console.log(this.ruleForm.LoginName);
        console.log(this.ruleForm.password);
        console.log(this.ruleForm.checkPass);*/
      this.axios({
        method:'POST',
        url:'/api/user/doregister',
        params:{
          username: this.ruleForm.LoginName,
          password: this.ruleForm.password,
          checkPass: this.ruleForm.checkPass,
        }
      })
        .then(response => {
          if (response.data == "register successfully") {
            console.log(response.data)
            this.$router.push({path: '/'})
          } else {
            console.log(response.data)
          }
        })
        .catch(failResponse => {
        })
    },
      //重置
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      ToLogin() {
        this.$router.push({path: '/'})
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
    margin: 0px;
    padding: 0px;
    text-align: center;

  }

  .register-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .register-title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>
