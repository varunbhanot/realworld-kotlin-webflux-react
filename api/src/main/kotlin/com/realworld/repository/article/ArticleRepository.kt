package com.realworld.repository.article

import com.realworld.model.domain.article.Article
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import reactor.core.publisher.Flux
import java.util.*

interface ArticleRepository : ReactiveCassandraRepository<Article, UUID> {
    fun findByTagListContaining(tag: String): Flux<Article>
}