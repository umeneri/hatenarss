package hatenarss.services

import hatenarss.models.HatenaRssItem
import javax.inject.Inject
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HatenaRssServiceWS @Inject()(ws: WSClient) extends HatenaRssService {
  def getHatenaRssItems(keyword: String): Future[Seq[HatenaRssItem]] = {
    val url = s"http://b.hatena.ne.jp/$keyword.rss"

    val itemsFuture = RssClient(ws).read(url)

    itemsFuture.map { items =>
      items.map { item =>
        println(item)
        HatenaRssItem.fromJson(item)
      }
    }
  }
}
