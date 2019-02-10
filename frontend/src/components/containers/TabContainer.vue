<template>
  <section class="section">
    <tab-view :tabs="tabs"></tab-view>
  </section>
</template>

<script>
import TabView from '@/components/molecules/TabView'

export default {
  props: {
    tabs: {
      type: Array,
      default: () => [
        {
          name: '日',
          path: 'daily',
          isActive: true
        },
        {
          name: '週',
          path: 'weekly',
          isActive: false
        },
        {
          name: '月',
          path: 'monthly',
          isActive: false
        },
        {
          name: '年',
          path: 'annually',
          isActive: false
        },
        {
          name: '累計',
          path: 'all',
          isActive: false
        }
      ]
    }
  },
  components: {
    TabView
  }
}
</script>
