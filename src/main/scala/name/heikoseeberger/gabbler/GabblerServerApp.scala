/*
 * Copyright 2013 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package name.heikoseeberger.gabbler

import akka.actor.Props
import spray.can.server.SprayCanHttpServerApp

object GabblerServerApp extends App with SprayCanHttpServerApp {

  val gabblerHub = system.actorOf(Props(new GabblerHub), "gabbler-hub")
  val gabblerService = system.actorOf(Props(new GabblerService(gabblerHub)), "gabbler-service")
  newHttpServer(gabblerService) ! Bind(interface = "localhost", port = 8080)

  Console.readLine("Hit ENTER to exit ...")
  system.shutdown()
}
