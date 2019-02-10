package hatenarss.models

import java.time.ZonedDateTime

import hatenarss.helpers.HatenaRssItemSerializer
import org.json4s.native.Serialization.write
import org.json4s.{DefaultFormats, JValue}

import scala.xml.Node

case class HatenaRssItem(title: String,
                         description: String,
                         link: String,
                         imageurl: String,
                         bookmarkcount: Int,
                         datetime: ZonedDateTime)

object HatenaRssItem {
  def fromXml(item: Node): HatenaRssItem = {
    val title = (item \ "title").text
    val link = (item \ "link").text
    val description = (item \ "description").text
    val datetime: ZonedDateTime = HatenaRssItemSerializer.parseToZonedDateTime((item \ "date").text)
    val subjects = (item \\ "subject").toList.map(_.text)
    val imageurl = (item \ "imageurl").text
    val bookmarkcount = (item \ "bookmarkcount").text.toInt

    HatenaRssItem(title, description, link, imageurl, bookmarkcount, datetime)
  }

  implicit val formats = DefaultFormats + new HatenaRssItemSerializer()

  def fromJson(json: JValue): HatenaRssItem = {

    json.extract[HatenaRssItem]
  }

  def renderJson(items: Seq[HatenaRssItem]): String = {
    write(items)
  }
}
