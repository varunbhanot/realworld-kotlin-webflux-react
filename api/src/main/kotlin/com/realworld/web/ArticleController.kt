package com.realworld.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.realworld.service.ArticleService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import com.realworld.model.api.Article as ArticleIO
import com.realworld.model.api.Articles as ArticlesIO
import com.realworld.model.api.NewArticle as NewArticleIO


@RestController
class ArticleController(@Autowired val service: ArticleService) {

    private val logger = LoggerFactory.getLogger(ArticleController::class.java)

    //TODO pagination,security
    @GetMapping("/api/articles")
    fun articles(@RequestParam tag: String?,
                 @RequestParam favorited: String?): Mono<ArticlesIO> {
        logger.info("Getting all articles")
        val articlesFlux = tag?.let { service.getArticlesByTag(it) }
                ?: favorited?.let { service.getArticleByFavorited(it) }
                ?: service.getAllArticles()

        return articlesFlux
                .collectList()
                .map { ArticlesIO(it.map { article -> ArticleIO.fromModel(article) }, it.count()) }
    }

    //TODO  security
    @PostMapping("/api/articles")
    @ResponseStatus(HttpStatus.CREATED)
    fun newArticle(@Validated @RequestBody newArticle: NewArticleIO): Mono<String?> {
        val mapper = ObjectMapper()
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE)
        return service.saveNewArticle(newArticle).map { mapper.writeValueAsString(NewArticleIO.fromModel(it)) }
    }

    @GetMapping("/api/articles/{slug}")
    fun getArticleBySlug(@PathVariable slug: String): Mono<ArticleIO> {
        logger.info("Getting article for slug : $slug")
        return service.getArticleBySlug(slug).map { ArticleIO.fromModel(it) }
    }

}