import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.RampOpenInjection
import scenarios.LoadItemsScenario

import scala.concurrent.duration._
import scala.language.postfixOps

class TemplateSimulation extends Simulation  with LoadItemsScenario {

  val usersPerMinute = 1000

  setUp(
    loadItems.inject(setupUsers(durationInMinutes, usersPerMinute, loadFactor)).protocols(httpProtocol)
    // Add additional scenarios here to run concurrently
  ).assertions(
    global.failedRequests.count.is(0),
    global.responseTime.percentile1.lt(1000), // 50% of the calls
    global.responseTime.percentile3.lt(3000), // 95% of the calls
    global.responseTime.max.lt(5000) // all the calls
  )

  def setupUsers(minutes: Int, perMinute: Int, loadFactor: Double): RampOpenInjection =
    rampUsers(Math.ceil(perMinute * minutes * loadFactor).toInt).during(minutes minutes)

}
