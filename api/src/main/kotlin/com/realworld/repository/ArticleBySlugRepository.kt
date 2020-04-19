package com.realworld.repository

import com.realworld.model.domain.article.ArticleBySlug
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository

interface ArticleBySlugRepository : ReactiveCassandraRepository<ArticleBySlug, String> {
}