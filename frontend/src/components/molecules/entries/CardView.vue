<template>
  <div class="columns is-multiline">
    <div class="column is-4" v-for="article in articles" :key="article.link">
      <div class="card">
        <div class="card-image">
          <figure class="image is-4by3">
            <img :src="article.imageurl" alt="image">
          </figure>
        </div>

        <div class="card-header">
          <p class="card-header-title">{{ article.title }}</p>
        </div>

        <div class="card-content">
          <div class="content">
            {{ article.description }}
          </div>
          <br>
          <div class="level">
            <div class="level-left">
              <time :datetime="article.datetime">{{ article.datetime }}</time>
            </div>
            <div class="level-right button is-primary is-rounded">
              <p>{{ article.bookmarkcount }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: [ 'articles' ]
}
</script>
