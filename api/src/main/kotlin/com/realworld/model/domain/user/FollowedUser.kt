package com.realworld.model.domain.user

import org.springframework.data.cassandra.core.mapping.UserDefinedType

@UserDefinedType("followed_user")
data class FollowedUser(val email: String,
                        val username: String,
                        val bio: String,
                        val image: String)