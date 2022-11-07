package com.nopalsoft.http.server.server.repositories

import com.nopalsoft.http.server.server.GeneralException
import com.nopalsoft.http.server.server.model.User

interface UserRepository {
    fun userList(): List<User>

    fun getUser(id: Int): User

    fun addUser(user: User): User

    fun removeUser(id: Int): User
}

class UserRepositoryImp : UserRepository {
    private val userList = mutableListOf<User>(
        User(1, "John Smith", "john.smith", "john@yahoo.com", "+191223425", "jhnsmth.yahoo.com"),
        User(2, "Sarah Connor", "sarrah", "sconnor@altavista.com", "+1487216423", "killterminator.com"),
        User(3, "Clementine Bauch", "Samantha", "Nathan@yesenia.net", "1-463-123-4447", "ramiro.info"),
    )
    private var idCount = userList.size

    override fun userList(): List<User> = userList

    override fun getUser(id: Int): User {
        userList.find { it.id == id }?.let {
            return it
        }
        throw GeneralException("User not found: $id")
    }

    override fun addUser(user: User): User {
        val newUser = user.copy(id = ++idCount);
        userList.add(newUser)
        return newUser
    }

    override fun removeUser(id: Int): User {
        userList.find { it.id == id }?.let {
            userList.remove(it)
            return it
        }
        throw GeneralException("Cannot remove user: $id")
    }

}