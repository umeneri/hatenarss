export const MENU_TYPE:{ [index:string] : {name: string, path: string, icon: string, isActive: Boolean} } = {
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
