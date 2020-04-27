package com.realworld.service

import com.realworld.model.domain.user.User
import com.realworld.repository.user.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import com.realworld.model.api.User as UserIO

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(userIO: UserIO): Mono<User> {
        val user = userIO.let {
            User(it.email,
                    "",
                    "",
                    it.username,
                    it.bio,
                    it.image,
                    listOf()
            )
        }
        return userRepository.save(user)
    }
}