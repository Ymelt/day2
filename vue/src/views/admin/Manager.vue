<template>
   <div>
     <el-container style="height: 100%;">
       <el-aside width="200px" style="background-color: rgb(238,241,246); height:100%"   >
        <Aside/>
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
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'HomeView',

  data() {

    // const item = {
    //   date: '2016-05-02',
    //   name: '王小虎',
    //   address: '上海市普陀区金沙江路 1518 弄'
    // };
    return {
      tableData:[],
      total:0,
      pageNum:1,
      pageSize:5,
      keyword:"",
      course:"",
      user: {name:"未登录"}

      // tableData: Array(10).fill(item)
    }
  },
  //请求分页查询数据
  created(){
    this.getUser();
  },
  components:{
    Aside,
    Header
  },
  methods:{
    // load(){
    //   this.request.get("/course/page" ,{
    //     params:{
    //       pageNum : this.pageNum,
    //       pageSize : this.pageSize,
    //       courseName : this.course
    //
    //     }
    //   }).then(res => {
    //     console.log(res)
    //     this.tableData = res.records
    //     this.total = res.total
    //   })
    //
    //
    // },
    // select(){
    //   this.request.get("/course/all").then(res => {
    //     this.tableData = res
    //   })
    // },
    // handleSizeChange(pageSize){
    //   console.log(pageSize)
    //   this.pageSize = pageSize
    //   this.load()
    // },
    // handleCurrentChange(pageNum){
    //   console.log(pageNum)
    //   this.pageNum = pageNum
    //   this.load()
    // }
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
