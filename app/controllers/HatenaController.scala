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

  //  [はてなブックマークフィード仕様 - Hatena Developer Center](http://developer.hatena.ne.jp/ja/documents/bookmark/misc/feed)
  def hotentry(category: String): EssentialAction =
    cached({ _: RequestHeader => s"hotentry-$category" }, 5.minutes) {
      Action.async {
        val futureItems = hatenaRssService.getHotEntryItems(category)
        futureItems.map { items => Ok(jsonSerializer.toJson(items)) }
      }
    }

  def ranking(period: String): EssentialAction =
    cached({ _: RequestHeader => s"ranking-$period" }, 5.minutes) {
      Action.async {
        val futureItems = hatenaRssService.getRankingItems(period)

        futureItems.map { items => Ok(jsonSerializer.toJson(items)) }
      }
    }
}
