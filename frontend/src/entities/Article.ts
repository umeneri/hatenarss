export default class Article {
  constructor (item) {
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
