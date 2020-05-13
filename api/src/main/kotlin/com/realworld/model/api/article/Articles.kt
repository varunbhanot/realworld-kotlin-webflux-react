package com.realworld.model.api.article

import com.fasterxml.jackson.annotation.JsonProperty
import com.realworld.model.api.article.Article as ArticleIO


data class Articles(@JsonProperty("articles") var articles: List<ArticleIO> = listOf(),
                    @JsonProperty("articlesCount") var articlesCount: Int = 0)