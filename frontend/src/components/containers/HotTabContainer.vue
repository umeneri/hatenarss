<template>
  <section class="section">
    <tab-view :tabs="tabs" :path="$route.path"></tab-view>
  </section>
</template>

<script>
import TabView from '@/components/molecules/TabView'
import { HOT_ENTRY_TYPE } from '@/entities/HotEntryType'

const tabList = Object.keys(HOT_ENTRY_TYPE).map((key) => {
  return {
          name: HOT_ENTRY_TYPE[key].name,
          path: HOT_ENTRY_TYPE[key].category,
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
