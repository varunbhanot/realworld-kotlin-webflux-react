package com.realworld.model.api

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonRootName("user")
data class User(var email: String = "",
                @JsonIgnore
                var password: String = "",
                var token: String = "",
                var username: String = "",
                var bio: String = "",
                var image: String = "") {
    override fun toString(): String = "User($email, $username)"
}