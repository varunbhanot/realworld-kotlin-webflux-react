package com.realworld.model

import com.fasterxml.jackson.annotation.JsonRootName
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table
@JsonRootName(value = "article")
data class Article(
        @PrimaryKey  val articleId: UUID,
        val slug: String,
        val title: String,
        val description: String,
        val body: String,
        val tagList: List<String>,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        val favorited: Boolean,
        val favoritesCount: Int,
        val authorId: UUID) {

}