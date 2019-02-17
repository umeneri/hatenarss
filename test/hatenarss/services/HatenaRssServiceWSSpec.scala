package hatenarss.services

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import hatenarss.models.HatenaRssItem
import org.scalatest.{Matchers, WordSpec}
import play.api.test.WsTestClient

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class HatenaRssServiceWSSpec extends WordSpec with Matchers {

  "hatenarss.services.HatenaRssServiceImpl" should {
    "get hatena rss items from url" in {
      val category = "hotentry"

      WsTestClient.withClient { client =>
        val items: Future[Seq[HatenaRssItem]] = new HatenaEntryServiceWS(client).getHotEntryItems(category)
        val result: Seq[HatenaRssItem] = Await.result(items, 10.seconds)
        result.length should be > 0
      }
    }

    "sort hatena rss items" in {
      val category = "hotentry"

      WsTestClient.withClient { client =>
        val items: Future[Seq[HatenaRssItem]] = new HatenaEntryServiceWS(client).getHotEntryItems(category)
        val result: Seq[HatenaRssItem] = Await.result(items, 10.seconds)

        val bookmarkCounts = result.map(_.bookmarkCount)
        val isSorted = bookmarkCounts.zip(bookmarkCounts.tail).forall { case (n1, n2) => n1 >= n2 }
        isSorted shouldBe true
      }
    }

    "get hatena rss items from ranking url" in {
      val period: String = "all"

      WsTestClient.withClient { client =>
        val items: Future[Seq[HatenaRssItem]] = new HatenaEntryServiceWS(client).getRankingItems(period)
        val result: Seq[HatenaRssItem] = Await.result(items, 10.seconds)
        result.length should be > 0
      }
    }

    "pagenate hatena rss ranking items" in {
      val period: String = "all"

      WsTestClient.withClient { client =>
        val items: Future[Seq[HatenaRssItem]] = new HatenaEntryServiceWS(client).getRankingItems(period, page = 2)
        val result: Seq[HatenaRssItem] = Await.result(items, 10.seconds)
        result.length should be > 0
      }
    }


    "convert period to date strings" in {

      WsTestClient.withClient { client =>
        val hatenaRssService = new HatenaEntryServiceWS(client)
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
