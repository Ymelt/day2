<template>
  <div>
<!--    <el-dropdown style="width: 150px; cursor: pointer; text-align: right">-->
<!--      <div style="display: inline-block">-->
<!--        <img :src="user" alt=""-->
<!--             style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">-->
<!--        <span>{{ user.name }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>-->
<!--      </div>-->
<!--      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">-->

<!--        <el-dropdown-item style="font-size: 14px; padding: 5px 0">-->
<!--          <span style="text-decoration: none" @click="logout">退出</span>-->
<!--        </el-dropdown-item>-->
<!--      </el-dropdown-menu>-->
<!--    </el-dropdown>-->
    <el-dropdown>
      <i  style="margin-right: 15px">{{ user.name }}</i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>
          <router-link to="/login">登录</router-link>
        </el-dropdown-item>

        <el-dropdown-item  >
          <span style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header.vue",
  props:{
    user: {
      name:"未登录"
    }
  },
  methods:{
    logout(){
      localStorage.removeItem("user")
      sessionStorage.removeItem("token")
      this.$router.push("/login")
    }
  }

}
</script>

<style scoped>

</style>