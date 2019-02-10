<template>
  <div>
    <transition>
      <div v-if="isEntriesVisible">
        <column-view :items="items"></column-view>
      </div>
    </transition>
  </div>
</template>

<script>
import axios from 'axios'
import ColumnView from '@/components/entries/ColumnView.vue'
import hatenaHotentryJson from '@/data/hatena-hotentry'

export default {
  props: {
    category: {
      type: String
    }
  },
  components: {
    ColumnView
  },
  data () {
    return {
      rssData: null,
      isEntriesVisible: false,
    }
  },
  mounted () {
    this.setRss()
  },
  methods: {
    async setRss () {
      // this.rssData = await this.getRss()
      this.rssData = await this.getRss()
      this.isEntriesVisible = true
    },
    async getRss () {
      const url = `/api/hotentry?category=${this.category}`
      const result = await axios.get(url)
      return result.data
    },
  },
  computed: {
    items () {
      return this.rssData
    }
  }
}
</script>

<style lang="sass" scoped>
.v-enter-active, .v-leave-active
  transition: opacity .5s

.v-enter, .v-leave-to
  opacity: 0

</style>

