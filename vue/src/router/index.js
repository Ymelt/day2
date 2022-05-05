import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/admin/Manager.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/admin/Manager.vue'),
    children:[
      {
        path: 'course2', name: 'Message', component: () => import('../views/admin/Course2.vue')      },
      {
        path: 'course', name: 'Course', component: () => import('../views/admin/Course.vue')
      },
      {
        path: 'teacher1', name: 'Teacher1', component: () => import('../views/admin/Teacher1.vue')
      },
      {
        path: 'teacher2', name: 'Teacher2', component: () => import('../views/admin/Teacher2.vue')
      },
      {
        path: 'grade1', name: 'Grade1', component: () => import('../views/admin/Grade1.vue')
      },
      {
        path: 'theclass1', name: 'TheClass1', component: () => import('../views/admin/TheClass1.vue')
      },
      {
        path: 'student', name: 'Student', component: () => import('../views/admin/StudentInClass.vue')
      }
    ]

  },
  {
    path: '/forTeacher',
    name: 'TeacherHome',
    component: () => import('../views/teacher/Manager2.vue'),
    children:[
      {
        path: 'papers', name: 'Papers', component: () => import('../views/teacher/papers.vue')
      },
      {
        path: 'design', name: 'Design', component: () => import('../views/teacher/Design.vue')
      },
      {
        path: 'setContent', name: 'SetContent', component: () => import('../views/teacher/SetContents.vue')
      },
      {
        path: 'myStudents', name: 'MyStudents', component: () => import('../views/teacher/MyStudents.vue')
      },
    ]

  },
  {
    path: '/forStudent',
    name: 'StudentHome',
    component: () => import('../views/student/Manager3.vue'),
    children:[
      {
        path: 'theClass1', name: 'TheClass1', component: () => import('../views/student/TheClass1.vue')
      },
      {
        path: 'paperInfo', name: 'PaperInfo', component: () => import('../views/student/PaperInfo.vue')
      },
      {
        path: 'paperCommit', name: 'PaperCommit', component: () => import('../views/student/PaperCommit.vue')
      },
      {
        path: 'history', name: 'PaperHistory', component: () => import('../views/student/PapersHistory.vue')
      },
    ]

  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/test',
    name: 'Test',
    component: () => import('../views/Test.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
