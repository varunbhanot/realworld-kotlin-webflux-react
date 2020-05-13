package com.realworld.model.api.User

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.realworld.model.domain.user.User as UserModel
@JsonTypeName("user")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
data class User(var email: String = "",
                var token: String = "",
                var username: String = "",
                var bio: String = "",
                var image: String = "") {
    override fun toString(): String = "com.realworld.model.api.User.User($email, $username)"
    companion object {
        fun fromModel(userModel: UserModel): User {
            return User(userModel.email,
                    userModel.token,
                    userModel.username,
                    userModel.bio,
                    userModel.image)
        }
    }

}