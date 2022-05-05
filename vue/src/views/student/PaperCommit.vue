<template>
<div>
  <template>-->
    <el-select v-model="allId" placeholder="请选择试卷号">
      <el-option
          v-for="item in Ids"
          :key="item"
          :label="item"
          :value="item">
      </el-option>
    </el-select>
  </template>
  <el-button type="primary" round @click="modules">查询试卷<i class="el-icon-edit"></i></el-button>

  <el-table :data="form" border>
    <el-table-column prop="paperModule" label="试卷模块" width="140">
    </el-table-column>

    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button type="info" round @click="open(scope.row)">查看模块下的题目<i class="el-icon-edit"></i></el-button>
        <el-button type="danger"  round @click="removeModule(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
      </template>
    </el-table-column>
  </el-table>


  <el-dialog title="题目" :visible.sync="dialogTitleVisible">
    <el-form ref="onlyTitle" :model="onlyTitle" label-width="150px" >
      <el-form-item label="试卷号" style="width: 600px">
        <el-input v-model="onlyTitle.paperId"></el-input>
      </el-form-item>
      <el-form-item label="题号" style="width: 600px">
        <el-input v-model="onlyTitle.paperTitleId"></el-input>
      </el-form-item>
      <el-form-item label="题目模块" style="width: 300px">
        <el-input v-model="onlyTitle.paperModule"></el-input>
      </el-form-item>
      <el-form-item label="题型" style="width: 500px">
        <el-input v-model="onlyTitle.paperTitleType"></el-input>
      </el-form-item>
      <el-form-item label="题目内容" style="width: 500px">
        <el-input v-model="onlyTitle.paperTitle"></el-input>
      </el-form-item>
      <el-form-item label="题目答案" style="width: 500px">
        <el-input v-model="onlyTitle.paperTitleAnswer"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="commitTitle">提交</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>


  <el-drawer
      title="我是标题"
      :visible.sync="drawer"
      :with-header="false"
      size="75%">
    <el-table :data="tableData" border>
      <el-table-column prop="paperTitleId" label="题号" width="80">
      </el-table-column>
      <el-table-column prop="paperTitleType" label="题目类型" width="140">
      </el-table-column>
      <el-table-column prop="paperTitle" label="题目" width="200">
      </el-table-column>
      <el-table-column prop="paperStudentAnswer" label="我的答案" width="200">
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="info" round @click="choices(scope.row)">查看选择题的选项<i class="el-icon-edit"></i></el-button>
          <el-button type="success"  round @click="toContent(scope.row)">答题按钮<i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-drawer
        title="选择内容"
        :append-to-body="true"
        :before-close="handleClose"
        :visible.sync="innerDrawer">

      <el-table :data="innerData" border>
        <el-table-column prop="paperTitleOption" label="选项" width="70">
        </el-table-column>
        <el-table-column prop="paperOptionContent" label="内容" >
        </el-table-column>
      </el-table>
    </el-drawer>
  </el-drawer>

  <!--    答题弹出框-->
  <el-dialog title="答题框" :visible.sync="dialogChoiceVisible">
    <el-form :model="choiceContent">
      <el-form-item label="题号" >
        <el-input  v-model="choiceContent.paperTitleId" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="作答内容" :label-width="formLabelWidth">
        <el-input v-model="choiceContent.paperStudentAnswer" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogChoiceVisible = false">取 消</el-button>
      <el-button type="primary" @click="saveAnswer">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "PaperCommit",
  data(){
    return{
      count: 10,
      tableData:[],
      form:[],
      dialogTableVisible : false,
      drawer:false,
      innerDrawer:false,
      innerData:[],
      dialogTitleVisible:false,
      onlyTitle:{},
      allId:"",
      course:[],
      uName:"",
      dialogChoiceVisible:false,
      choiceContent:{},
      Ids:[]


    }

  },
  created(){
    this.modules();
    this.selectId();
  },
  methods: {

    modules(){
      this.request.get("/atStudent/content/allModule?paperId="+this.allId).then(res=>{
        this.form = res
        this.choiceContent.paperId = this.allId
      })
    },

    open(row){
      this.drawer = true
      this.request.get("/atStudent/answerShell?module="+row.paperModule+"&paperId="+this.allId).then(res=>{
        this.tableData = res
      })
    },
    choices(row){
      this.request.get("/atTeacher/findChoices?paperId="+row.paperId+"&titleId="+row.paperTitleId).then(res=>{
        this.choiceContent.paperId = row.paperId
        this.choiceContent.paperTitleId = row.paperTitleId
        this.choiceContent.paperModule = row.paperModule
        this.innerData = res
        this.innerDrawer =true
      })
    },
    commitTitle() {
      this.request.post("/atTeacher/saveTitle", this.onlyTitle)
    },
    selectId(){
      this.uName= JSON.parse(localStorage.getItem("user"))? JSON.parse(localStorage.getItem("user")).name : ""
      console.log(this.uName)
      this.choiceContent.paperStudent = this.uName
      this.request.get("/atStudent/findIdForStu?name="+this.uName).then(res=> {
          this.Ids = res
      })

    },
    saveChoice(){
      this.request.post("/atTeacher/saveChoice",this.choiceContent).then(res=>{
        if(res.code === "200"){
          this.$message.success("保存成功")
          this.dialogChoiceVisible = false

        }else{
          this.$message.error("保存失败")
        }
      })
    },
    toContent(row){
      this.dialogChoiceVisible = true
      this.choiceContent.paperTitleId = row.paperTitleId
    },
    saveAnswer(){
      this.request.post("/atStudent/save",this.choiceContent).then(res=>{
        this.$message.success("yes")
        this.dialogChoiceVisible = false
        this.drawer=false

      })
    }




  }
}
</script>