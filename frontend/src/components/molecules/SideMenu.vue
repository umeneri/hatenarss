<template>
  <aside class="column is-2 section has-background-dark side-height">
    <p class="menu-label has-text-white">Menu</p>

    <ul class="menu-list">
      <li v-for="menu in menus"
          :key="menu.name"
          @click="selectMenu(menu)">
        <RouterLink
          :to="menu.path"
          :class="{ 'is-active': menu.isActive }">
          <span class="icon"><i :class="iconClasses(menu)"></i></span>{{ menu.name }}
        </RouterLink>
      </li>
    </ul>

  </aside>
</template>

<script>
export default {
  props: [ 'menus' ],
  methods: {
    selectMenu (selectedMenu) {
      this.menus.forEach(menu => {
        menu.isActive = (menu.name === selectedMenu.name);
      });
    },
    iconClasses (menu) {
      return `fa fa-${menu.icon}`
    }
  }
}
</script>

<style lang="sass" scoped>
.side-height
  min-height: 3000px
</style>
