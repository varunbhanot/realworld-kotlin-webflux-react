package com.realworld.model.api

import com.fasterxml.jackson.annotation.JsonProperty

data class Tags(@JsonProperty("tags") var tags: List<String> = listOf())