import Vue from 'vue'
import Router from 'vue-router'
import TabContainer from '@/components/containers/TabContainer'
import EntryContainer from '@/components/containers/EntryContainer'
import About from '@/components/About'
import { RANKING_TYPE } from '@/entities/RankingType'
import { HOT_ENTRY_TYPE } from '@/entities/HotEntryType'

Vue.use(Router)

const childPathes = Object.keys(RANKING_TYPE).map((key) => {
  const period = RANKING_TYPE[key].path

  return {
    path: period,
    component: EntryContainer,
    props: {
      keyword: period,
      getUrl: (period) => `/api/ranking?period=${period}`
    }
  }
})

const hotChildPathes = Object.keys(HOT_ENTRY_TYPE).map((key) => {
  const category = HOT_ENTRY_TYPE[key].path

  return {
    path: category,
    component: EntryContainer,
    props: {
      keyword: category,
      getUrl: (category) => `/api/hotentry?category=${category}`
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
      props: {
        typeEnum: RANKING_TYPE
      },
      children: childPathes
    },
    {
      path: '/hotentry',
      component: TabContainer,
      props: {
        typeEnum: HOT_ENTRY_TYPE
      },
      children: hotChildPathes
    },
    {
      path: '/about',
      component: About
    }
  ],
  mode: 'history'
})
