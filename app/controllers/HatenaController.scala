package controllers

import hatenarss.helpers.JsonSerializer
import hatenarss.services.HatenaRssService
import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HatenaController @Inject()(cc: ControllerComponents,
                                 hatenaRssService: HatenaRssService,
                                 jsonSerializer: JsonSerializer
                                )
                                (implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  //  [はてなブックマークフィード仕様 - Hatena Developer Center](http://developer.hatena.ne.jp/ja/documents/bookmark/misc/feed)
  def hotentry(category: String): Action[AnyContent] = Action.async {
    val itemsFuture = hatenaRssService.getHotEntryItems(category)

    itemsFuture.map { items => Ok(jsonSerializer.toJson(items)) }
  }

  def ranking(period: String): Action[AnyContent] = Action.async {
    val itemsFuture = hatenaRssService.getRankingItems(period)

    itemsFuture.map { items => Ok(jsonSerializer.toJson(items)) }
  }
}
