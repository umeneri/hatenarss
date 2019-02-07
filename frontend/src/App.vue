<template>
  <div id="app">
    <div class="main-content columns is-fullheight">
      <side-menu/>
      <div class="container column is-10">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>
import SideMenu from '@/components/SideMenu'

export default {
  name: 'app',
  components: {
    SideMenu
  }
}
</script>

<style lang="sass">
$menu-item-active-background-color: hsl(171, 100%, 41%);
$menu-item-color: hsl(0, 0%, 100%);

@import '~bulma';
$fa-font-path: '~font-awesome/fonts/';
@import '~font-awesome/scss/font-awesome';

</style>
