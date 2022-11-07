package com.nopalsoft.http.server.server.services

import com.nopalsoft.http.server.server.GeneralException
import com.nopalsoft.http.server.server.MissingParamsException
import com.nopalsoft.http.server.server.model.Post
import com.nopalsoft.http.server.server.model.User
import com.nopalsoft.http.server.server.repositories.PostRepository
import com.nopalsoft.http.server.server.repositories.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PostService : KoinComponent {

    private val postRepository by inject<PostRepository>()

    fun postList(): List<Post> = postRepository.postList()

    fun addPost(post: Post): Post {
        if (post.body == null)
            throw MissingParamsException("body")
        if (post.title == null)
            throw MissingParamsException("title")
        return postRepository.addPost(post)
    }

    fun removePost(id: Int): Post = postRepository.removePost(id)

    fun getPost(id: Int): Post = postRepository.getPost(id)
}