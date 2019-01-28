package hatenarss.services

import hatenarss.models.HatenaRssItem
import org.scalatest.{Matchers, WordSpec}
import play.api.test.WsTestClient

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class HatenaRssServiceImplSpec extends WordSpec with Matchers {
  "hatenarss.services.HatenaRssServiceImple" should {
    "get hatena rss items from url" in {
      val url = "http://b.hatena.ne.jp/entrylist?sort=hot&threshold=3&url=https%3A%2F%2Ftwitter.com&mode=rss"

      WsTestClient.withClient { client =>
        val items: Future[Seq[HatenaRssItem]] = new HatenaRssServiceImpl(client).getHatenaRssItems(url)
        val result: Seq[HatenaRssItem] = Await.result(items, 10.seconds)
        result.length should be > 0
      }
    }
  }
}
