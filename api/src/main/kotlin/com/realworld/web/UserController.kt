package com.realworld.web

import com.realworld.model.api.User
import com.realworld.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User) : Mono<com.realworld.model.domain.user.User> {
       return userService.createUser(user)
    }
}