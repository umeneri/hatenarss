<template>
  <SideMenu :menus="menus"/>
</template>

<script>
import SideMenu from '@/components/molecules/SideMenu'
import { MENU_TYPE } from '@/entities/MenuType'

export default {
  components: {
    SideMenu
  },
  props: {
    menus: {
      type: Array,
      default: () => Object.values(MENU_TYPE),
    }
  }
}
</script>

