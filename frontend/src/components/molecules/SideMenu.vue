<template>
  <aside class="column is-2 is-narrow-mobile is-fullheight section is-hidden-mobile has-background-dark">
    <p class="menu-label has-text-white">Navigation</p>

    <ul class="menu-list">
      <li v-for="menu in menus" :class="{ 'is-active': menu.isActive }" :key="menu.name" @click="selectMenu(menu)">
        <router-link :to="menu.path">
          <span class="icon"><i :class="iconClasses(menu)"></i></span>{{ menu.name }}
        </router-link>
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
