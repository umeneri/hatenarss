<template>
  <header>
    <nav class="navbar has-shadow" role="navigation" aria-label="main navigation">
      <div class="navbar-brand">
        <div class="navbar-item">
          <img src="/assets/logo.png"/>
        </div>
        <a role="button"
           class="navbar-burger burger"
           aria-label="menu"
           aria-expanded="false"
           data-target="navbarManu"
           @click="toggleMenu"
           :class="{'is-active': isMenuActive}">
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
        </a>
      </div>

      <div id="navbarManu" class="navbar-menu is-dark" :class="{'is-active': isMenuActive}">
        <div class="navbar-start">
          <RouterLink
            v-for="menu in menus"
            :key="menu.name"
            :to="menu.path"
            class="navbar-item">
              <span class="icon"><i :class="iconClasses(menu)"></i></span>
              <span>{{ menu.name }}</span>
          </RouterLink>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  props: {
    menus: {
      type: Array
    }
  },
  data () {
    return {
      isMenuActive: false
    }
  },
  methods: {
    toggleMenu () {
      this.isMenuActive = !this.isMenuActive
    },
    closeMenu () {
      this.isMenuActive = false
    },
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
$navbar-background-color: #363636
$navbar-item-color: hsl(0, 0%, 100%)
$navbar-item-hover-color: hsl(0, 0%, 29%)
$navbar-item-hover-background-color: hsl(0, 0%, 98%)

@import '~bulma'
</style>

