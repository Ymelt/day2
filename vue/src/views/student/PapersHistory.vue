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
    <el-button type="primary" round @click="findHistory">查询试卷<i class="el-icon-edit"></i></el-button>


    <template>
      <el-table
          :data="tableData"
          border
          style="width: 100%">
        <el-table-column
            prop="paperTitleId"
            label="题号"
            width="80">
        </el-table-column>
        <el-table-column
            prop="paperModule"
            label="题目模块"
            width="120">
        </el-table-column>
        <el-table-column
            prop="paperTitleType"
            label="题目类别"
            width="120">
        </el-table-column>
        <el-table-column
            prop="paperTitle"
            label="题目"
            width="350">
        </el-table-column>
        <el-table-column
            prop="paperStudentAnswer"
            label="我的答案"
            width="350">
        </el-table-column>
        <el-table-column
            prop="paperJudge"
            label="批改结果">
        </el-table-column>
      </el-table>
    </template>


  </div>
</template>

<script>
export default {
  name: "PapersHistory",
  created() {
    this.findIds();
  },
  data(){
    return{
      allId:"",
      uName:"",
      Ids:[],
      tableData:[]
    }
  },
  methods:{
    findIds(){
      this.uName= JSON.parse(localStorage.getItem("user"))? JSON.parse(localStorage.getItem("user")).name : ""
      this.request.get("atStudent/selectIds?studentName="+this.uName).then(res=>{
        this.Ids = res
      })
    },
    findHistory(){
      this.request.get("/atStudent/history?paperId="+this.allId).then(res=>{
        this.tableData = res
      })
    }
  }
}
</script>

