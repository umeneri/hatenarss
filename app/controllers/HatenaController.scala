package controllers

import hatenarss.services.HatenaRssService
import hatenarss.helpers.JsonSerializer
import hatenarss.models.HatenaRssItem
import javax.inject._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HatenaController @Inject()(cc: ControllerComponents,
                                 hatenaRssService: HatenaRssService,
                                 jsonSerializer: JsonSerializer
                                )
                                (implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  //  [はてなブックマークフィード仕様 - Hatena Developer Center](http://developer.hatena.ne.jp/ja/documents/bookmark/misc/feed)
  def hatena(keyword: String): Action[AnyContent] = Action.async {
    val itemsFuture: Future[Seq[HatenaRssItem]] = hatenaRssService.getHatenaRssItems(keyword)

    itemsFuture.map { items => Ok(jsonSerializer.toJson(items)) }
  }

  def ranking(period: String): Action[AnyContent] = Action.async {
    val itemsFuture: Future[Seq[HatenaRssItem]] = hatenaRssService.getRankingItems(period)

    itemsFuture.map { items => Ok(jsonSerializer.toJson(items)) }
  }
}
