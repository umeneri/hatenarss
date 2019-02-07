package hatenarss.helpers

trait JsonSerializer {
  def toJson[T](t :T): String
}
