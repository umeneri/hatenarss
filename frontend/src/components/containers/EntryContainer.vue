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
// import hatenaHotentryJson from '@/data/hatena-hotentry'

export default {
  props: {
    keyword: {
      type: String
    },
    getUrl: {
      type: Function
    }
  },
  components: {
    ColumnView
  },
  data () {
    return {
      itemData: null,
      isEntriesVisible: false,
    }
  },
  mounted () {
    this.setRss()
  },
  methods: {
    async setRss () {
      this.itemData = await this.getRss()
      this.isEntriesVisible = true
    },
    async getRss () {
      const url = this.getUrl(this.keyword)
      const result = await axios.get(url)
      return result.data
    },
  },
  computed: {
    items () {
      return this.itemData
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

