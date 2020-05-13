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
}