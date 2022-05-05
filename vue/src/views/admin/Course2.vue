<template>
  <div>
    <div style="padding:10px 0">
      <el-input style="width: 200px" v-model="course"></el-input><el-button @click="load">搜索</el-button>
    </div>
    <div>
      <el-button type="primary" @click="select">查看科目</el-button>
    </div>


    <el-table :data="tableData" border>
      <el-table-column prop="courseName" label="学科" width="140">
      </el-table-column>
      <el-table-column prop="courseTeacher" label="授课老师" width="120">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="info" round @click="changethis(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger"  round @click="open(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--    授课老师管理-->
    <el-dialog title="学科管理" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="学科科目" :label-width="formLabelWidth">
          <el-input v-model="form.courseName" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
<!--        <el-form-item label="授课老师" :label-width="formLabelWidth">-->
<!--          <el-input v-model="form.courseTeacher" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="授课老师" :label-width="formLabelWidth">
          <el-select v-model="form.courseTeacher" placeholder="请选择老师" clearable>
            <el-option label="未安排" value="未安排"></el-option>
            <el-option
                v-for="item in teacherOptions"
                :key="item.id"
                :label="item.name"
                :value="item.name"
            ></el-option>
          </el-select>


        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addTeacher">确 定</el-button>
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
  name: "Course2.vue",
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
      form:{},
      dialogFormVisible:false,
      teacherOptions:[]


      // tableData: Array(10).fill(item)
    }
  },
  created(){
    this.load();
    this.getTeachers();
  },
  methods:{
    load(){
      this.request.get("/admin/course/page" ,{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,
          courseName : this.course

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
    getTeachers(){
      this.request.get("/admin/course/OnlyTeacher").then(res=>{
        this.teacherOptions = res
      })
    },
    select(){
      this.request.get("/admin/course/all").then(res => {
        this.tableData = res
      })
    },
    changethis(row){
      this.form = row
      this.dialogFormVisible =true
    },
    addTeacher(){
      this.request.get("/admin/course/saveOnlyCourse?teacherName="+this.form.courseTeacher+"&courseName="+this.form.courseName).then(res=>{
        // +form.courseName+"&teacherName="+form.teacherName
        if(res.code === "400"){
          this.$message.error("请选择下拉框里的内容")
        }else if(res.code === "200"){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.error("保存失败")
        }

      })
    },
    open(row) {
      this.request.post("/admin/course/removeThisTeacher",row).then(res=>{
        if(res.code === "200"){
          this.$message.success("修改成功")
          this.load()
        }else{
          this.$message.success("修改失败")
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

  },
  // mounted(){
  //   this.getTeachers();
  // }
}
</script>

<style scoped>

</style>