package requests

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import scenarios.RequestBase

trait LoadingRequests extends RequestBase {

  val itemLoaded: ChainBuilder =
    exec(
      http("Item Loaded")
        .put("http://localhost:8080/itemLoaded")
        .body(ElFileBody("templates/itemLoaded.json"))
        .check(status.is(200))
    )
      //.exitHereIfFailed

  val tsuFinished: ChainBuilder =
    exec(
      http("TSU Finished")
        .put("http://localhost:8080/tsuFinished")
        .body(ElFileBody("templates/tsuFinished.json"))
        .check(status.is(200))
    )
      //.exitHereIfFailed
}
