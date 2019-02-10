<template>
  <SideMenu :menus="menus"/>
</template>

<script>
import SideMenu from '@/components/molecules/SideMenu'

export default {
  props: {
    menus: {
      type: Array,
      default: () => [
        {
          name: 'Ranking',
          path: '/ranking/daily',
          icon: 'home',
          isActive: true
        },
        {
          name: 'About',
          path: '/about',
          icon: 'info',
          isActive: false
        }
      ],
    }
  },
  components: {
    SideMenu
  }
}
</script>

