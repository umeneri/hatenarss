package hatenarss.models

import hatenarss.helpers.HatenaRssItemSerializer
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization.write
import org.json4s._
import org.scalatest.{Matchers, WordSpec}

class HatenaRssItemSpec extends WordSpec with Matchers {
  "hatenarss.models.HatenaRssItem" should {
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

      val rssItem = HatenaRssItem.parse(json)
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

    val rssItem = HatenaRssItem.parse(json)

    implicit val formats = DefaultFormats + new HatenaRssItemSerializer()
    val resultString = write(rssItem)

    resultString.length should be > 0

  }
}
