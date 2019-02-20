package controllers

import hatenarss.services.{CachedService, HatenaEntryService, HatenaEntryServiceWS}
import javax.inject.Inject
import org.scalatestplus.play.PlaySpec
import play.api.Application
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.mvc.EssentialAction
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.duration.{FiniteDuration, _}

class HatenaControllerSpec extends PlaySpec {

  lazy val application: Application = new GuiceApplicationBuilder()
    .overrides(bind[HatenaEntryService].to[HatenaEntryServiceWS])
    .overrides(bind[CachedService].to[MockCachedService])
    .build()

  "HatenaControllerSpec" should {

    "hotentry" in {
      val Some(result) = route(application, FakeRequest(GET, "/hotentry"))

      status(result) mustBe OK
      contentAsString(result).length must be > 0
    }

    "ranking" in {
      val Some(result) = route(application, FakeRequest(GET, "/ranking"))

      status(result) mustBe OK
      contentAsString(result).length must be > 0
    }

    "ranking next page" in {
      val Some(result1) = route(application, FakeRequest(GET, "/ranking"))
      val Some(result2) = route(application, FakeRequest(GET, "/ranking?page=2"))

      status(result1) mustBe OK
      status(result2) mustBe OK
      contentAsString(result1) must not be contentAsString(result2)
    }
  }
}

class MockCachedService @Inject() extends CachedService {
  override val expiration: FiniteDuration = 1.second

  override def cachedAction(key: String)(action: EssentialAction): EssentialAction = action
}
