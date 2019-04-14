package hatenarss.services

import org.json4s.native.JsonMethods
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import play.api.test.WsTestClient

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.xml.Node

class RssClientSpec extends WordSpec with Matchers with ScalaFutures with JsonMethods {
  "hatenarss.services.RssClient" should {
    "return a valid jsonObject for read RSS" in {
      val url = "http://b.hatena.ne.jp/entrylist?sort=hot&threshold=3&url=https%3A%2F%2Ftwitter.com&mode=rss"

      WsTestClient.withClient { client =>
        val xml: Future[Seq[Node]] = RssClient(client).read(url)
        val result: Seq[Node] = Await.result(xml, 10.seconds)

        result.length should be > 0
      }
    }
  }
}
