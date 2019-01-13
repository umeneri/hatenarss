package hatenarss.models

import java.time.ZonedDateTime

import hatenarss.helpers.RssItemSerializer
import org.json4s.{DefaultFormats, JValue}

case class RssItem(title: String,
                   description: String,
                   link: String,
                   imageurl: String,
                   bookmarkcount: Int,
                   datetime: ZonedDateTime,
                  )

object RssItem {
  def parse(json: JValue): RssItem = {
    implicit val formats = DefaultFormats + new RssItemSerializer()

    json.extract[RssItem]
  }
}

