<template>
  <div class="section">
    <div class="card">
      <div class="card-header"><p class="card-header-title">Header</p></div>
      <div class="card-content"><div class="content">Content</div></div>
    </div>
    <br />

    <div class="card is-hidden1">
      <div class="card-header"><p class="card-header-title">Header</p></div>
      <div class="card-content"><div class="content">Content</div></div>
    </div>
    <br />

    <div class="card is-hidden1">
      <div class="card-header"><p class="card-header-title">Header</p></div>
      <div class="card-content"><div class="content">Content</div></div>
    </div>
    <br />

    <div class="card is-hidden1">
      <div class="card-header"><p class="card-header-title">Header</p></div>
      <div class="card-content"><div class="content">Content</div></div>
    </div>
    <br />

    <div class="card is-hidden1">
      <div class="card-header"><p class="card-header-title">Header</p></div>
      <div class="card-content"><div class="content">Content</div></div>
    </div>
    <br />

    <div class="card is-hidden1">
      <div class="card-header"><p class="card-header-title">Header</p></div>
      <div class="card-content"><div class="content">Content</div></div>
    </div>
    <br />
  </div>
</template>

<script>
import axios from 'axios'

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
      console.log(result)
      return result.data
    }
  }
}
</script>
