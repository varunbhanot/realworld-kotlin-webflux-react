package com.realworld.repository.user

import com.realworld.model.domain.user.User
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository

interface UserRepository : ReactiveCassandraRepository<User, String> {
}