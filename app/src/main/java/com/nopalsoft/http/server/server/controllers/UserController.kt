package com.nopalsoft.http.server.server.controllers

import com.nopalsoft.http.server.server.model.User
import com.nopalsoft.http.server.server.model.ResponseBase
import com.nopalsoft.http.server.server.services.UserService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.userController() {

    val userService by inject<UserService>()

    get("/users") {
        call.respond(userService.userList())
    }

    get("/users/{id}") {
        val id = call.parameters["id"]?.toInt()!!
        call.respond(userService.getUser(id))
    }

    post("/users") {
        val person = call.receive<User>()
        call.respond(userService.addUser(person))
    }

    delete("/users/{id}") {
        val id = call.parameters["id"]?.toInt()!!
        call.respond(userService.removeUser(id))
    }
}