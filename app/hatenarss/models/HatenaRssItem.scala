package hatenarss.models

import java.time.ZonedDateTime

import hatenarss.helpers.RssItemSerializer
import org.json4s.{DefaultFormats, JValue}

case class HatenaRssItem(title: String,
                         description: String,
                         link: String,
                         imageurl: String,
                         bookmarkcount: Int,
                         datetime: ZonedDateTime,
                  )

object HatenaRssItem {
  def parse(json: JValue): HatenaRssItem = {
    implicit val formats = DefaultFormats + new RssItemSerializer()

    json.extract[HatenaRssItem]
  }
}
