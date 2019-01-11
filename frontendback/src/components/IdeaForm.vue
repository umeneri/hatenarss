<template>
  <section class="hero is-fullheight-with-navbar">
    <div class="hero-body">
      <div class="container has-text-centered">

        <div class="tile box is-ancestor">
          <div class="tile is-parent is-2" v-for="(value, key) in selectedWordMap" :key="key">
            <div class="tile box is-child">
              <div class=''>
                {{ key }}
              </div>
              <div class="content is-large">
                {{ value }}
              </div>
            </div>
          </div>
        </div>

        <p>
          <button class="button is-primary is-outlined is-large" @click='setSelectedWordMap' >
            Get Idea!
          </button>
        </p>

      </div>
    </div>
  </section>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      selectedWordMap: {
        who1: '',
        who2: '',
        when: '',
        where: '',
        option1: '',
        option2: ''
      },
      wordMap: {
        who1: [],
        who2: [],
        when: [],
        where: [],
        option1: [],
        option2: []
      }
    }
  },
  methods: {
    async setSelectedWordMap () {
      this.wordMap = await this.getWordMap()

      this.selectedWordMap = {
        who1: this.getRandomWord(this.wordMap.who1),
        who2: this.getRandomWord(this.wordMap.who2),
        when: this.getRandomWord(this.wordMap.when),
        where: this.getRandomWord(this.wordMap.where),
        option1: this.getRandomWord(this.wordMap.option1),
        option2: this.getRandomWord(this.wordMap.option2)
      }
    },
    async getWordMap () {
      if (this.wordMap.who1.length === 0) {
        return this.downloadWordMap()
      } else {
        return this.wordMap
      }
    },
    async downloadWordMap () {
      const url = 'https://m5894b6kil.execute-api.ap-northeast-1.amazonaws.com/Prod/ideatool'
      const result = await axios.post(url)

      return result.data.map
    },
    getRandomWord (array) {
      if (array.length === 0) {
        return ''
      }

      const index = Math.floor(Math.random() * array.length)
      return array[index]
    }
  }
}
</script>
