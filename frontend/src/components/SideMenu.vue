<template>
  <aside class="column is-2 is-narrow-mobile is-fullheight section is-hidden-mobile has-background-dark">
    <p class="menu-label is-hidden-touch has-text-white">Navigation</p>
    <ul class="menu-list">
      <li>
        <router-link to="/" :class="{ 'is-active': activeNum == 0 }" @click="changeActiveLink(0)">
          <span class="icon"><i class="fa fa-home"></i></span>Home
        </router-link>
      </li>
      <li>
        <router-link to="/About" :class="{ 'is-active': activeNum == 1 }" @click="changeActiveLink(1)">
          <span class="icon"><i class="fa fa-book"></i></span> About
        </router-link>
        <ul>
          <li>
            <a href="#" :class="{ 'is-active': activeNum == 10 }" @click="changeActiveLink(10)">
              <span class="icon is-small"><i class="fa fa-link"></i></span> Twitter
            </a>
          </li>
          <li>
            <a href="#" :class="{ 'is-active': activeNum == 11 }" @click="changeActiveLink(11)">
              <span class="icon is-small"><i class="fa fa-link"></i></span> Qiita
            </a>
          </li>
        </ul>
      </li>
      <li>
        <a href="#" :class="{ 'is-active': activeNum == 13 }" @click="changeActiveLink(13)">
          <span class="icon"><i class="fa fa-info"></i></span> Test
        </a>
      </li>
    </ul>
  </aside>
</template>

<script>
export default {
  data () {
    return {
      activeNum: 0
    }
  },
  methods: {
    changeActiveLink (n) {
      this.activeNum = n
    }
  }
}
</script>
