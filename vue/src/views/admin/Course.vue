<template>
  <div>
    <div style="padding:10px 0">
      <el-input style="width: 200px" v-model="course"></el-input><el-button @click="load">搜索</el-button>
    </div>
    <div>
      <el-button type="primary" @click="dialogFormVisible = true">新增科目</el-button>
    </div>


    <el-table :data="tableData">
      <el-table-column prop="courseName" label="学科" width="900">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
<!--          <el-button type="success"  @click="changethis(scope.row)">编辑<i class="el-icon-edit"></i></el-button>-->
          <el-button type="danger" round @click="dele(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--    授课老师管理-->
    <el-dialog title="新增学科" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="学科名称" :label-width="formLabelWidth">
          <el-input v-model="form.courseName" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="newone">确 定</el-button>
      </div>
    </el-dialog>

    <!--        删课警告-->
    <el-dialog  :visible.sync="dialogTableVisible">
      <el-form :model="table">
<!--        <el-form-item label="学科名称" :label-width="500">-->
<!--          <el-input v-model="form.courseName" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
        <br>当前课程有老师安排授课，你确定要删除吗

        <br>
        <b>我们建议你先管理好课程，将所有老师，班级与这门学科的联系断开</b>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="danger" @click="dele2">不顾一切得删除</el-button>
        <el-button @click="dialogTableVisible = false">取 消</el-button>

        <el-button  type="primary" @click="$router.push('/teacher2')">课程安排</el-button>
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
  name: "Course.vue",
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
      form:{
        courseTeacher:"未安排"
      },
      dialogFormVisible:false,
      dialogTableVisible:false,
      table:{},
      thisCourse:""


      // tableData: Array(10).fill(item)
    }
  },
  created(){
    this.load()
  },
  methods:{
    load(){
      this.request.get("/admin/course/all" ,{
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
    select(){
      this.request.get("/admin/course/all").then(res => {
        this.tableData = res
      })
    },
    dele(row){
      this.request.post("/admin/course/check?courseName="+row.courseName).then(res=>{
        if(res.code === "400"){
          this.$message.error("输入不能不空")
        }else if(res.code === "600"){
          this.table = row
          this.dialogTableVisible=true
        }else {
          this.request.post("/admin/course/decourse1?courseName="+row.courseName).then(res=>{
            if(res.code === "200"){
              this.$message.success("删除成功")
              this.load()
            }else{
              this.$message.error("删除失败")
            }
          })
        }
      })
    },
    dele2(){
      this.request.post("/admin/course/decourse?courseName="+this.table.courseName).then(res=>{
        if(res.code === "200"){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },

    newone(){
      this.request.post("/admin/course/savecourse",this.form).then(res =>{
        if(res.code === "400"){
          this.$message.error("输入不能不空")
        }else if(res.code === "500"){
          this.$message.error("系统错误，请重新尝试")
        }else if(res.code ==="600"){
          this.$message.error("学科已存在")
        }
        else{
          this.$message.success("添加成功")
          this.load()
          this.dialogFormVisible = false
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