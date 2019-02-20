package hatenarss.services

import javax.inject.Inject
import play.api.cache.Cached
import play.api.mvc.{EssentialAction, RequestHeader}

import scala.concurrent.duration.{FiniteDuration, _}

class PlayCachedService @Inject()(cached: Cached) extends CachedService {
  override val expiration: FiniteDuration = 1.hour

  override def cachedAction(key: String)(action: EssentialAction): EssentialAction = {
    cached({ _: RequestHeader => key }, expiration) {
      action
    }
  }

}
