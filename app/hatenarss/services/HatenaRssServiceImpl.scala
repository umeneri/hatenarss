package hatenarss.services

import hatenarss.models.HatenaRssItem
import javax.inject.Inject
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HatenaRssServiceImpl @Inject()(ws: WSClient) extends HatenaRssService {
  def getHatenaRssItemJsonString(url: String): Future[String] = {
    val itemsFuture = getHatenaRssItems(url)
    itemsFuture.map(HatenaRssItem.renderJson)
  }

  def getHatenaRssItems(url: String): Future[Seq[HatenaRssItem]] = {
    val itemsFuture = RssClient(ws).read(url)

    itemsFuture.map { items =>
      items.map { item =>
        println(item)
        HatenaRssItem.fromJson(item)
      }
    }
  }
}
