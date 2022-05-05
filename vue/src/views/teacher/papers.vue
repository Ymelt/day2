<template>
 <div>
   <el-table
       :data="tableData"
       border
       style="width: 100%">
     <el-table-column
         prop="paperId"
         label="试卷编号"
         width="180">
     </el-table-column>
     <el-table-column
         prop="paperCourse"
         label="试卷科目"
         width="180">
     </el-table-column>
     <el-table-column
         prop="paperName"
         label="试卷名字"
         width="180">
     </el-table-column>
     <el-table-column
         prop="paperAuthor"
         label="出卷老师"
         width="180">
     </el-table-column>
     <el-table-column
         prop="paperApplyClass"
         label="试卷适用班级">
     </el-table-column>
   </el-table>

   <div  style="padding: 10px 0">
     <el-pagination
         @size-change="handleSizeChange"
         @current-change="handleCurrentChange"
         :current-page="pageNum"
         :page-sizes="[2, 5, 10, 20]"
         :page-size="5"
         layout="total, sizes, prev, pager, next, jumper"
         :total="total">
     </el-pagination>
   </div>
 </div>


</template>

<script>
export default {
  name: "papers",
  data(){
    return{
      tableData:[],
      total:0,
      pageNum:1,
      pageSize:5,
      uName:"",
      course:[],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.uName= JSON.parse(localStorage.getItem("user"))? JSON.parse(localStorage.getItem("user")).name : ""
      this.request.get("/atTeacher/selectCourse?teacherName="+this.uName).then(res=>{
        this.course = res
        // console.log(this.test)
        this.request.get("/atTeacher/info/page", {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            courseName : this.course[0]

          }
        }).then(res => {
          console.log(res)
          this.tableData = res.records
          this.total = res.total
        })

      })



    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>

