<template>
  <div class="column is-6 box">
    <article class="media" v-for="item in items" :key="item.link">
        <div class="media-content">
          <div class="content">
            <p>
              <a :href="item.link" target="_blank" class="has-text-dark"><strong>{{ item.title }}</strong></a>
            </p>
          </div>
          <nav class="level is-mobile">
            <a class="level-left" :href="createHatenaButton(item.link)" target="_blank">
              <div class="level-item has-text-danger">
                <span class="icon is-small"><i class="fa fa-bookmark-o"></i></span>
              </div>
              <div class="level-item has-text-danger">
                <p>{{ item.bookmarkcount  }} User</p>
              </div>
            </a>
          </nav>
        </div>

        <div class="media-right">
          <a :href="item.link" target="_blank">
            <figure class="image is-64x64">
              <img class="is-clipped" :src="item.imageurl ? item.imageurl : defaultImageUrl " alt="image">
            </figure>
          </a>
        </div>

    </article>
  </div>

</template>

<script>
export default {
  props: [ 'items' ],
  data () {
    return {
      defaultImageUrl: 'http://placehold.jp/128x128.png?text=hatenaRss'
    }
  },
  methods: {
    createHatenaButton (url) {
      const target = this.removeLinkProtocol(url)
      return `http://b.hatena.ne.jp/entry/s/${target}`
    },
    removeLinkProtocol (url) {
      return url.replace(/(http|https):\/\//, "")
    }
  }
}
</script>


<style  lang="sass" scoped>
.image img
  height: 100%
  object-fit: cover
</style>
