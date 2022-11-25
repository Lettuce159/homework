<template>
  <div>
    <div class="avatar">
      <el-avatar icon="el-icon-user-solid"></el-avatar>
      <p>{{username}}</p>
    </div>
    <el-card>
      <el-form ref="editFormRef" :model="editForm" :rules="editFormRules">
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="editForm.newPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" prop="confirmPassword">
          <el-input v-model="editForm.confirmPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-card>


  </div>
</template>

<script>
  import Cookies from 'js-cookie'
  export default {
    name: "Home",
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入新密码'));
        } else {
          if (this.editForm.confirmPassword !== '') {
            this.$refs.ruleForm.validateField('confirmPassword');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入新密码'));
        } else if (value !== this.editForm.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        editForm: {
          newPassword: '',
          confirmPassword: ''
        },
        editFormRules: {
          newPassword: [
            {validator: validatePass, trigger: 'blur'}
          ],
          confirmPassword: [
            {validator: validatePass2, trigger: 'blur'}
          ],
        }
      };
    },
    computed: {
      username() {
        return Cookies.get('username')
      }
    },
    methods:{
//修改用户信息并提交
      save() {
        this.axios({
          method: 'POST',
          url: '/api/user/dochange',
          params: {
            username: Cookies.get('username'),
            /*oldpassword:Cookies.get('password'),*/
            newPassword: this.editForm.newPassword,
            confirmPassword: this.editForm.confirmPassword
          }
        })
          .then(response => {
            if (response.data == "change successfully") {
              this.$message.success("修改成功")
            } else {
              this.$message.error(res.msg)
            }
          })
      },
    }
  }
</script>

<style scoped>
.avatar{
  text-align: center;
  padding-top: 10px;
  padding-bottom: 20px;
}
</style>
