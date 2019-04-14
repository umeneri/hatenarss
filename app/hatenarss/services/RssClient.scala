package hatenarss.services

import play.api.libs.ws.{WSClient, WSRequest, WSResponse}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.xml.Node

case class RssClient(ws: WSClient) {
  def read(url: String): Future[Seq[Node]] = {
    val request: WSRequest = ws.url(url)
    val futureResponse: Future[WSResponse] = request.get()

    futureResponse.map { response =>
      val xml = response.xml
      val xml2: Seq[Node] = (xml \ "item").theSeq
      xml2
    }
  }
}
