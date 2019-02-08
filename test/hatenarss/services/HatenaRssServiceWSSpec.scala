package hatenarss.services

import hatenarss.models.HatenaRssItem
import org.scalatest.{Matchers, WordSpec}
import play.api.test.WsTestClient

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class HatenaRssServiceWSSpec extends WordSpec with Matchers {
  "hatenarss.services.HatenaRssServiceImple" should {
    "get hatena rss items from url" in {
//      val url = "http://b.hatena.ne.jp/entrylist?sort=hot&threshold=3&url=https%3A%2F%2Ftwitter.com&mode=rss"
      val keyword = "hotentry"

      WsTestClient.withClient { client =>
        val items: Future[Seq[HatenaRssItem]] = new HatenaRssServiceWS(client).getHatenaRssItems(keyword)
        val result: Seq[HatenaRssItem] = Await.result(items, 10.seconds)
        result.length should be > 0
      }
    }
  }
}