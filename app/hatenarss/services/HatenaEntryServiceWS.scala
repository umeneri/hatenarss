package hatenarss.services

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import hatenarss.models.HatenaRssItem
import javax.inject.Inject
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HatenaEntryServiceWS @Inject()(ws: WSClient) extends HatenaEntryService {
  def getRankingItems(period: String, page: Int = 1): Future[Seq[HatenaRssItem]] = {
    val url = createRankingUrl(period, page)

    println(url)

    getHatenaRssItems(url)
  }

  def getHotEntryItems(category: String): Future[Seq[HatenaRssItem]] = {
    val url = category match {
      case "hotentry" => s"http://b.hatena.ne.jp/hotentry.rss"
      case _ => s"http://b.hatena.ne.jp/hotentry/$category.rss"
    }

    getHatenaRssItems(url)
  }

  private def getHatenaRssItems(url: String): Future[Seq[HatenaRssItem]] = {
    val itemsFuture = RssClient(ws).read(url)

    itemsFuture.map { items =>
      items.map { item =>
        HatenaRssItem.fromXml(item)
      }.sortWith((item1, item2) => item1.bookmarkCount > item2.bookmarkCount)
    }
  }

  private def createRankingUrl(period: String, page: Int): String = {
    val laterReadTagParam = "q=%E3%81%82%E3%81%A8%E3%81%A7%E8%AA%AD%E3%82%80"
    val otherParams = s"safe=on&sort=popular&mode=rss&page=$page"
    val baseUrl = s"http://b.hatena.ne.jp/search/tag?$laterReadTagParam&$otherParams"

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
