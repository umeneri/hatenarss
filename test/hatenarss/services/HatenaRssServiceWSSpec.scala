package hatenarss.services

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import hatenarss.models.HatenaRssItem
import org.joda.time.DateTimeUtils
import org.scalatest.{Matchers, WordSpec}
import play.api.test.WsTestClient

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class HatenaRssServiceWSSpec extends WordSpec with Matchers {

  "hatenarss.services.HatenaRssServiceImpl" should {
    "test" in {
      val a = Seq(1,2,3,5)
      println(a.sortWith((a,b) => a > b))

    }
    "get hatena rss items from url" in {
//      val url = "http://b.hatena.ne.jp/entrylist?sort=hot&threshold=3&url=https%3A%2F%2Ftwitter.com&mode=rss"
      val category = "hotentry"

      WsTestClient.withClient { client =>
        val items: Future[Seq[HatenaRssItem]] = new HatenaRssServiceWS(client).getHotEntryItems(category)
        val result: Seq[HatenaRssItem] = Await.result(items, 10.seconds)
        result.length should be > 0
      }
    }

    "sort hatena rss items" in {
      //      val url = "http://b.hatena.ne.jp/entrylist?sort=hot&threshold=3&url=https%3A%2F%2Ftwitter.com&mode=rss"
      val category = "hotentry"

      WsTestClient.withClient { client =>
        val items: Future[Seq[HatenaRssItem]] = new HatenaRssServiceWS(client).getHotEntryItems(category)
        val result: Seq[HatenaRssItem] = Await.result(items, 10.seconds)

        val bookmarkCounts = result.map(_.bookmarkCount)
        val isSorted = bookmarkCounts.zip(bookmarkCounts.tail).forall { case (n1, n2) => n1 >= n2 }
        isSorted shouldBe true
      }
    }

    "get hatena rss items from ranking url" in {
      val period: String = "all"

      WsTestClient.withClient { client =>
        val items: Future[Seq[HatenaRssItem]] = new HatenaRssServiceWS(client).getRankingItems(period)
        val result: Seq[HatenaRssItem] = Await.result(items, 10.seconds)
        result.length should be > 0
      }
    }

    "convert period to date strings" in {

      WsTestClient.withClient { client =>
        val hatenaRssService = new HatenaRssServiceWS(client)
        val f = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val today = ZonedDateTime.now

        val (dateBegin, dateEnd) = hatenaRssService.periodToDates("daily")
        dateBegin.format(f) shouldBe today.format(f)
        dateEnd.format(f) shouldBe today.format(f)

        val (weekBegin, weekEnd) = hatenaRssService.periodToDates("weekly")
        weekBegin.format(f) shouldBe today.minusDays(7).format(f)
        weekEnd.format(f) shouldBe today.format(f)

        val (monthBegin, monthEnd) = hatenaRssService.periodToDates("monthly")
        monthBegin.format(f) shouldBe today.minusDays(30).format(f)
        monthEnd.format(f) shouldBe today.format(f)

        val (yearBegin, yearEnd) = hatenaRssService.periodToDates("annually")
        yearBegin.format(f) shouldBe today.minusDays(365).format(f)
        yearEnd.format(f) shouldBe today.format(f)
      }
    }
  }
}
