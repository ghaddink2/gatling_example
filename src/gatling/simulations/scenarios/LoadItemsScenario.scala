package scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import requests.LoadingRequests

trait LoadItemsScenario extends LoadingRequests {
  def itemsLoadedFeed = csv("data/itemLoadedFeed2.csv")
  def tsuFinishedFeed = csv("data/tsuFinishedFeed2.csv")

  val loadItems: ScenarioBuilder = scenario("Load Items test")
    .group("loadAndExecute") {
      feed(itemsLoadedFeed)
        .exec(itemLoaded)
        .feed(tsuFinishedFeed)
        .exec(tsuFinished)
    }
}
