<template>
  <table class="table is-fullwidth is-striped">
    <tr class="" v-for="article in articles" :key="article.link">

      <td class="button is-primary is-rounded">
        <p>{{ article.bookmarkcount }}</p>
      </td>

      <td class="">
        <figure class="image is-4by3">
          <img :src="article.imageurl" alt="image">
        </figure>
      </td>

      <td class="">
        <p class="">{{ article.title }}</p>
      </td>

      <time :datetime="article.datetime">{{ article.datetime }}</time>
    </tr>
  </table>
</template>

<script>
export default {
  props: [ 'articles' ]
}
</script>
