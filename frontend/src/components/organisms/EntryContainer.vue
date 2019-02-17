<template>
  <div>
    <clip-loader :loading="!isEntriesVisible"></clip-loader>
    <transition>
      <div v-if="isEntriesVisible">
        <column-view :items="items"></column-view>
        <nav class="nav-bar has-text-centered">
          <div @click="loadNextPage"
            class="button is-primary"
            :class="{ 'is-loading': isLoadingNext }">
            次のページ
          </div>
        </nav>
      </div>
    </transition>
  </div>
</template>

<script>
import axios from 'axios'
import ColumnView from '@/components/entries/ColumnView.vue'
import ClipLoader from 'vue-spinner/src/ClipLoader.vue'
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
    ColumnView,
    ClipLoader
  },
  data () {
    return {
      itemData: [],
      isEntriesVisible: false,
      isLoadingNext: false,
      page: 1
    }
  },
  mounted () {
    this.setRss()
  },
  methods: {
    async setRss () {
      const rssData = await this.getRss(this.keyword, this.page, this.getUrl)
      this.itemData = this.itemData.concat(rssData)
      this.isEntriesVisible = true
      this.page += 1
    },
    async getRss (keyword, page, getUrl) {
      const url = getUrl(keyword, page)
      console.log(`get Rss url:  keyword: ${keyword}, page: ${page}, url: ${url}`);

      const result = await axios.get(url)
      return result.data
    },
    async loadNextPage () {
      this.isLoadingNext = true
      await this.setRss()
      this.isLoadingNext = false
    }
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
