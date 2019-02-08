import Vue from 'vue'
import Router from 'vue-router'
import EntryView from '@/components/EntryView'
import About from '@/components/About'
import Register from '@/components/Register'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: EntryView
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
