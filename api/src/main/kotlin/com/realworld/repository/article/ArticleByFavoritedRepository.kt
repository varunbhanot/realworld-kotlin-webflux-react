package com.realworld.repository.article

import com.realworld.model.domain.article.ArticleByFavorited
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import reactor.core.publisher.Flux

interface ArticleByFavoritedRepository : ReactiveCassandraRepository<ArticleByFavorited, String> {
    fun findByFavorited(favorited: String): Flux<ArticleByFavorited>
}