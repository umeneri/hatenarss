package controllers

import hatenarss.helpers.JsonSerializer
import hatenarss.services.HatenaEntryService
import javax.inject._
import play.api.cache.Cached
import play.api.mvc._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

@Singleton
class HatenaController @Inject()(cc: ControllerComponents,
                                 hatenaRssService: HatenaEntryService,
                                 jsonSerializer: JsonSerializer,
                                 cached: Cached,
                                )
                                (implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  val expiration: FiniteDuration = 1.hour

  //  [はてなブックマークフィード仕様 - Hatena Developer Center](http://developer.hatena.ne.jp/ja/documents/bookmark/misc/feed)
  def hotEntry(category: String, page: Int): EssentialAction =
    cached({ _: RequestHeader => s"hotentry-$category-$page" }, expiration) {
      Action.async {
        val futureItems = hatenaRssService.getHotEntryItems(category, page)
        futureItems.map { items => Ok(jsonSerializer.toJson(items)) }
      }
    }

  def ranking(period: String, page: Int): EssentialAction =
    cached({ _: RequestHeader => s"ranking-$period-$page" }, expiration) {
      Action.async {
        val futureItems = hatenaRssService.getRankingItems(period, page)

        futureItems.map { items => Ok(jsonSerializer.toJson(items)) }
      }
    }
}
