package hatenarss.models

import org.json4s.native.JsonMethods._
import org.scalatest.WordSpec

class RssItemSpec extends WordSpec {
  "hatenarss.models.RssItem" should {
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

      val rssItem = RssItem.parse(json)
    }
  }
}
