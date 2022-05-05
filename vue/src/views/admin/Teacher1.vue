<template>
  <div>
    <div style="padding:10px 0">
      <el-input style="width: 200px" v-model="tname"></el-input><el-button @click="load">搜索</el-button>
    </div>
    <div>
      <el-button type="primary" @click="addTeacher">新增老师</el-button>
    </div>

<!--    新增老师弹框-->
    <el-dialog title="你将注册一个老师账号加入系统" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="名字" label-width=50px>
          <el-input v-model="form.name"  autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="账户" label-width=50px>
          <el-input v-model="form.account"  autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width=50px>
          <el-input placeholder="密码默认为123456"  v-model="form.password" :disabled="true">
          </el-input>
        </el-form-item>
        <el-form-item label="身份" label-width=50px>
          <el-input placeholder="老师" v-model="form.role"  :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="realAdd">确 定</el-button>
      </div>
    </el-dialog>



    <!--    编辑老师信息弹框-->
    <el-dialog   :visible.sync="dialogTableVisible" style="width: 1200px">
      <el-form :model="box">
        <el-form-item label="名字" label-width=50px>
          <el-input v-model="box.name"   autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="账户" label-width=50px>
          <el-input v-model="box.account"  autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width=50px>
          <el-input   v-model="box.password"  show-password>
          </el-input>
        </el-form-item>
        <el-form-item label="身份" label-width=50px>
          <el-input placeholder="老师" v-model="box.role"  :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取 消</el-button>
        <el-button type="primary" @click="addModify">确 定</el-button>
      </div>
    </el-dialog>


    <el-table :data="tableData">
      <el-table-column prop="name" label="老师" width="900">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="info" round @click="modify(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" round>删除<i class="el-icon-remove-outline"></i></el-button>
        </template>
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
      </el-pagination></div>
  </div>
</template>

<script>
export default {
  name: "Teacher1.vue",
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
      tname:"",
      dialogFormVisible:false,
      form:{
        password:"123456",
        role:"老师"
      },
      dialogTableVisible:false,
      box:{
        role:"老师"
      }
      // tableData: Array(10).fill(item)
    }
  },
  created(){
    this.load()
  },
  methods:{
    load(){
      this.request.get("/admin/user/all" ,{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,
          teacherName :this.tname

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
    addTeacher(){
      this.dialogFormVisible = true
    },
    realAdd(){
      this.request.post("/admin/user/save",this.form).then(res => {
        this.$message.success("we are testing!!")
        if(res.code === "400"){
          this.$message.error("输入不能为空")
        }else if(res.code === "500"){
          this.$message.error("保存失败")
        }else{
          this.$message.success("保存成功")
        }
      })
    },
    select(){
      this.request.get("/admin/course/all").then(res => {
        this.tableData = res
      })
    },
    modify(row){
      this.box = row
      this.dialogTableVisible = true
    },
    addModify(){
      this.request.post("/admin/user/modify",this.box).then(res => {
        if(res.code === "400"){
          this.$message.error("输入不能为空")
        }else if(res.code === "500"){
          this.$message.error("修改失败")
        }else{
          this.$message.success("修改成功")
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