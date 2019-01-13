package hatenarss.helpers

import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId, ZonedDateTime}

import hatenarss.models.HatenaRssItem
import org.json4s.CustomSerializer
import org.json4s.JsonAST.JObject
import org.json4s.JsonDSL._

class RssItemSerializer extends CustomSerializer[HatenaRssItem](format => ( {
  case jObject: JObject =>
    implicit val fmt = format

    val title = (jObject \ "title").extract[String]
    val description = (jObject \ "description").extract[String]
    val link = (jObject \ "link").extract[String]
    val imageurl = (jObject \ "hatena:imageurl").extract[String]
    val bookmarkcount = (jObject \ "hatena:bookmarkcount").extract[String].toInt
    val datetimeStr = (jObject \ "dc:date").extract[String]
    val datetime = RssItemSerializer.parseToZonedDateTime(datetimeStr)

    HatenaRssItem(title, description, link, imageurl, bookmarkcount, datetime)
}, {
  case rssItem: HatenaRssItem => ("title" -> rssItem.title) ~
    ("description" -> rssItem.description) ~
    ("link" -> rssItem.link) ~
    ("hatena:imageurl" -> rssItem.imageurl) ~
    ("hatena:bookmarkcount" -> rssItem.bookmarkcount) ~
    ("dc:date" -> RssItemSerializer.format(rssItem.datetime))
}
))

object RssItemSerializer {
  private def format(t: ZonedDateTime): String =
    t.format(DateTimeFormatter.ISO_INSTANT)

  private def parseToZonedDateTime(t: String): ZonedDateTime = {
    Instant.parse(t).atZone(ZoneId.systemDefault())
  }
}
