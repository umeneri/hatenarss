import Vue from 'vue'
import Router from 'vue-router'
import TabContainer from '@/components/organisms/TabContainer.vue'
import EntryContainer from '@/components/organisms/EntryContainer.vue'
import About from '@/components/pages/About.vue'
import { RANKING_TYPE } from '@/entities/RankingType'
import { HOT_ENTRY_TYPE } from '@/entities/HotEntryType'

Vue.use(Router)

const childPathes = Object.keys(RANKING_TYPE).map((key) => {
  const period: string = RANKING_TYPE[key].path

  return {
    name: `ranking-${period}`,
    path: period,
    component: EntryContainer,
    props: {
      keyword: period,
      getUrl: (keyword: string, page: number) => `/api/ranking?period=${keyword}&page=${page}`
    }
  }
})

const hotChildPathes = Object.keys(HOT_ENTRY_TYPE).map((key) => {
  const category = HOT_ENTRY_TYPE[key].path

  return {
    name: `hotentry-${category}`,
    path: category,
    component: EntryContainer,
    props: {
      keyword: category,
      getUrl: (keyword: string, page: number) => `/api/hotentry?category=${keyword}&page=${page}`
    }
  }
})

export default new Router({
  routes: [
    {
      name: 'root',
      path: '/',
      redirect: '/ranking/daily',
      component: EntryContainer
    },
    {
      name: 'ranking',
      path: '/ranking',
      component: TabContainer,
      props: {
        typeEnum: RANKING_TYPE
      },
      children: childPathes
    },
    {
      name: 'hotentry',
      path: '/hotentry',
      component: TabContainer,
      props: {
        typeEnum: HOT_ENTRY_TYPE
      },
      children: hotChildPathes
    },
    {
      name: 'about',
      path: '/about',
      component: About
    }
  ],
  mode: 'history'
})
