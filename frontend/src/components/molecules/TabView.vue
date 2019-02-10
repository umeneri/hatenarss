<template>
  <div>
    <div class="tabs is-centered">
      <ul>
        <li v-for="tab in tabs" :class="{ 'is-active': tab.isActive }" :key="tab.name" @click="selectTab(tab)">
          <router-link :to="tab.path">
            {{ tab.name }}
          </router-link>
        </li>
      </ul>
    </div>
    <router-view :key="$route.fullPath"/>
  </div>
</template>

<script>
export default {
  props: [ 'tabs' ],
  methods: {
    selectTab (selectedTab) {
      this.tabs.forEach(tab => {
        tab.isActive = (tab.name === selectedTab.name);
      });
    }
  }
}
</script>
