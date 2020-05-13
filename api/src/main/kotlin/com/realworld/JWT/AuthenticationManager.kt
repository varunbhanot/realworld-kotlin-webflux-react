package com.realworld.JWT

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

@Component
class AuthenticationManager(private val jwtUtil: JWTUtil) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        val authToken = authentication!!.credentials.toString()

        return try {
            if (!jwtUtil.validateToken(authToken)) {
                return Mono.empty()
            }
            val claims = jwtUtil.getAllClaimsFromToken(authToken)
            //val rolesMap: List<String> = claims["role"] as List<String>
            val authorities: MutableList<GrantedAuthority> = ArrayList()
            /*for (roleMap in rolesMap) {
                authorities.add(SimpleGrantedAuthority(roleMap))
            }*/
            Mono.just(UsernamePasswordAuthenticationToken(claims.subject, null, authorities))
        } catch (e: Exception) {
            Mono.empty()
        }

    }
}