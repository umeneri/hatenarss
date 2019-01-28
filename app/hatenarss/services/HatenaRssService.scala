package hatenarss.services

import scala.concurrent.Future

trait HatenaRssService  {
  def getHatenaRssItemJsonString(url: String): Future[String]
}
