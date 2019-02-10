import Vue from 'vue'
import Router from 'vue-router'
import TabContainer from '@/components/containers/TabContainer'
import EntryView from '@/components/EntryView'
import About from '@/components/About'

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
    }
  ],
  mode: 'history'
})
