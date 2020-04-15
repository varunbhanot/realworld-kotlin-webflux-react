package com.realworld.model.api

import com.realworld.model.Article

data class Articles(var articles: List<Article> = listOf(),
                    var articlesCount: Int = 0)