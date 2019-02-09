package hatenarss.services

import java.net.URL

import org.json4s.JValue
import org.json4s.native.JsonMethods
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import play.api.test.WsTestClient

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.io.Source
import scala.xml.{Elem, Node}

class RssClientSpec extends WordSpec with Matchers with ScalaFutures with JsonMethods {
  "hatenarss.services.RssClient" should {
    "convert rss to json object" in {
      val rssUrl: URL = getClass.getClassLoader.getResource("entry1.xml")
      val xml = scala.xml.XML.loadString(Source.fromURL(rssUrl).mkString)

      val json: JValue = RssClient.xmlToJson(xml)
      val items = json \\ "item"

      val expectedJsonUrl: URL = getClass.getClassLoader.getResource("entry1-item.json")
      val expectedJson = Source.fromURL(expectedJsonUrl).mkString

      pretty(render(items)) shouldBe expectedJson
    }

    "get feed items xml from rss xml" in {
      val rssUrl: URL = getClass.getClassLoader.getResource("entry1.xml")
      val xml = scala.xml.XML.loadString(Source.fromURL(rssUrl).mkString)

      RssClient.getItems(xml).length shouldBe 2
    }

    "return a valid jsonObject for read RSS" in {
      val url = "http://b.hatena.ne.jp/entrylist?sort=hot&threshold=3&url=https%3A%2F%2Ftwitter.com&mode=rss"

      WsTestClient.withClient { client =>
        val xml: Future[Seq[Node]] = RssClient(client).read(url)
        val result: Seq[Node] = Await.result(xml, 10.seconds)

        result.length should be > 0
        println(result)
      }
    }
  }
}
