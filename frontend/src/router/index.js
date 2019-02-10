import Vue from 'vue'
import Router from 'vue-router'
import TabContainer from '@/components/containers/TabContainer'
import EntryView from '@/components/EntryView'
import About from '@/components/About'
import { RANKING_TYPE } from '@/entities/RankingType'

Vue.use(Router)

const childPathes = Object.keys(RANKING_TYPE).map((key) => {
  const period = RANKING_TYPE[key].period

  return {
    path: period,
    component: EntryView,
    props: { period: period }
  }
})

export default new Router({
  routes: [
    {
      path: '/',
      component: EntryView
    },
    {
      path: '/ranking',
      component: TabContainer,
      children: childPathes
    },
    {
      path: '/about',
      component: About
    }
  ],
  mode: 'history'
})
