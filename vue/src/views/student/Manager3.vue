<template>
  <div>
    <el-container style="height: 100%;">
      <el-aside width="200px" style="background-color: rgb(238,241,246); height:100%"   >
        <Aside3/>
      </el-aside>

      <el-container>

        <el-header style="text-align: right; font-size: 12px;border-bottom: 1px solid #ccc;line-height: 60px;">
          <Header :user="user"/>
        </el-header>





        <el-main>
          <router-view />
        </el-main>



      </el-container>
    </el-container>
  </div>
</template>

<script>

import Header from "@/components/Header";
import Aside3 from "@/components/Aside3";

export default {
  name: "Manager3",
  components:{
    Aside3,
    Header
  },
  data(){
    return{
      user: {name:"未登录"}
    }
  },
  created(){
    this.getUser();
  },
  methods:{
    getUser(){
      let account= JSON.parse(localStorage.getItem("user"))? JSON.parse(localStorage.getItem("user")).account : ""
      // ? JSON.parse(localStorage.getItem("user")).name : ""
      // console.log(username)
      // 从后台获取User数据
      this.request.get("/user/username?username="+ account).then(res => {
        // 重新赋值后台的最新User数据
        this.user = res.data
      })

    },
  }
}
</script>

