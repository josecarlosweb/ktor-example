package com.ccarvalho

import com.ccarvalho.dao.DatabaseFactory
import com.ccarvalho.plugins.configureRouting
import com.ccarvalho.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        DatabaseFactory.init()
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
