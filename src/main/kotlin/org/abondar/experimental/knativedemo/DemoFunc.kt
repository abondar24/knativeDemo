package org.abondar.experimental.knativedemo

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory


fun main(){
    val logger = LoggerFactory.getLogger("DemoFunc")

    val port = System.getenv("PORT")?.toInt() ?: 8080

    val host = System.getenv("HOSTNAME") ?: "localhost"

    logger.info("Starting on $port")
     embeddedServer(Netty, port) {
         routing {
             get ("/"){
                 call.respondText("Demo func up is on $host!!\n", ContentType.Text.Html)
             }
         }
     }.start(true)
}