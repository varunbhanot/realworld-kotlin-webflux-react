package com.realworld.config.spring

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.QueryLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun queryLogger(cluster: Cluster): QueryLogger? {
        val queryLogger = QueryLogger.builder()
                .build()
        cluster.register(queryLogger)
        return queryLogger
    }
//    @Bean
//    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
//
//
//    @Bean
//    @Throws(Exception::class)
//    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
//        return http
//                .csrf().disable()
//                .authorizeExchange()
//                .pathMatchers(HttpMethod.GET, "/articles/**").permitAll()
//                .pathMatchers("/posts/**").authenticated()
//                .anyExchange().permitAll()
//                .and()
//                .httpBasic() //.pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
//                .and()
//                .build()
//    }

}