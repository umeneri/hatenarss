<template>
  <HeaderNav :menus="menus"/>
</template>

<script>
import HeaderNav from '@/components/molecules/HeaderNav'
import { MENU_TYPE } from '@/entities/MenuType'

export default {
  components: { HeaderNav },
  props: {
    menus: {
      type: Array,
      default: () => Object.values(MENU_TYPE),
    }
  }
}
</script>
