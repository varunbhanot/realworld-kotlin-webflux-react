package com.realworld.service

import com.realworld.repository.ArticleRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class TagsService(private val articleRepository: ArticleRepository) {

    fun getAllTags(): Flux<String> {
        return articleRepository
                .findAll()
                .flatMap { Flux.fromIterable(it.tagList) }
                .distinct()
    }
}