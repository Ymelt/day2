<template>
  <div>
    <div style="padding:10px 0">
      <el-input style="width: 200px" v-model="course"></el-input><el-button @click="load">搜索</el-button>
    </div>
    <div>
      <el-button type="primary" @click="select">新增</el-button>
    </div>


    <el-table :data="tableData" border>
      <el-table-column prop="gradeNumber" label="年级" width="140">
      </el-table-column>
      <el-table-column prop="theClassNumber" label="班级" width="120">
      </el-table-column>
      <el-table-column prop="classCourse" label="科目" width="120">
      </el-table-column>
      <el-table-column prop="courseTeacher" label="老师" width="120">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="info" round  @click="open(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" round>删除<i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>


    <!--    班级授课管理-->
    <el-dialog title="班级授课管理" :visible.sync="dialogFormVisible">
      <el-form :model="form" >
        <el-form-item label="删除当前班级" >
          <el-input v-model="form.theClassNumber" style="width: 200px" :disabled="true"></el-input>
          <el-button type="danger">确定删除</el-button>
        </el-form-item>
        <el-form-item label="科*********目" >
          <el-input v-model="form.classCourse" style="width: 200px" ></el-input>
        </el-form-item>
        <el-form-item label="老*********师" :label-width="formLabelWidth">
          <el-select v-model="form.courseTeacher" placeholder="请选择授课老师" clearable>
            <el-option
                v-for="item in Options"
                :key="item.id"
                :label="item"
                :value="item.courseTeacher"

            ></el-option>
          </el-select>
        </el-form-item>

<!--        v-for="item in Options"-->
<!--        :key="item.id"-->
<!--        :label="item.courseTeacher"-->
<!--        :value="item.courseTeacher"-->




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
  name: "TheClass1",
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
      Options:[],
      // tableData: Array(10).fill(item)
    }
  },
  created(){
    this.load();
    this.getCourses();
  },
  methods:{
    load(){
      this.request.get("/admin/teacher/page" ,{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,


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
    getCourses(){
      this.request.get("/admin/course/allData").then(res=>{

      })
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