package com.realworld.model.domain.article

import org.springframework.data.cassandra.core.mapping.Indexed
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("articles")
data class Article(
        @PrimaryKey val articleId: UUID,
        val slug: String,
        val title: String,
        val description: String,
        val body: String,
        @Indexed val tagList: List<String>,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        val favorited: String,
        val favoritesCount: Int,
        val authorName: String)