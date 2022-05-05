<template>
 <div>
    <el-form ref="form" :model="form" label-width="150px">
      <el-form-item label="试卷名称：" style="width: 600px">
        <el-input v-model="form.paperName"></el-input>
      </el-form-item>
      <el-form-item label="试卷编号：" style="width: 300px">
        <el-input v-model="form.paperId"></el-input>
      </el-form-item>
<!--      <el-form-item label="试卷科目：">-->
<!--        <el-select v-model="form.region" placeholder="请选择活动区域">-->
<!--          <el-option label="区域一" value="shanghai"></el-option>-->
<!--          <el-option label="区域二" value="beijing"></el-option>-->
<!--        </el-select>-->
<!--      </el-form-item>-->
      <el-form-item label="试卷科目：" style="width: 500px">
        <el-input v-model="form.paperCourse"></el-input>
      </el-form-item>
      <el-form-item label="出卷人：" style="width: 500px">
        <el-input v-model="form.paperAuthor"></el-input>
      </el-form-item>

      <el-form-item label="适用班级：" style="width: 500px">
        <el-input v-model="form.paperApplyClass"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
 </div>

</template>

<script>
export default {
  name: "design",
  data(){
    return{
      form: {}
    }
  },
  methods:{
    submit(){
      this.request.post("/atTeacher/save",this.form).then(res=>{
        if(res.code === "200"){
          this.$message.success("删除成功")
        }else{
          this.$message.error("删除失败")
        }
      })
    },
  }
}
</script>

