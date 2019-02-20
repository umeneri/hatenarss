package hatenarss.helpers
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write

class Json4sSerializer extends JsonSerializer {
  implicit val formats = DefaultFormats + new HatenaRssItemSerializer()

  override def toJson[T](t: T): String = {
    write(t)
  }
}
