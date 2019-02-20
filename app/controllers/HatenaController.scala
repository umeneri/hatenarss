package controllers

import hatenarss.helpers.JsonSerializer
import hatenarss.services.{CachedService, HatenaEntryService, PlayCachedService}
import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HatenaController @Inject()(cc: ControllerComponents,
                                 hatenaRssService: HatenaEntryService,
                                 jsonSerializer: JsonSerializer,
                                 cachedService: CachedService
                                )
                                (implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  //  [はてなブックマークフィード仕様 - Hatena Developer Center](http://developer.hatena.ne.jp/ja/documents/bookmark/misc/feed)
  def hotEntry(category: String, page: Int): EssentialAction =
    cachedService.cachedAction(s"hotentry-$category-$page") {
      Action.async {
        val futureItems = hatenaRssService.getHotEntryItems(category, page)
        futureItems.map { items => Ok(jsonSerializer.toJson(items)) }
      }
    }

  def ranking(period: String, page: Int): EssentialAction =
    cachedService.cachedAction(s"ranking-$period-$page") {
      Action.async {
        val futureItems = hatenaRssService.getRankingItems(period, page)

        futureItems.map { items => Ok(jsonSerializer.toJson(items)) }
      }
    }
}
