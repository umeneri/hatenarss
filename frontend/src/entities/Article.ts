export default class Article {
  bookmarkcount: number
  datetime: Date
  description: string
  imageurl: string
  link: string
  title: string
  hasVisibleDescription: boolean

  constructor (item: {
    bookmarkcount: number
    datetime: Date
    description: string
    imageurl: string
    link: string
    title: string
  }) {
    this.bookmarkcount = item.bookmarkcount
    this.datetime = item.datetime
    this.description = item.description
    this.imageurl = item.imageurl
    this.link = item.link
    this.title =  item.title
    this.hasVisibleDescription = false
  }

  toggleDescription () {
    this.hasVisibleDescription = !this.hasVisibleDescription
  }
}
