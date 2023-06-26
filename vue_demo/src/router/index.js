import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: () => import('../views/Manage.vue'),
        redirect: "/home",
        children: [
            { path: 'home', name: '首页', component: () => import('../views/Home.vue')},
            { path: 'teacher', name: '教师信息管理', component: () => import('../views/Teacher.vue')},
            { path: 'student', name: '学生信息管理',component: () => import('../views/Student.vue')},
            { path: 'clazz', name: '班级信息管理', component: () => import('../views/Clazz.vue')},
        ]
    },
    {
        path: '/login',
        component: () => import('../views/login/Login.vue'),
        children: [
            {
                path: '/login',
                name: '登录',
                component: () => import('../views/login/Login_Form.vue'),
            },
            {
                path: '/findPassword',
                name: '找回密码',
                component: () => import('../views/login/Find_Form.vue')
            }
        ]
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
    localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称，为了在Header组件中去使用
    store.commit("setPath")  // 触发store的数据更新
    next()  // 放行路由
})

export default router