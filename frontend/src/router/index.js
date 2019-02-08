import Vue from 'vue'
import Router from 'vue-router'
import Entries from '@/components/Entries'
import About from '@/components/About'
import Register from '@/components/Register'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Entries
    },
    {
      path: '/about',
      component: About
    },
    {
      path: '/register',
      component: Register
    }
  ],
  mode: 'history'

})
