<template>
  <div id="app">
    <div class="main-content columns is-fullheight">
      <SideMenuContainer/>
      <div class="container column is-10">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>
import SideMenuContainer from '@/components/containers/SideMenuContainer'

export default {
  name: 'app',
  components: {
    SideMenuContainer
  }
}
</script>

<style lang="sass">
$menu-item-active-background-color: hsl(171, 100%, 41%);
$menu-item-color: hsl(0, 0%, 100%);

@import '~bulma'
$fa-font-path: '~font-awesome/fonts/'
@import '~font-awesome/scss/font-awesome'

</style>
