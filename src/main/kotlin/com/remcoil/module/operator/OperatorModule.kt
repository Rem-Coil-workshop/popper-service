package com.remcoil

import com.remcoil.service.operator.OperatorService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.*
import org.kodein.di.ktor.closestDI

fun Application.operatorModule() {
    val service: OperatorService by closestDI().instance()

    routing {
        route("/operator") {

            post("/sign_in") {
                val token = service.getOperator(call.receive())
                if (token == null) {
                    call.respond(HttpStatusCode.Unauthorized)
                } else {
                    call.respond(hashMapOf("token" to token))
                }
            }

            post("/sign_up") {
                val token = service.createOperator(call.receive())
                call.respond(hashMapOf("token" to token))
            }
        }
    }
}