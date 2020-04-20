package com.realworld.repository.article

import com.realworld.model.domain.article.ArticleBySlug
import com.realworld.model.domain.article.ArticlesByFavorited
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository

interface ArticleBySlugRepository : ReactiveCassandraRepository<ArticleBySlug, String> {
}