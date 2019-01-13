<template>
  <div class="section">
    <div class="container">
      <div class="columns is-multiline">

        <div class="column is-4" v-for="item in items" :key="item.link">
          <div class="card">
            <div class="card-image">
              <figure class="image is-4by3">
                <img :src="item.imageurl" alt="image">
              </figure>
            </div>

            <div class="card-header"><p class="card-header-title">{{ item.title }}</p></div>

            <div class="card-content">
              <div class="content">
                {{ item.description }}
              </div>
              <br>
              <time :datetime="item.datetime">{{ item.datetime }}</time>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import hatenaHotentryJson from '../data/hatena-hotentry'

export default {
  name: 'top',
  mounted: function () {
    this.setRss()
  },
  methods: {
    async setRss () {
      this.rssData = await this.getRss()
    },

    async getRss () {
      // const url = 'http://b.hatena.ne.jp/hotentry.rss'
      const url = '/api/hello'
      const result = await axios.get(url)
      // console.log(result)
      return result.data
    },
  },
  computed: {
    items: () => {
      return hatenaHotentryJson
    }
  }
}
</script>
