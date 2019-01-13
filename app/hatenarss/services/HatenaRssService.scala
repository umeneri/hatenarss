package hatenarss.services

import hatenarss.models.HatenaRssItem
import play.api.libs.ws.WSClient

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class HatenaRssService(ws: WSClient) {
  def getHatenaRssItems(url: String): Future[Seq[HatenaRssItem]] = {
    val itemsFuture = RssClient(ws).read(url)

    itemsFuture.map { items =>
      items.map { item =>
        println(item)
        HatenaRssItem.parse(item)
      }
    }
  }

  def getHatenaRssItemJsonString(url: String): Future[String] = {
    val itemsFuture = getHatenaRssItems(url)
    itemsFuture.map(HatenaRssItem.renderJson)
  }
}
