package controllers

import hatenarss.services.HatenaRssService
import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HatenaController @Inject()(cc: ControllerComponents,
                                 hatenaRssService: HatenaRssService)
                                (implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  //  [はてなブックマークフィード仕様 - Hatena Developer Center](http://developer.hatena.ne.jp/ja/documents/bookmark/misc/feed)
  def hatena: Action[AnyContent] = Action.async {
    val url = "http://b.hatena.ne.jp/hotentry.rss"
    val jsonFuture = hatenaRssService.getHatenaRssItemJsonString(url)
    jsonFuture.map(Ok(_))
  }
}
