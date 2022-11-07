package com.nopalsoft.http.server.server.controllers

import com.nopalsoft.http.server.server.model.Post
import com.nopalsoft.http.server.server.services.PostService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.postController() {

    val postService by inject<PostService>()

    get("/posts") {
        call.respond(postService.postList())
    }

    get("/posts/{id}") {
        val id = call.parameters["id"]?.toInt()!!
        call.respond(postService.getPost(id))
    }

    post("/posts") {
        val post = call.receive<Post>()
        call.respond(postService.addPost(post))
    }

    delete("/posts/{id}") {
        val id = call.parameters["id"]?.toInt()!!
        call.respond(postService.removePost(id))
    }
}