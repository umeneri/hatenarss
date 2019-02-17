package controllers

import hatenarss.services.{HatenaEntryService, HatenaEntryServiceWS}
import org.scalatestplus.play.PlaySpec
import play.api.Application
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.FakeRequest
import play.api.test.Helpers._

class HatenaControllerSpec extends PlaySpec {

  lazy val application: Application = new GuiceApplicationBuilder()
    .bindings(bind[HatenaEntryService].to[HatenaEntryServiceWS])
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
