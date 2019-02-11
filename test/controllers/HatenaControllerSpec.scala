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
    }
  }
}
