package com.realworld.model.api

import com.fasterxml.jackson.annotation.JsonProperty
import com.realworld.model.api.Article as ArticleIO


data class Articles(@JsonProperty("articles") var articles: List<ArticleIO> = listOf(),
                    @JsonProperty("articlesCount") var articlesCount: Int = 0)