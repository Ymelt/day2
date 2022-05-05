<template>

  <div>
    <el-table
        :data="tableData"
        height="250"
        border
        style="width: 100%">
      <el-table-column
          prop="paperId"
          label="试卷编号"
          width="180">
      </el-table-column>
      <el-table-column
          prop="paperName"
          label="试卷名字"
          width="180">
      </el-table-column>
      <el-table-column
          prop="paperCourse"
          label="试卷科目"
          width="180">
      </el-table-column>
      <el-table-column
          prop="paperAuthor"
          label="出卷老师">
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
  name: "PaperInfo",
  created() {
    this.findPapers();
  },
  data(){
    return{
      tableData:[],
      total:0,
      pageNum:1,
      pageSize:5,
    }
  },
  methods:{
    findPapers(){
      this.request.get("/atStudent/selectPapers",{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,
        }
      }).then(res=>{
        console.log(res)
        this.tableData = res.records
        this.total = res.total
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
