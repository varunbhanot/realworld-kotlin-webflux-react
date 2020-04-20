package com.realworld.repository.article

import com.realworld.model.domain.article.ArticlesByFavorited
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import reactor.core.publisher.Flux

interface ArticleByFavoritedRepository : ReactiveCassandraRepository<ArticlesByFavorited, String> {
    fun findByFavorited(favorited: String): Flux<ArticlesByFavorited>
}