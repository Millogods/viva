import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AdminPanel from '../views/AdminPanel.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
      {
        path: '/home',
        name: 'home',
        component: HomeView
      },
      {
        path: '/',
        name: 'admin',
        component: AdminPanel
      },
  ]
})

export default router
