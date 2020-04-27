package com.realworld.model.api

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.realworld.model.domain.article.Article
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


@JsonRootName("article")
data class NewArticle(@NotNull var title: String? = null,
                      @NotNull var description: String? = null,
                      @NotNull var body: String? = null,
                      @NotEmpty var tagList: List<String> = listOf(),
                      var slug: String = "",
                      var createdAt: String = "",
                      var updatedAt: String = "",
                      var favorited: String = "",
                      var favoritesCount: Int = 0,
                      var author: String = "") {
    companion object {
        fun dateFormat(date: OffsetDateTime): String {
            return date.toZonedDateTime().withZoneSameInstant(ZoneId.of("Z")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
        }

        fun fromModel(model: Article): NewArticle {
            return NewArticle(
                    slug = model.slug,
                    title = model.title,
                    description = model.description,
                    body = model.body,
                    tagList = model.tagList,
                    createdAt = dateFormat(OffsetDateTime.of(model.createdAt, ZoneOffset.UTC)),
                    updatedAt = dateFormat(OffsetDateTime.of(model.updatedAt, ZoneOffset.UTC)),
                    favorited = model.favorited,
                    favoritesCount = model.favoritesCount,
                    author = model.authorName
            )
        }
    }
}