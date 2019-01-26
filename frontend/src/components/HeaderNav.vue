<template>
  <header>
    <nav class="navbar has-shadow" role="navigation" aria-label="main navigation">
      <div class="navbar-brand">
        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample"
          @click="toggleMenu" :class="{'is-active': isMenuActive}">
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
        </a>
      </div>

      <div id="navbarBasicExample" class="navbar-menu" :class="{'is-active': isMenuActive}">
        <div class="navbar-end">
          <router-link to="/" class="navbar-item">Top</router-link>
          <router-link to="/about" class="navbar-item">About</router-link>
          <router-link to="/register" class="navbar-item">Register</router-link>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  // name: 'header-nav',
  data: () => {
    return { isMenuActive: false }
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

<style scoped>
</style>
