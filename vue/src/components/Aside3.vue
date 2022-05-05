<template>
  <el-menu :default-openeds="['1', '3']" style="overflow-x:hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           router
  >
    <div>
      <b style="color: white;text-align: center">学生平台</b>
    </div>

    <el-submenu index="1">
      <template slot="title" ><i class="el-icon-files"></i>试卷库</template>
      <el-menu-item-group>
        <el-menu-item index="PaperInfo">所有试卷</el-menu-item>
<!--        <el-menu-item index="course2">学科与授课老师详情</el-menu-item>-->
      </el-menu-item-group>

    </el-submenu>
    <el-submenu index="2">
      <template slot="title"><i class="el-icon-files"></i>我的试卷管理</template>
      <el-menu-item-group>
        <!--        <template slot="title">教师列表</template>-->
        <el-menu-item index="PaperCommit">答题</el-menu-item>
        <!--        <el-menu-item index="2-2">选项2</el-menu-item>-->
      </el-menu-item-group>
      <el-menu-item-group >
        <el-menu-item index="history">历史答卷</el-menu-item>
      </el-menu-item-group>
      <!--      <el-submenu index="2-4">-->
      <!--        <template slot="title">选项4</template>-->
      <!--        <el-menu-item index="2-4-1">选项4-1</el-menu-item>-->
      <!--      </el-submenu>-->
    </el-submenu>

  </el-menu>
</template>

<script>

export default {
  name: "Aside2.vue",


}
</script>
