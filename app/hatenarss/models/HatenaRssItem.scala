package hatenarss.models

import java.time.ZonedDateTime

import hatenarss.helpers.HatenaRssItemSerializer
import org.json4s.{DefaultFormats, JValue}
import org.json4s.native.Serialization.write

case class HatenaRssItem(title: String,
                         description: String,
                         link: String,
                         imageurl: String,
                         bookmarkcount: Int,
                         datetime: ZonedDateTime,
                  )

object HatenaRssItem {
  implicit val formats = DefaultFormats + new HatenaRssItemSerializer()

  def fromJson(json: JValue): HatenaRssItem = {

    json.extract[HatenaRssItem]
  }

  def renderJson(items: Seq[HatenaRssItem]): String = {
    write(items)
  }
}
