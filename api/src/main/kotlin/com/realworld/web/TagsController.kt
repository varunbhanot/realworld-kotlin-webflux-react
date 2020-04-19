package com.realworld.web

import com.realworld.service.TagsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import com.realworld.model.api.Tags as TagsIO

@RestController
class TagsController(private val tagsService: TagsService) {

    @GetMapping("/api/tags")
    fun getAllTags(): Mono<TagsIO> {
        return tagsService.getAllTags().collectList().map { TagsIO(it) }
    }
}