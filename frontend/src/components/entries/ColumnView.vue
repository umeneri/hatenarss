<template>
  <div class="column is-6 box">
    <article class="media" v-for="item in items" :key="item.link">
        <div class="media-left">
          <!-- <p class="image is-14x14">                  -->
          <!--   <img src="http://placehold.jp/14x14.png"> -->
          <!-- </p>                                        -->
        </div>

        <div class="media-content">
          <div class="content">
            <p>
              <a :href="item.link" target="_blank" class="has-text-dark">{{ item.title }}</a>
            </p>
          </div>
          <nav class="level is-mobile">
            <div class="level-left">
              <a class="level-item">
                <span class="icon is-small"><i class="fa fa-bookmark-o"></i></span>
              </a>
              <a class="level-item">
                <p>{{ item.bookmarkcount  }} User</p>
              </a>
            </div>
            <div class="level-right">
            </div>
          </nav>
        </div>

        <figure class="media-right">
          <figure class="image is-64x64">
            <img class="is-clipped" :src="item.imageurl ? item.imageurl : defaultImageUrl " alt="image">
          </figure>
        </figure>

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
  }
}
</script>


<style scoped>
.image img {
  height: 100%;
  object-fit: cover;
}
</style>
