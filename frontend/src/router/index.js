import Vue from 'vue'
import Router from 'vue-router'
import TabContainer from '@/components/containers/TabContainer'
import EntryView from '@/components/EntryView'
// import EntryView2 from '@/components/EntryView2'
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
      path: '/ranking',
      component: TabContainer,
      children: [
        {
          path: 'daily',
          component: EntryView,
          props: { period: 'daily' }
        },
        {
          path: 'weekly',
          component: EntryView,
          props: { period: 'weekly' }
        }
      ]
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
