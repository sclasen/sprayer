package example

import spray.testkit.ScalatestRouteTest
import org.scalatest.{ MustMatchers, WordSpec }
import spray.http.{ ContentTypes, HttpData, HttpEntity }

class SprayerHttpServiceSpec extends WordSpec with MustMatchers with ScalatestRouteTest with SprayerHttpService {
  def actorRefFactory = system
  def dynamo = ??? //not needed atm
  def ctx = system.dispatcher

  "A Sprayer" should {

    "return ohaithere from /hello" in {

      Get("/hello") ~> routes ~> check {
        responseAs[String] must equal("ohaithere")
      }

    }

    "get and post json" in {
      import SprayerJsonProtocol._
      import spray.httpx.SprayJsonSupport._

      Get("/json") ~> routes ~> check {
        //the import SprayerJsonProtocol._  pulls in implicit serializer/deserializer
        val outer = responseAs[Outer]   //<-responseAs[Outer] uses the deserializer
        outer.name must equal("outer")
        outer.inner.name must equal("inner")
      }
      //the import SprayerJsonProtocol._  pulls in implicit serializer/deserializer
      //so posting the case class automatically serializes to json
      Post("/json", Outer("post", Inner("post"))) ~> routes ~> check {
        responseAs[String] must equal("got Outer(post,Inner(post))")
      }
    }

  }
}
