/*global Vue*/
import Router from 'vue-router'
import Login from '@/components/main/login.vue'
import Main from '@/components/main/index.vue'
import GatherMoney from '@/components/wallet/gatherMoney.vue'
import CreateIdentity from '@/components/main/createIdentity.vue'
import RecoverIdentity from '@/components/main/recoverIdentity.vue'
import Management from '@/components/wallet/management.vue'

Vue.use(Router)

export const router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/main'
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/createIdentity',
      name: 'CreateIdentity',
      component: CreateIdentity
    },
    {
      path: '/recoverIdentity',
      name: 'RecoverIdentity',
      component: RecoverIdentity
    },
    {
      path: '/main',
      name: 'Main',
      component: Main
    },
    {
      path: '/gatherMoney',
      name: 'GatherMoney',
      component: GatherMoney
    },
    {
      path: '/management',
      name: 'Management',
      component: Management
    }
  ]
})
