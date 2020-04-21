package com.realworld.model.domain.article

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("articles_by_favorited")
data class ArticleByFavorited(
        val slug: String,
        val articleId: UUID,
        val title: String,
        val description: String,
        val body: String,
        val tagList: List<String>,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        @PrimaryKey val favorited: String,
        val favoritesCount: Int,
        val authorName: String)