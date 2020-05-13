package com.realworld.service

import com.realworld.JWT.JWTUtil
import com.realworld.model.api.User.LoginUser
import com.realworld.model.api.User.NewUser
import com.realworld.model.domain.user.User
import com.realworld.repository.user.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class UserService(private val userRepository: UserRepository, private val bCryptPasswordEncoder: BCryptPasswordEncoder, private val jwtUtil: JWTUtil) {

    fun createUser(newUser: NewUser): Mono<User> {

        val user = newUser.let {
            User(it.email,
                    bCryptPasswordEncoder.encode(it.password),
                    "",
                    it.username,
                    "",
                    "",
                    listOf()
            )
        }

        return userRepository.save(user)
    }

    fun existsById(username: String): Mono<Boolean> {
        return userRepository.existsById(username)
    }

    fun login(loginUser: LoginUser): Mono<User> {
        return findByUsername(loginUser.username)
                .filter { bCryptPasswordEncoder.matches(loginUser.password, it.password) }
                .map { it.copy(token = jwtUtil.generateToken(it)) }
    }

    fun findByUsername(username: String): Mono<User> {
        return userRepository.findById(username)
    }

}