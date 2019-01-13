package hatenarss.services

import org.json4s.JValue
import org.json4s.Xml.toJson
import org.json4s.native.JsonMethods
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.xml.{Elem, NodeSeq}

case class RssClient(ws: WSClient) {
  def read(url: String): Future[Seq[JValue]] = {
    val request: WSRequest = ws.url(url)
    val futureResponse: Future[WSResponse] = request.get()

    futureResponse.map { response =>
      val xml = response.xml
      RssClient.getItems(xml)
    }
  }
}

object RssClient extends JsonMethods {
  def xmlToJson(xml: Elem): JValue = toJson(xml)

  def getItems(xml: Elem): Seq[JValue] = {
    val xml2: NodeSeq = xml \ "item"
    xml2.map(toJson(_) \ "item")
  }
}
