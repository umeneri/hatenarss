import Vue from 'vue'
import Router from 'vue-router'
import TabContainer from '@/components/containers/TabContainer'
import HotTabContainer from '@/components/containers/HotTabContainer'
import EntryContainer from '@/components/containers/EntryContainer'
import HotEntryContainer from '@/components/containers/HotEntryContainer'
import About from '@/components/About'
import { RANKING_TYPE } from '@/entities/RankingType'
import { HOT_ENTRY_TYPE } from '@/entities/HotEntryType'

Vue.use(Router)

const childPathes = Object.keys(RANKING_TYPE).map((key) => {
  const period = RANKING_TYPE[key].period

  return {
    path: period,
    component: EntryContainer,
    props: { period: period }
  }
})

const hotChildPathes = Object.keys(HOT_ENTRY_TYPE).map((key) => {
  return {
    path: HOT_ENTRY_TYPE[key].category,
    component: HotEntryContainer,
    props: { category: HOT_ENTRY_TYPE[key].category }
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
