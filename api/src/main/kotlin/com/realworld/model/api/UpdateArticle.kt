package com.realworld.model.api

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("article")
data class UpdateArticle(var title: String? = null,
                         var description: String? = null,
                         var body: String? = null,
                         var tagList: List<String>? = null)