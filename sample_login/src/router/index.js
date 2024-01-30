import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LoginPageView from '../views/LoginPageView.vue'
import InfoPageView from '@/views/UserInfo.vue'
import RegistView from "@/views/Regist.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/loginPage',
      name: 'loginPage',
      component: LoginPageView
    },
    {
      path: '/userinfo',
      name: 'userinfo',
      component: InfoPageView
    },
    {
      path: '/regist',
      name: 'regist',
      component: RegistView
    }
  ]
})

export default router
