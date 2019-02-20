package hatenarss.services

import play.api.mvc.EssentialAction
import scala.concurrent.duration.FiniteDuration

trait CachedService {
  val expiration: FiniteDuration

  def cachedAction(key: String)(action: EssentialAction): EssentialAction
}
