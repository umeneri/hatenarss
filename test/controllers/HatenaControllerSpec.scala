package controllers

import hatenarss.services.{HatenaRssService, HatenaRssServiceImpl}
import org.scalatestplus.play.PlaySpec
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.FakeRequest
import play.api.test.Helpers._

class HatenaControllerSpec extends PlaySpec {

  lazy val application = new GuiceApplicationBuilder()
    .bindings(bind[HatenaRssService].to[HatenaRssServiceImpl])
    .build()

  "HatenaControllerSpec" should {

    "hatena" in {
      val Some(result) = route(application, FakeRequest(GET, "/hatena"))


      status(result) mustBe OK
    }
  }
}
