package com.realworld.web

import com.realworld.model.api.User.LoginUser
import com.realworld.model.api.User.NewUser
import com.realworld.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import com.realworld.model.api.User.User as UserIO

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody newUser: NewUser): Mono<ResponseEntity<UserIO>> {
        return userService.findByUsername(newUser.username)
                .map { ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UserIO()) }
                .switchIfEmpty(createUserResponse(newUser))
    }

    private fun createUserResponse(newUser: NewUser): Mono<ResponseEntity<com.realworld.model.api.User.User>> {
        return userService.createUser(newUser)
                .map { UserIO.fromModel(it) }
                .map { ResponseEntity.status(HttpStatus.OK).body(it) }
    }


    @PostMapping("/api/users/login")
    fun login(@RequestBody loginUser: LoginUser): Mono<ResponseEntity<UserIO>> {
        return userService.login(loginUser)
                .map { ResponseEntity.ok(UserIO.fromModel(it)) }
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build())
    }

    @GetMapping("/api/user")
    fun currentUser(authentication: Authentication): Mono<ResponseEntity<UserIO>> {
        return userService.findByUsername(authentication.name)
                .map { ResponseEntity.ok(UserIO.fromModel(it)) }
    }

}