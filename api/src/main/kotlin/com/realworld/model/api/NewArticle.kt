package com.realworld.model.api

import com.fasterxml.jackson.annotation.JsonRootName
import javax.validation.constraints.NotNull

@JsonRootName(value = "article")
data class NewArticle(@NotNull var title: String = "",
                      @NotNull var description: String = "",
                      @NotNull var body: String = "",
                      var tagList: List<String> = listOf()
)