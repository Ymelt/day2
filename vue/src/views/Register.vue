<template>
  <div class="wrapper">
    <div style="margin: 100px auto; background-color: #fff; width: 350px; height: 400px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注 册</b></div>
      <!--      <el-form :model="user" :rules="rules" ref="userForm">-->
      <!--        <el-form-item prop="username">-->
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="account">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" placeholder="请输入账号" v-model="user.account"></el-input>
        </el-form-item>
        <el-form-item prop="name">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-edit" placeholder="请输入你的名字" v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" placeholder="请输入你的密码" show-password
                    v-model="user.password"></el-input>
        </el-form-item>
        <el-radio-group v-model="user.role">
          <el-radio :label="'管理员'" >管理员</el-radio>
          <el-radio :label="'老师'" >老师</el-radio>
          <el-radio :label="'学生'" >学生</el-radio>
        </el-radio-group>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/login')">登录界面</el-button>
          <el-button type="primary" size="small" autocomplete="off" @click="save">确定注册</el-button>
        </el-form-item>
      </el-form>
      <!--      </el-form>-->
    </div>
  </div>


</template>

<script>
export default {
  name: "Register",
  data(){
    return{
      user:{},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
      },
      role:9

    }
  },
  methods:{
    save(){
      this.request.post("/user/save",this.user).then(res =>{
        if(res.code == "500"){

          this.$message.error("账号已存在，请重新注册")
        }else if(res.code == "400"){
          this.$message.error("请不要空数据输入")
        }
        else{
          this.$message.success("保存成功")
        }
      })
    },

  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right,#42b983 ,#409EFF);
  overflow: hidden;
}
</style>