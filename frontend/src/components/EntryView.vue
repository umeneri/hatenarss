<template>
  <section class="section">
    <h1 class="title">Hotentry</h1>
    <!-- <div class="box"> -->
      <transition appear>
        <div v-if="isEntriesVisible">
          <card-view :items="items"></card-view>
        </div>
        <!-- <list-view :items="items"></list-view> -->
      </transition>
    <!-- </div> -->
  </section>
</template>

<script>
import axios from 'axios'
import CardView from './entries/CardView.vue'
import ListView from './entries/ListView.vue'
import hatenaHotentryJson from '../data/hatena-hotentry'

export default {
  components: {
    CardView,
    ListView
  },
  data () {
    return {
      rssData: null,
      isEntriesVisible: false
    }
  },
  created () {
    this.setRss()
  },
  methods: {
    async setRss () {
      this.rssData = await this.getRss()
    },

    async getRss () {
      const url = '/api/hatena'
      const result = await axios.get(url)
      this.isEntriesVisible = true
      // console.log(result)
      return result.data
    },
  },
  computed: {
    items () {
      return this.rssData
      // return hatenaHotentryJson
    }
  }
}
</script>

<style scoped>
.v-enter-active, .v-leave-active {
  transition: opacity .5s;
}

.v-enter, .v-leave-to {
  opacity: 0;
}
</style>

