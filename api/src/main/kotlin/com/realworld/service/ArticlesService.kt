package com.realworld.service

import com.datastax.driver.core.utils.UUIDs
import com.github.slugify.Slugify
import com.realworld.model.Article
import com.realworld.model.api.NewArticle
import com.realworld.repository.ArticleRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class ArticleService(private val repository: ArticleRepository) {

    fun getAllArticles(): Flux<Article> {
        return repository.findAll()
    }

    fun getArticleCount():Mono<Long> {
        return repository.count()
    }

    fun saveNewArticle(newArticle: NewArticle): Mono<Article> {
        return newArticle.let {
            repository.insert(Article(
                    UUIDs.random(),
                    Slugify().slugify(it.title),
                    it.title!!,
                    it.description!!,
                    it.body!!,
                    it.tagList,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    false,
                    0,
                    UUIDs.random()))
        }
    }
}

