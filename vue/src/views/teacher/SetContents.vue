<template>
<!--  <div>-->
<!--    <el-button @click="test2">默认按钮</el-button>-->
<!--    <div style="padding: 10px">-->
<!--      <el-card>-->
<!--        <div v-for="item in form" :key="item.paperModule" style="margin: 10px 0; padding: 10px 0; color: #666666; border-bottom: 1px dashed #cccccc">-->
<!--          <span style=" font-size: 20px; margin-top: 10px">{{ item.paperModule }}</span>-->

<!--&lt;!&ndash;          <span style=" font-size: 16px; margin-top: 10px">{{ item.paperTitleId }}.</span>&ndash;&gt;-->
<!--&lt;!&ndash;          <span style="font-size: 18px; cursor: pointer" class="item" @click="detail(item.id)">{{ item.paperTitle }}</span>&ndash;&gt;-->

<!--           <div style="padding: 10px">-->
<!--            <el-card>-->
<!--              <div v-for="item2 in tableData[0]" :key="item2.id" style="margin: 10px 0; padding: 10px 0; color: #666666; border-bottom: 1px dashed #cccccc">-->
<!--                <span style=" font-size: 16px; margin-top: 10px">{{ item2.paperTitleId }}.</span>-->
<!--                <span style=" font-size: 16px; margin-top: 10px">{{ item2.paperTitle }}</span>-->
<!--&lt;!&ndash;                <span style="font-size: 18px; cursor: pointer" class="item" @click="detail(item.id)">{{ item.paperTitle }}</span>&ndash;&gt;-->

<!--              </div>-->
<!--            </el-card>-->
<!--           </div>-->
<!--        </div>-->
<!--      </el-card>-->
<!--    </div>-->
<!--  </div>-->

<!--  <div>-->
<!--    <template>-->
<!--      <el-select v-model="allId" placeholder="请选择">-->
<!--        <el-option-->
<!--            v-for="item in options"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value">-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--    </template>-->
<!--  </div>-->
  <div>
    <template>-->
            <el-select v-model="allId" placeholder="请选择试卷号">
              <el-option
                  v-for="item in options"
                  :key="item"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
    </template>
    <el-button type="primary" round @click="modules">查询试卷<i class="el-icon-edit"></i></el-button>

    <el-button type="primary" round @click="addTitle">新增\修改题目<i class="el-icon-edit"></i></el-button>

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
        <el-table-column prop="paperTitleAnswer" label="题目答案" width="150">
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="info" round @click="choices(scope.row)">查看选择题的选项<i class="el-icon-edit"></i></el-button>
            <el-button type="danger"  round @click="removeTitle(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-drawer
          title="选择内容"
          :append-to-body="true"
          :before-close="handleClose"
          :visible.sync="innerDrawer">
        <el-button type="info" round @click="toContent">编辑<i class="el-icon-edit"></i></el-button>

        <el-table :data="innerData" border>
            <el-table-column prop="paperTitleOption" label="选项" width="70">
            </el-table-column>
            <el-table-column prop="paperOptionContent" label="内容" width="140">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="info" round @click="edit(scope.row)">编辑此选项<i class="el-icon-edit"></i></el-button>
                <el-button type="danger"  round  @click="removeChoice(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
              </template>
            </el-table-column>
          </el-table>
      </el-drawer>
    </el-drawer>

<!--    选择题内容弹出框-->
    <el-dialog title="编辑选项" :visible.sync="dialogChoiceVisible">
      <el-form :model="choiceContent">
        <el-form-item label="题号" >
          <el-input  v-model="choiceContent.paperTitleId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="选项" :label-width="formLabelWidth">
          <el-input v-model="choiceContent.paperTitleOption" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="选项内容" :label-width="formLabelWidth">
          <el-input v-model="choiceContent.paperOptionContent" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChoiceVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveChoice">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "SetContents",
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
      choiceContent:{}
    }

  },
  // computed:{
  //   demo(){
  //     for(let i=0;i<this.form.length;i++){
  //
  //     }
  //     this.form
  //     return{}
  //   },
  // },
  created(){
    // this.load();
    this.modules();
    this.selectId();
    // this.test1();
    // this.test2();
  },
  methods: {

    // load(){
    //   this.request.get("/content/all").then(res=>{
    //     this.tableData = res
    //   })
    // },
    test1(){
      this.request.get("/atTeacher/content/only").then(res=>{
        this.mo = res
        // console.log(this.mo)
      })
    },
    modules(){
      this.request.get("/atTeacher/content/allModule?paperId="+this.allId).then(res=>{
        this.form = res
      })
    },
    test2(){
      let name  = ""
      let arr = []
      for(let i=0;i<this.mo.length;i++){
        name = this.mo[i]
        this.request.get("/atTeacher/content/titleIn?module="+name).then(res=>{
            arr.push(res)
          // console.log(arr)
        })
      }
      this.tableData = arr
      // console.log(this.tableData)
    },
    open(row){
      this.drawer = true
      this.request.get("/atTeacher/content/titleIn?module="+row.paperModule).then(res=>{
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
    addTitle() {
      this.dialogTitleVisible = true
    },
    commitTitle() {
      this.request.post("/atTeacher/saveTitle", this.onlyTitle)
    },
    selectId(){
      this.uName= JSON.parse(localStorage.getItem("user"))? JSON.parse(localStorage.getItem("user")).name : ""
      console.log(this.uName)
      this.request.get("/atTeacher/selectCourse?teacherName="+this.uName).then(res=> {
        this.course = res
        this.request.get("/atTeacher/selectId?courseName="+this.course[0]).then(res=>{
          this.allId = res
        })
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
    toContent(){
      this.dialogChoiceVisible = true
    },
    edit(row){
      this.dialogChoiceVisible = true
      this.choiceContent = row
    },
    removeChoice(row){
      this.request.get("atTeacher/deOnlyChoice/"+row.id).then(res=>{
        if(res.code === "200"){
          this.$message.success("保存成功")
          this.dialogChoiceVisible = false

        }else{
          this.$message.error("保存失败")
        }
      })
    },
    removeTitle(row){
      this.request.get("atTeacher/deTitle/"+row.id).then(res=>{

      })
      this.request.get("/atTeacher/deChoice/"+row.paperTitleId)
    },
    removeModule(row){
      this.request.get("/atTeacher/delModule/"+row.paperModule).then(res=>{
        if(res.code === "200"){
          this.$message.success("删除成功")
          this.dialogChoiceVisible = false

        }else{
          this.$message.error("删除失败")
        }
      })
    }


  }
}
</script>
