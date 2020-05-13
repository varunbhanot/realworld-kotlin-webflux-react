package com.realworld.model.api.User

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("user")
data class NewUser(var email: String = "",
                   var password: String = "",
                   var token: String = "",
                   var username: String = "") {
    override fun toString(): String = "com.realworld.model.api.User.User($email, $username)"
}