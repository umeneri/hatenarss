<template>
  <div>
    <ClipLoader :loading="!isEntriesVisible"></ClipLoader>
    <transition>
      <div v-if="isEntriesVisible">
        <ColumnView :articles="articles"></ColumnView>
        <nav class="nav-bar has-text-centered">
          <LoadingButton
            v-if="!isEndPage"
            :title="'次のページ'"
            :loading="false"
            :onClickCallback="loadNextPage">
          </LoadingButton>
          <div v-else>
            最後のページです
          </div>
        </nav>
      </div>
    </transition>
  </div>
</template>

<script>
import axios from 'axios'
import ClipLoader from 'vue-spinner/src/ClipLoader.vue'
import ColumnView from '@/components/molecules/entries/ColumnView.vue'
import LoadingButton from '@/components/atoms/LoadingButton.vue'
import Article from '@/entities/Article'
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
    ClipLoader,
    LoadingButton
  },
  data () {
    return {
      itemData: [],
      isEntriesVisible: false,
      page: 1,
      isEndPage: false,
    }
  },
  mounted () {
    this.setRss()
  },
  methods: {
    async setRss () {
      const rssData = await this.getRss(this.keyword, this.page, this.getUrl)

      if (rssData.length <= 0) {
        this.isEndPage = true
        return
      }

      this.itemData = this.itemData.concat(this.getArticles(rssData))

      this.isEntriesVisible = true
      this.page += 1
    },
    getArticles (items) {
      return items.map((item) => new Article(item))
    },
    async loadNextPage () {
      await this.setRss()
    },
    async getRss (keyword, page, getUrl) {
      const url = getUrl(keyword, page)
      console.log(`get Rss url:  keyword: ${keyword}, page: ${page}, url: ${url}`);

      const result = await axios.get(url)
      return result.data
    }
  },
  computed: {
    articles () {
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
