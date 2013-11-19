package example

import spray.testkit.ScalatestRouteTest
import org.scalatest.{ MustMatchers, WordSpec }

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

  }
}
