package com.realworld.model.domain.user

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("users")
data class User(val email: String,
                val password: String,
                val token: String,
                @PrimaryKey val username: String,
                val bio: String,
                val image: String,
                val follows: List<FollowedUser>) {
    override fun toString(): String = "User($email, $username)"
}