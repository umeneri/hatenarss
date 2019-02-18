package hatenarss.models

import hatenarss.helpers.HatenaRssItemSerializer
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization.write
import org.json4s._
import org.scalatest.{Matchers, WordSpec}

import scala.xml.XML

class HatenaRssItemSpec extends WordSpec with Matchers {
  "hatenarss.models.HatenaRssItem" should {
    "create from xml object" in {
      val url = this.getClass.getClassLoader.getResource("item.xml")
      val xml = XML.load(url)
      val rssItem = HatenaRssItem.fromXml(xml)
      rssItem.title shouldBe "Angularの学習コストは本当に高いのか？ - lacolaco"
      rssItem.bookmarkCount shouldBe 23
    }

    "get image url" in {
      val result = "https://cdn-ak-scissors.b.st-hatena.com/image/square/98756aa90bd66bcc61be3cfd9f74ef83e0c381c4/height=90;version=1;width=120/https%3A%2F%2Fcdn-ak.f.st-hatena.com%2Fimages%2Ffotolife%2Fl%2Flacolaco%2F20190219%2F20190219000741.png"
      val url = this.getClass.getClassLoader.getResource("item.xml")
      val xml = XML.load(url)
      val imageUrl = HatenaRssItem.getImageUrl(xml)

      imageUrl shouldBe Some(result)
    }

    "created from json object" in {
      val jsonString =
        """
          |{
          |    "about":"https://twitter.com/mazy_3anime/status/1083159787674587136",
          |    "title":"アニメ版まじさん",
          |    "link":"https://twitter.com/mazy_3anime/status/1083159787674587136",
          |    "description":"Bar",
          |    "dc:date":"2019-01-12T03:15:00Z",
          |    "dc:subject":"世の中",
          |    "dc:subject":"アニメ",
          |    "taxo:topics":{
          |      "rdf:Bag":{
          |        "resource":"http://b.hatena.ne.jp/search/tag?q=%E3%82%A2%E3%83%8B%E3%83%A1"
          |      }
          |    },
          |    "content:encoded":"content1",
          |    "hatena:imageurl":"https://pbs.twimg.com/profile_images/987614437887524865/u1WDMgrd.jpg",
          |    "hatena:bookmarkcount":"8"
          |}
        """.stripMargin

      val json = parse(jsonString)

      val rssItem = HatenaRssItem.fromJson(json)
    }
  }

  "convert to json" in {
    val jsonString =
      """
        |{
        |    "about":"https://twitter.com/mazy_3anime/status/1083159787674587136",
        |    "title":"アニメ版まじさん",
        |    "link":"https://twitter.com/mazy_3anime/status/1083159787674587136",
        |    "description":"Bar",
        |    "dc:date":"2019-01-12T03:15:00Z",
        |    "dc:subject":"世の中",
        |    "dc:subject":"アニメ",
        |    "taxo:topics":{
        |      "rdf:Bag":{
        |        "resource":"http://b.hatena.ne.jp/search/tag?q=%E3%82%A2%E3%83%8B%E3%83%A1"
        |      }
        |    },
        |    "content:encoded":"content1",
        |    "hatena:imageurl":"https://pbs.twimg.com/profile_images/987614437887524865/u1WDMgrd.jpg",
        |    "hatena:bookmarkcount":"8"
        |}
      """.stripMargin

    val json = parse(jsonString)

    val rssItem = HatenaRssItem.fromJson(json)

    implicit val formats = DefaultFormats + new HatenaRssItemSerializer()
    val resultString = write(rssItem)

    resultString.length should be > 0

  }
}
