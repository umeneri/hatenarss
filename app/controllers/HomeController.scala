package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  def index: Action[AnyContent] =  {
    loadIndex()
  }

  def indexAll(path: String): Action[AnyContent] = {
    loadIndex()
  }

  private def loadIndex(): Action[AnyContent] = {
    controllers.Assets.at(path = "/public", file = "index.html")
  }

}
