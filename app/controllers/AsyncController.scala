package controllers

import akka.actor.ActorSystem
import hatenarss.services.HatenaRssService
import javax.inject._
import play.api.mvc._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future, Promise}

@Singleton
class AsyncController @Inject()(cc: ControllerComponents,
                                actorSystem: ActorSystem,
                                hatenaRssService: HatenaRssService)
                               (implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  def message: Action[AnyContent] = Action.async {
    getFutureMessage(1.second).map { msg => Ok(msg) }
  }

  private def getFutureMessage(delayTime: FiniteDuration): Future[String] = {
    val promise: Promise[String] = Promise[String]()
    actorSystem.scheduler.scheduleOnce(delayTime) {
      promise.success("Hi!")
    }(actorSystem.dispatcher) // run scheduled tasks using the actor system's dispatcher
    promise.future
  }

  //  [はてなブックマークフィード仕様 - Hatena Developer Center](http://developer.hatena.ne.jp/ja/documents/bookmark/misc/feed)
  def hatena: Action[AnyContent] = Action.async {
    val url = "http://b.hatena.ne.jp/hotentry.rss"
    val jsonFuture = hatenaRssService.getHatenaRssItemJsonString(url)
    jsonFuture.map(Ok(_))
  }
}
