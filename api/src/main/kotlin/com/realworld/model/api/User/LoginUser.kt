package com.realworld.model.api.User

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("user")
data class LoginUser(var username: String = "", var password: String = "")