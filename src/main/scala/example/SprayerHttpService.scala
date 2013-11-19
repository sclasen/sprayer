package example

import spray.routing.{ HttpServiceActor, HttpService }
import akka.actor.Actor
import com.sclasen.spray.aws.dynamodb.{ DynamoDBClientProps, DynamoDBClient }
import com.amazonaws.services.dynamodbv2.model.{ ListTablesResult, ListTablesRequest }
import collection.JavaConverters._
import scala.concurrent.ExecutionContext

trait SprayerHttpService extends HttpService {

  def routes = path("hello") {
    get {
      complete("ohaithere")
    }
  } ~ path("goodbye") {
    get {
      complete("buh-bye")
    }
  } ~ path("tables")(listTables)

  def listTables = complete {
    //Notice this is async, as the dynamo call returns a future
    dynamo.sendListTables(new ListTablesRequest()).map {
      case r: ListTablesResult => r.getTableNames.asScala.reduceLeft(_ + ", " + _)
    }
  }

  def dynamo: DynamoDBClient
  implicit def ctx: ExecutionContext
}

class SprayerHttpActor(dynamoProps: DynamoDBClientProps) extends HttpServiceActor with SprayerHttpService {
  def receive: Actor.Receive = runRoute(routes)
  val dynamo = new DynamoDBClient(dynamoProps)
  val ctx = context.system.dispatcher
}

