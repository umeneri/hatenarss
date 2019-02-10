<template>
  <div>
    <transition>
      <div v-if="isEntriesVisible">
        <column-view :items="items"></column-view>
        <!-- <card-view :items="items"></card-view> -->
        <!-- <list-view :items="items"></list-view> -->
      </div>
    </transition>
  </div>
</template>

<script>
import axios from 'axios'
// import CardView from './entries/CardView.vue'
// import ListView from './entries/ListView.vue'
import ColumnView from './entries/ColumnView.vue'
import hatenaHotentryJson from '../data/hatena-hotentry'

export default {
  props: {
    period: {
      type: String,
      default: 'daily'
    }
  },
  components: {
    // CardView,
    // ListView,
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
    },
    async getRss () {
      const url = `/api/ranking?period=${this.period}`
      const result = await axios.get(url)

      this.isEntriesVisible = true

      return result.data
    },
  },
  computed: {
    items () {
      // return this.rssData
      return hatenaHotentryJson
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

