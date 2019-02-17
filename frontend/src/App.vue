<template>
  <div id="app">
    <HeaderNavContainer class="is-hidden-desktop"/>
    <div class="columns">
      <SideMenuContainer class="is-hidden-touch"/>
      <main class="main-content is-fullheight">
        <div class="container">
          <RouterView :key="$route.fullPath"/>
        </div>
      </main>
    </div>
    <FooterNav/>
  </div>
</template>

<script>
import HeaderNavContainer from '@/components/organisms/HeaderNavContainer'
import SideMenuContainer from '@/components/organisms/SideMenuContainer'
import FooterNav from '@/components/organisms/FooterNav'

export default {
  components: {
    HeaderNavContainer,
    SideMenuContainer,
    FooterNav
  }
}
</script>

<style lang="sass">
$footer-background-color: hsl(0, 0%, 21%)
$menu-item-active-background-color: hsl(171, 100%, 41%)
$menu-item-color: hsl(0, 0%, 100%)

@import '~bulma'
$fa-font-path: '~font-awesome/fonts/'
@import '~font-awesome/scss/font-awesome'

</style>
