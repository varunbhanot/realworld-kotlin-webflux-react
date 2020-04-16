package com.realworld.web

import com.realworld.model.api.NewArticle
import com.realworld.service.ArticleService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import com.realworld.model.api.Articles as ArticlesIO

@RestController
class ArticleController(@Autowired val service: ArticleService) {

    private val logger = LoggerFactory.getLogger(ArticleController::class.java)

    //TODO Handle date format, author, pagination, security
    @GetMapping("/api/articles")
    fun articles(): Mono<ArticlesIO> {
        logger.info("Getting all articles")
        return service
                .getAllArticles()
                .collectList()
                .map { ArticlesIO(it, it.count()) }
    }

    //TODO Handle date format, author, pagination, security ,slug
    @PostMapping("/api/articles")
    @ResponseStatus(HttpStatus.CREATED)
    fun newArticle(@RequestBody newArticle: NewArticle): Mono<com.realworld.model.api.Article> {
        logger.info(newArticle.toString())
        return service.saveNewArticle(newArticle)
                .map { com.realworld.model.api.Article.fromModel(it) }
    }
}