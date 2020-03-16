import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
// import Main from '@/components/Main'
import Page from '@/components/Page'
import Debate from '@/components/Debate'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/navigation',
      name: 'navigation',
      component: require('../components/Navigation').default
    },
    {
      path: '/page',
      name: 'page',
      component: Page
    },
    {
      path: '/debate',
      name: 'debate',
      component: Debate
    }
  ]
})
