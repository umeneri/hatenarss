<template>
  <div class="column is-12 box">
    <article class="media" v-for="article in articles" :key="article.link">
      <div class="media-content">
        <div class="content">
          <p>
          <span><img :src="getFaviconUrl(article.link)" alt="favicon" class="favicon"/></span>
          <a :href="article.link" target="_blank" class="has-text-dark"><strong>{{ article.title }}</strong></a>
          </p>
        </div>

        <nav class="level is-mobile">
          <a class="level-left" :href="getHatenaButton(article.link)" target="_blank">
            <div class="level-item has-text-danger">
              <span class="icon is-small"><i class="fa fa-bookmark-o"></i></span>
            </div>
            <div class="level-item has-text-danger">
              <p>{{ article.bookmarkcount  }} User</p>
            </div>
          </a>
          <div class="level-right">
            <span class="has-text-grey is-size-8">{{ getFormatedDate(article.datetime) }}</span>
            <span class="tag">
              <span class="icon is-small"><i class="fa fa-chevron-down" @click="article.toggleDescription()"></i></span>
            </span>
          </div>
        </nav>

        <div v-if="article.hasVisibleDescription">
          {{ article.description }}
        </div>
      </div>

      <div class="media-right">
        <div>
          <a :href="article.link" target="_blank">
            <figure class="image is-64x64">
              <img class="is-clipped" :src="article.imageurl ? article.imageurl : defaultImageUrl " alt="image">
            </figure>
          </a>
        </div>
        <div>
          <span></span>
        </div>
      </div>

    </article>
  </div>

</template>

<script>
import {} from '@/../node_modules/twitter-relative-time-js/twitter.relative.time.min.js'
import Article from '@/entities/Article'

export default {
  props: [ 'articles' ],
  data () {
    return {
      defaultImageUrl: '/assets/hatenaRss-128x128.png'
    }
  },
  created () {
    this.articles = this.getArticles(this.items)
  },
  methods: {
    getArticles (items) {
      return items.map((item) => new Article(item))
    },
    getHatenaButton (url) {
      const target = this.removeLinkProtocol(url)
      return `http://b.hatena.ne.jp/entry/s/${target}`
    },
    removeLinkProtocol (url) {
      return url.replace(/(http|https):\/\//, "")
    },
    getFaviconUrl (url) {
      const base = "https://www.google.com/s2/favicons"
      const matched = url.match(/https?:\/\/(.+)\//)
      const domain = matched !== null && matched.length > 0 ? matched[1] : ''

      return `${base}?domain=${domain}`
    },
    getFormatedDate (date) {
      return (new Date(date)).toTwitterRelativeTime('ja')
    }
  }
}
</script>

<style lang="sass" scoped>
.image img
  height: 100%
  object-fit: cover

.favicon
  vertical-align: text-top
  padding-right: 4px
</style>
