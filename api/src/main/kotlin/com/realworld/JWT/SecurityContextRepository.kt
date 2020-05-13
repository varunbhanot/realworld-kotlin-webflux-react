package com.realworld.JWT

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Component
class SecurityContextRepository : ServerSecurityContextRepository {
    @Autowired
    private val authenticationManager: AuthenticationManager? = null
    override fun save(swe: ServerWebExchange, sc: SecurityContext): Mono<Void> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun load(swe: ServerWebExchange): Mono<SecurityContext> {
        val request: org.springframework.http.server.reactive.ServerHttpRequest = swe.request
        val authHeader: String? = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        if (authenticationManager != null) {
            return if (authHeader != null && authHeader.startsWith("Token")) {
                val authToken = authHeader.substring(6)
                val auth: Authentication = UsernamePasswordAuthenticationToken(authToken, authToken)
                authenticationManager.authenticate(auth).map { authentication -> SecurityContextImpl(authentication) }
            } else {
                return Mono.empty()
            }
        }
        return Mono.empty()
    }
}