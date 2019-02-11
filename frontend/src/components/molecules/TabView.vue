<template>
  <section class="section">
    <div class="tabs is-centered is-fullwidth">
      <ul>
        <li v-for="tab in tabs" :class="{ 'is-active': tab.isActive }" :key="tab.name" @click="selectTab(tab)">
          <router-link :to="tab.path">
            {{ tab.name }}
          </router-link>
        </li>
      </ul>
    </div>
    <router-view :key="$route.fullPath"/>
  </section>
</template>

<script>
export default {
  props: [ 'tabs', 'path'],
  created () {
    this.resolveActiveTabName()
  },
  updated () {
    this.resolveActiveTabName()
  },
  methods: {
    resolveActiveTabName () {
      // get last word by splitting '/'
      const tabPath = this.path.replace(/(.*\/)[/]*/, '')

      this.tabs.forEach(tab => {
        tab.isActive = (tab.path === tabPath);
      });
    },
    selectTab (selectedTab) {
      this.tabs.forEach(tab => {
        tab.isActive = (tab.name === selectedTab.name);
      });
    }
  }
}
</script>
