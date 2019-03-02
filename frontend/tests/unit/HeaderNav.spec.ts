import Vue from 'vue'
import Router from 'vue-router'
import { shallowMount, mount, Wrapper } from '@vue/test-utils';
import HeaderNav from '@/components/molecules/HeaderNav.vue'
import { MENU_TYPE } from '@/entities/MenuType'

Vue.use(Router)

describe('HeaderNav.vue', () => {
  it('toggle menu when button is clicked', () => {
    const wrapper: Wrapper<HeaderNav> = shallowMount(HeaderNav, {
      propsData: { menus: Object.values(MENU_TYPE) }
    })

    expect(wrapper.props().menus[0].name).toBe("Ranking")

    const button = wrapper.find('.navbar-burger');
    expect(button.isVisible).toBeTruthy

    button.trigger('click')

    expect(wrapper.vm.isMenuActive).toBe(true)
    expect(wrapper.html()).toMatchSnapshot();
  })
})
