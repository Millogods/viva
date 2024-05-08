import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DemoPage from '../views/DemoPage.vue'
import AdminPanel from '../views/AdminPanel.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
      {
        path: '/',
        name: 'home',
        component: HomeView
      },
      {
        path: '/admin',
        name: 'admin',
        component: AdminPanel
      },
      {
        path: '/demo',
        name: 'demo',
        component: DemoPage
      },
  ]
})

export default router
