<template>
  <section class="section">
    <tab-view :tabs="tabs" :path="$route.path"></tab-view>
  </section>
</template>

<script>
import TabView from '@/components/molecules/TabView'

export default {
  props: {
    typeEnum: {
      type: Object
    },
  },
  data () {
    // NOTE: tabs is immutable type, but can't define in props because of generating it later.
    return {
      tabs: this.getTabList()
    }
  },
  components: {
    TabView
  },
  methods: {
    getTabList() {
      return Object.keys(this.typeEnum).map((key) => {
        return {
          name: this.typeEnum[key].name,
          path: this.typeEnum[key].path,
          isActive: false
        }
      })
    }
  }
}
</script>
