package hatenarss.services

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import hatenarss.models.HatenaRssItem
import javax.inject.Inject
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HatenaRssServiceWS @Inject()(ws: WSClient) extends HatenaRssService {
  def getRankingItems(period: String): Future[Seq[HatenaRssItem]] = {
    val laterReadTagParam = "q=%E3%81%82%E3%81%A8%E3%81%A7%E8%AA%AD%E3%82%80"
    val otherParams = "safe=on&sort=popular&mode=rss"
    val baseUrl = s"http://b.hatena.ne.jp/search/tag?$laterReadTagParam&$otherParams"

    val url = createRankingUrl(period, baseUrl)

    val itemsFuture = RssClient(ws).read(url)

    itemsFuture.map { items =>
      items.map { item =>
        HatenaRssItem.fromXml(item)
      }
    }
  }

  def getHatenaRssItems(keyword: String): Future[Seq[HatenaRssItem]] = {
    val url = s"http://b.hatena.ne.jp/$keyword.rss"

    val itemsFuture = RssClient(ws).read(url)

    itemsFuture.map { items =>
      items.map { item =>
        println(item)
        HatenaRssItem.fromXml(item)
      }
    }
  }

  private def createRankingUrl(period: String, baseUrl: String) = {
    period match {
      case "all" => baseUrl
      case p =>
        val (dateBegin, dateEnd) = periodToDates(p)
        val f = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateParams = s"date_begin=${dateBegin.format(f)}&date_end=${dateEnd.format(f)}"

        s"$baseUrl&$dateParams"
    }
  }

  private[services] def periodToDates(period: String): (ZonedDateTime, ZonedDateTime) = {
    val today = ZonedDateTime.now

    period match {
      case "annually" => (today.minusDays(365), today)
      case "monthly" => (today.minusDays(30), today)
      case "weekly" => (today.minusDays(7), today)
      case _ => (today, today)
    }
  }

}
