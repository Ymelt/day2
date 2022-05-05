<template>
  <el-menu :default-openeds="['1', '3']" style="overflow-x:hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           router
  >
    <div>
      <b style="color: white;text-align: center">后台管理</b>
    </div>

    <el-submenu index="1">
      <template slot="title" ><i class="el-icon-files"></i>学科管理</template>
      <el-menu-item-group>
        <el-menu-item index="course">所有学科</el-menu-item>
        <el-menu-item index="course2">学科与授课老师详情</el-menu-item>
      </el-menu-item-group>

    </el-submenu>
    <el-submenu index="2">
      <template slot="title"><i class="el-icon-files"></i>教师管理</template>
      <el-menu-item-group>
<!--        <template slot="title">教师列表</template>-->
        <el-menu-item index="teacher1">教师列表</el-menu-item>
<!--        <el-menu-item index="2-2">选项2</el-menu-item>-->
      </el-menu-item-group>
      <el-menu-item-group >
        <el-menu-item index="teacher2">教师授课管理</el-menu-item>
      </el-menu-item-group>
<!--      <el-submenu index="2-4">-->
<!--        <template slot="title">选项4</template>-->
<!--        <el-menu-item index="2-4-1">选项4-1</el-menu-item>-->
<!--      </el-submenu>-->
    </el-submenu>
    <el-submenu index="3">
      <template slot="title"><i class="el-icon-files"></i>年级段管理</template>
      <el-menu-item-group>
        <!--        <template slot="title">教师列表</template>-->
        <el-menu-item index="grade1">所有年级段及班级</el-menu-item>
        <!--        <el-menu-item index="2-2">选项2</el-menu-item>-->
      </el-menu-item-group>
<!--      <el-menu-item-group >-->
<!--        <el-menu-item index="teacher2">教师授课管理</el-menu-item>-->
<!--      </el-menu-item-group>-->
    </el-submenu>

    <el-submenu index="4">
      <template slot="title"><i class="el-icon-files"></i>班级管理</template>
      <el-menu-item-group>
        <!--        <template slot="title">教师列表</template>-->
        <el-menu-item index="theclass1">所有班级</el-menu-item>
        <!--        <el-menu-item index="2-2">选项2</el-menu-item>-->
      </el-menu-item-group>
      <el-menu-item-group >
        <el-menu-item index="student">班级里的学生</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside.vue",
  props:{
    isCollapse : Boolean,
    logoTextShow :Boolean
  }

}
</script>

<style scoped>

</style>