<template>
  <div>
    <div style="padding:10px 0">
      <el-input style="width: 200px" v-model="course"></el-input><el-button @click="load">搜索</el-button>
    </div>
    <div>
      <el-button type="primary" @click="load">查看所有老师</el-button>
      <el-button type="primary" @click="arrange">查看未安排课程老师</el-button>

    </div>


    <el-table :data="tableData" border>
      <el-table-column prop="name" label="老师" width="140">
      </el-table-column>
      <el-table-column prop="courseName" label="教授课程" width="120">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="info" round  @click="changethis(scope.row)"  >编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" round>删除<i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>


    <!--    老师授课科目管理-->
    <el-dialog title="学科管理" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="老师" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"  :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="教授课程安排" :label-width="formLabelWidth">
          <el-select v-model="form.courseName" placeholder="请选择课程" clearable>
            <el-option
                v-for="item in courseOptions"
                :key="item.courseName"
                :label="item.courseName"
                :value="item.courseName"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="changeCourse">确 定</el-button>
      </div>
    </el-dialog>




    <div  style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination></div>
  </div>
</template>

<script>
export default {
  name: "Teacher2.vue",
  data() {

    return {
      tableData:[],
      total:0,
      pageNum:1,
      pageSize:5,
      keyword:"",
      course:"",
      dialogFormVisible:false,
      form:{},
      courseOptions:[],
      // tableData: Array(10).fill(item)
    }
  },
  created(){
    this.load();
    this.getCourses();
  },
  methods:{
    load(){
      this.request.get("/admin/course/tandc" ,{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,
          teacherName : this.course


        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
      // request.get("http://localhost:9090/word/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&keyword="+this.keyword).then(res=>{
      //   console.log(res)
      // })

    },
    getCourses(){
      this.request.get("/admin/course/onlyCourse").then(res=>{
        this.courseOptions = res
      })
    },
    select(){
      this.request.get("/admin/course/all").then(res => {
        this.tableData = res
      })
    },
    arrange(){
      this.request.get("/admin/course/freeTeacher" ,{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,
          teacherName : this.course

        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    changethis(row){
      this.form = row
      this.dialogFormVisible =true
    },
    changeCourse(){
      this.request.post("/admin/course/saveOnlyTeach?courseName="+this.form.courseName+"&teacherName="+this.form.name).then(res=>{
        if(res.code === "200"){
          this.$message.success("修改成功")
          this.dialogFormVisible =false
        }else if(res.code === "400"){
          this.$message.error("若不给当前老师安排课程，请选择右边的删除按钮")
          this.dialogFormVisible =false
        }
        else{
          this.$message.error("修改失败")
        }
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

<style scoped>

</style>