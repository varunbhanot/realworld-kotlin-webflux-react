package com.realworld.model.api.User

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("user")
data class UpdatedUser(var email: String = "")