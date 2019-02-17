<template>
  <header>
    <nav class="navbar has-shadow" role="navigation" aria-label="main navigation">
      <div class="navbar-brand">
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

      <div id="navbarManu" class="navbar-menu" :class="{'is-active': isMenuActive}">
        <div class="navbar-start">
          <RouterLink
            v-for="menu in menus"
            :key="menu.name"
            :to="menu.path"
            class="navbar-item">
             {{ menu.name }}
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
    }
  }
}
</script>
