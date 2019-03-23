import MenuItem from '@/interfaces/MenuItem'

export const MENU_TYPE:{ [index:string] : MenuItem } = {
  RANKING: {
    name: 'Ranking',
    path: '/ranking/daily',
    icon: 'line-chart',
    isActive: true,
  },
  HOT_ENTRY: {
    name: 'HotEntry',
    path: '/hotentry/hotentry',
    icon: 'fire',
    isActive: false,
  },
  ABOUT: {
    name: 'About',
    path: '/about',
    icon: 'info',
    isActive: false,
  }
}
