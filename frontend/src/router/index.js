import Vue from 'vue'
import Router from 'vue-router'
import TabContainer from '@/components/organisms/TabContainer'
import EntryContainer from '@/components/organisms/EntryContainer'
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
      getUrl: (keyword, page) => `/api/ranking?period=${keyword}&page=${page}`
    }
  }
})

const hotChildPathes = Object.keys(HOT_ENTRY_TYPE).map((key) => {
  const period = HOT_ENTRY_TYPE[key].path

  return {
    path: period,
    component: EntryContainer,
    props: {
      keyword: period,
      getUrl: (keyword) => `/api/hotentry?category=${keyword}`
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
