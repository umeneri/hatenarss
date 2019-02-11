import Vue from 'vue'
import Router from 'vue-router'
import TabContainer from '@/components/containers/TabContainer'
import HotTabContainer from '@/components/containers/HotTabContainer'
import EntryContainer from '@/components/containers/EntryContainer'
import About from '@/components/About'
import { RANKING_TYPE } from '@/entities/RankingType'
import { HOT_ENTRY_TYPE } from '@/entities/HotEntryType'

Vue.use(Router)

const childPathes = Object.keys(RANKING_TYPE).map((key) => {
  const period = RANKING_TYPE[key].period

  return {
    path: period,
    component: EntryContainer,
    props: {
      keyword: period,
      generateUrl: (period) => `/api/ranking?period=${period}`
    }
  }
})

const hotChildPathes = Object.keys(HOT_ENTRY_TYPE).map((key) => {
  const category = HOT_ENTRY_TYPE[key].category

  return {
    path: category,
    component: EntryContainer,
    props: {
      keyword: category,
      generateUrl: (category) => `/api/hotentry?category=${category}`
    }
  }
})


export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/ranking/daily',
      component: EntryContainer
    },
    {
      path: '/ranking',
      component: TabContainer,
      children: childPathes
    },
    {
      path: '/hotentry',
      component: HotTabContainer,
      children: hotChildPathes
    },
    {
      path: '/about',
      component: About
    }
  ],
  mode: 'history'
})
