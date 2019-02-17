<template>
  <table class="table is-fullwidth is-striped">
    <tr class="" v-for="item in items" :key="item.link">

      <td class="button is-primary is-rounded">
        <p>{{ item.bookmarkcount }}</p>
      </td>

      <td class="">
        <figure class="image is-4by3">
          <img :src="item.imageurl" alt="image">
        </figure>
      </td>

      <td class="">
        <p class="">{{ item.title }}</p>
      </td>

      <time :datetime="item.datetime">{{ item.datetime }}</time>
    </tr>
  </table>
</template>

<script>
export default {
  props: [ 'items' ]
}
</script>
