package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String? = null, lastName: String? = null) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

//    constructor(id: String) : this(id, firstName = null, lastName = null, avatar = null)

    init {
        println("user created: '$id' '$firstName' '$lastName'")
    }

    fun printMe() {
        println(
            """
    printing user info:
        id = $id
        firstName = $firstName
        lastName = $lastName
        avatar = $avatar
        rating = $rating
        respect = $respect
        lastVisit = $lastVisit
        isOnline = $isOnline

        """.trimIndent()
        )
    }


    companion object Factory {
        var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(
                lastId.toString(),
                firstName = firstName,
                lastName = lastName
            );
        }
    }

}