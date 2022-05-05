<template>
  <div>
    <div style="padding:10px 0">
      <el-input style="width: 150px" v-model="gradeNum" placeholder="年级段"></el-input>
      <el-input style="width: 150px" v-model="classNum" placeholder="班级"></el-input>

      <el-button @click="load">搜索</el-button>
    </div>
    <div>
      <el-button type="primary" @click="select">新增</el-button>
    </div>


    <el-table :data="tableData" border>
      <el-table-column prop="gradeNumber" label="年级" width="140">
      </el-table-column>
      <el-table-column prop="theClassNumber" label="班级" width="120">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="info" round @click="open(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" round>删除<i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>


    <!--    年级段管理-->
    <el-dialog title="年级段管理" :visible.sync="dialogFormVisible">
      <el-form :model="form" >
        <el-form-item label="删除当前年级段" label-width=140px>
          <el-input v-model="form.gradeNumber" style="width: 200px" :disabled="true"></el-input>
          <el-button type="danger">确定删除</el-button>
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
  name: "grade",
  data() {

    return {
      tableData:[],
      total:0,
      pageNum:1,
      pageSize:5,
      keyword:"",
      course:"",
      form:"",
      dialogFormVisible:false,
      gradeNum:"",
      classNum:""
      // tableData: Array(10).fill(item)
    }
  },
  created(){
    this.load()
  },
  methods:{
    load(){
      this.request.get("/admin/teacher/page" ,{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,
          gradeNumber : this.gradeNum,
          classNumber : this.classNum

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
    open(row){
      this.form = row
      this.dialogFormVisible =true
    },
    select(){
      this.request.get("/admin/course/all").then(res => {
        this.tableData = res
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