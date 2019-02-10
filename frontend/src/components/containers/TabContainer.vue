<template>
  <section class="section">
    <tab-view :tabs="tabs" :path="$route.path"></tab-view>
  </section>
</template>

<script>
import TabView from '@/components/molecules/TabView'
import { RANKING_TYPE } from '@/entities/RankingType'

const tabList = Object.keys(RANKING_TYPE).map((key) => {
  return {
          name: RANKING_TYPE[key].name,
          path: RANKING_TYPE[key].period,
          isActive: false
        }
})

export default {
  props: {
    tabs: {
      type: Array,
      default: () => tabList
    },
  },
  components: {
    TabView
  }
}
</script>
