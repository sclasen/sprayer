package example

import akka.actor.{ Props, ActorSystem }
import akka.io.IO
import akka.pattern._
import spray.can.Http
import scala.util.{ Failure, Success }
import scala.concurrent.duration._
import org.slf4j.LoggerFactory
import akka.io.Tcp.Bound
import com.sclasen.spray.aws.dynamodb.{ DynamoDBClient, DynamoDBClientProps }

object SprayerMain extends App {

  val log = LoggerFactory.getLogger("sprayer")

  implicit val system = ActorSystem("sprayer")
  implicit val ctx = system.dispatcher

  val dynamoProps = DynamoDBClientProps(
    sys.env("AWS_ACCESS_KEY_ID"),
    sys.env("AWS_SECRET_ACCESS_KEY"),
    10 seconds,
    system,
    system)

  val serviceActor = system.actorOf(Props(classOf[SprayerHttpActor], dynamoProps), "the-service-actor")

  val port = sys.env.get("PORT").map(_.toInt).getOrElse {
    log.warn("PORT not set defaulting to 8080")
    8080
  }

  IO(Http).ask(Http.Bind(serviceActor, "0.0.0.0", port))(10 seconds).mapTo[Http.Bound].onComplete {
    case Success(Bound(_)) =>
      log.info(s"bound to $port")
    case Failure(exception) =>
      log.error("failed to bind to port, exiting", exception)
      System.exit(666)
  }

}
