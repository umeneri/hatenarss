<template>
  <div class="columns is-multiline">
    <div class="column is-4" v-for="item in items" :key="item.link">
      <div class="card">
        <div class="card-image">
          <figure class="image is-4by3">
            <img :src="item.imageurl" alt="image">
          </figure>
        </div>

        <div class="card-header">
          <p class="card-header-title">{{ item.title }}</p>
        </div>

        <div class="card-content">
          <div class="content">
            {{ item.description }}
          </div>
          <br>
          <div class="level">
            <div class="level-left">
              <time :datetime="item.datetime">{{ item.datetime }}</time>
            </div>
            <div class="level-right button is-primary is-rounded">
              <p>{{ item.bookmarkcount }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: [ 'items' ]
}
</script>
