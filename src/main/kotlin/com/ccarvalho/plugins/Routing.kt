package com.ccarvalho.plugins

import com.ccarvalho.dao.DaoFacade
import com.ccarvalho.dao.SubscriptionsDaoFacadeImpl
import com.ccarvalho.models.Subscription
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val subscriptionDao: DaoFacade<Subscription> = SubscriptionsDaoFacadeImpl()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        route("/Subscriptions") {

            post {
                val subscriptionRequest = call.receive<Subscription>()
                val savedSubscription = subscriptionDao.save(subscriptionRequest)
                if (savedSubscription == null) {
                    call.response.status(HttpStatusCode(500, "Subscription error"))
                } else {
                    call.respond(savedSubscription)
                }
            }

            get {
                call.respond(subscriptionDao.findAll())
            }

            get("/{id}") {
                val idRequest = call.parameters["id"]?.toLong()
                val findOne = subscriptionDao.findOne(idRequest!!)
                if (findOne == null) {
                    call.response.status(HttpStatusCode.OK)
                } else {
                    call.respond(findOne)
                }
            }

            delete("/{id}") {
                val idRequest = call.parameters["id"]?.toLong()
                subscriptionDao.delete(idRequest!!)
                call.response.status(HttpStatusCode.OK)
            }

            put("/{id}") {
                val idRequest = call.parameters["id"]?.toLong()
                val subscriptionToUpdate = call.receive<Subscription>()
                val update = subscriptionDao.update(idRequest!!, subscriptionToUpdate)
                call.respond(update)
            }

        }
    }
}
