<template>
  <!-- columns and show hidden style depending on device is written here -->
  <div id="app">
    <HeaderNavContainer class=""/>
    <div class="side-margin-less columns">
      <main class="column is-10 is-fullheight container">
        <RouterView :key="$route.fullPath"/>
      </main>
    </div>
    <FooterNav class="side-margin-less columns"/>
  </div>
</template>

<script>
import HeaderNavContainer from '@/components/organisms/HeaderNavContainer'
import FooterNav from '@/components/organisms/FooterNav'

export default {
  components: {
    HeaderNavContainer,
    FooterNav
  }
}
</script>

<style lang="sass">
@import '~bulma'
$fa-font-path: '~font-awesome/fonts/'
@import '~font-awesome/scss/font-awesome'

.side-margin-less.columns
  margin-right: 0
  margin-left: 0
</style>
