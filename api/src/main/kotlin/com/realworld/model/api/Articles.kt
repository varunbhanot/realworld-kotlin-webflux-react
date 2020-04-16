package com.realworld.model.api

import com.fasterxml.jackson.annotation.JsonProperty
import com.realworld.model.Article

data class Articles(@JsonProperty("articles") var articles: List<Article> = listOf(),
                    @JsonProperty("articlesCount") var articlesCount: Int = 0)