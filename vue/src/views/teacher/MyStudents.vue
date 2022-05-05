<template>
  <div>
    <div style="padding:10px 0">
      <el-input style="width: 200px" v-model="studentName"></el-input><el-button @click="load">搜索</el-button>
      <el-button type="primary" @click="addNew">在班级里加入新学生</el-button>
    </div>
<!--    <div>-->

<!--    </div>-->


    <el-table :data="tableData" border>
      <el-table-column prop="gradeNumber" label="年级段" width="120">
      </el-table-column>
      <el-table-column prop="theClassNumber" label="班级" width="120">
      </el-table-column>
      <el-table-column prop="studentName" label="学生姓名" width="180">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
<!--          <el-button type="info" round @click="changethis(scope.row)">编辑<i class="el-icon-edit"></i></el-button>-->
          <el-button type="info" round @click="changeThis(scope.row)">修改<i class="el-icon-edit"></i></el-button>
          <el-button type="danger"  round @click="removeStudent(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>


    <!--    对班级学生管理-->
    <el-dialog title="学生管理" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="年级" >
          <el-input v-model="form.gradeNumber" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="班级" >
          <el-input v-model="form.theClassNumber" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="学生" :label-width="formLabelWidth">
          <el-select v-model="form.studentName" placeholder="请选择学生" clearable>
<!--            <el-option label="未安排" value="未安排"></el-option>-->
            <el-option
                v-for="item in studentOptions"
                :key="item"
                :label="item"
                :value="item"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addStudent">确 定</el-button>
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
  name: "MyStudents",
  data(){
    return{
      total:0,
      pageNum:1,
      pageSize:5,
      tableData:{},
      userName:"",
      dialogFormVisible:false,
      form:{
        gradeNumber:"",
        theClassNumber:""
      },
      studentOptions:{},
      studentName:"",

    }
  },
  created(){
    this.load();
    this.freeStudents();
  },
  methods:{
    load(){
        this.userName= JSON.parse(localStorage.getItem("user"))? JSON.parse(localStorage.getItem("user")).name : ""
        // console.log(userName)
        // this.userName = userName
        // console.log(this.userName)
        this.request.get("/atTeacher/myStudents" ,{
          params:{
            pageNum : this.pageNum,
            pageSize : this.pageSize,
            teacherName : this.userName,
            studentName : this.studentName
          }

        }).then(res => {
          console.log(res)
          this.tableData = res.records
          this.form.gradeNumber = this.tableData[0].gradeNumber
          this.form.theClassNumber = this.tableData[0].theClassNumber
          this.total = res.total
        })
    },
    removeStudent(row){
      this.request.get("/atTeacher/removeStudent?id="+row.id).then(res=>{
        if(res.code ==="200"){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
          this.load()
        }
      })
    },
    freeStudents(){
      this.request.get("/atTeacher/selectFreeStudent").then(res=>{
        this.studentOptions = res
      })
    },
    changeThis(row){
      this.dialogFormVisible = true
      this.form = row
    },
    addStudent(){
      this.request.post("/atTeacher/addStudent",this.form).then(res=>{
        if(res.code === "200"){
          this.$message.success("添加成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.error("添加失败")
        }
      })
    },
    addNew(){
      this.dialogFormVisible = true

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