package com.realworld.model.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore


data class User(var email: String = "",
                @JsonIgnore
                var password: String = "",
                var token: String = "",
                var username: String = "",
                var bio: String = "",
                var image: String = "",
                @JsonIgnore
                var follows: MutableList<User> = mutableListOf())  {
    override fun toString(): String = "User($email, $username)"
}