package example

import spray.json.DefaultJsonProtocol

case class Inner(name: String)
case class Outer(name: String, inner: Inner)

object SprayerJsonProtocol extends DefaultJsonProtocol {
  implicit val InnerFormat = jsonFormat1(Inner)
  implicit val OuterFormat = jsonFormat2(Outer)
}
