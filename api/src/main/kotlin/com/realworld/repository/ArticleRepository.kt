package com.realworld.repository

import com.realworld.model.domain.article.Article
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import reactor.core.publisher.Mono
import java.util.*

interface ArticleRepository : ReactiveCassandraRepository<Article, UUID> {
    fun findBySlug(slug: String): Mono<Article>
}