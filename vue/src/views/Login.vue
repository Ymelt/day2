<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
<!--      <el-form :model="user" :rules="rules" ref="userForm">-->
<!--        <el-form-item prop="username">-->
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.account"></el-input>
<!--        </el-form-item>-->
<!--        <el-form-item prop="password">-->
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password
                    v-model="user.password"></el-input>
<!--        </el-form-item>-->
        <div style="margin: 10px 0; text-align: right">
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/register')">注册</el-button>
          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
        </div>
<!--      </el-form>-->
    </div>
  </div>


</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      user:{}
    }
  },
  methods:{
    login(){

          this.request.post("/user/login",this.user).then(res=>{
            if(res.code === "200"){
              this.$message.success("成功")
              sessionStorage.setItem("token",res.msg)
              localStorage.setItem("user",JSON.stringify(res.data))  // 存储用户信息到浏览器
              // JSON.stringify(res.data)
              if(res.data.role === "管理员"){
                this.$router.push("/")
              }else if (res.data.role === "老师"){
                this.$router.push("forTeacher/papers")
              }else if(res.data.role === "学生"){
                this.$router.push("forStudent/paperInfo")
              }
              // this.$router.push("/")
              }else{
                this.$message.error("错误")
              }
          })
    }
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