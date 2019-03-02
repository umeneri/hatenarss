import Vue from 'vue'
import Router from 'vue-router'
import { shallowMount } from '@vue/test-utils'
import HeaderNav from '@/components/molecules/HeaderNav.vue'
import { MENU_TYPE } from '@/entities/MenuType'

Vue.use(Router)

describe('HeaderNav.vue', () => {
  it('toggle menu', () => {
    const wrapper = shallowMount(HeaderNav, {
      propsData: { menus: Object.values(MENU_TYPE) }
    })
    // wrapper.vm.toggleMenu()
    // expect(wrapper.vm.isMenuActive).toBe(true)
  })
})
