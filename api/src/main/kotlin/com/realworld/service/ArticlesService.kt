package com.realworld.service

import com.github.slugify.Slugify
import com.realworld.model.api.NewArticle
import com.realworld.model.api.UpdateArticle
import com.realworld.model.domain.article.Article
import com.realworld.repository.ArticleBySlugRepository
import com.realworld.repository.ArticleRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*

@Service
class ArticleService(private val repository: ArticleRepository, private val articleBySlugRepository: ArticleBySlugRepository) {

    fun getAllArticles(): Flux<Article> {
        return repository.findAll()
    }

    fun getArticleCount(): Mono<Long> {
        return repository.count()
    }

    fun saveNewArticle(newArticle: NewArticle): Mono<Article> {
        return newArticle.let {
            repository.insert(Article(
                    UUID.randomUUID(),
                    Slugify().slugify(it.title),
                    it.title,
                    it.description,
                    it.body,
                    it.tagList,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    false,
                    0,
                    ""))
        }
    }

    fun updateArticle(slug: String, updateArticle: UpdateArticle): Mono<Article> {
        val ret = repository.findBySlug(slug)
        return Mono.empty<Article>()
    }

    fun getArticleBySlug(slug: String): Mono<Article> {
        return articleBySlugRepository.findById(slug)
                .map {
                    Article(it.articleId,
                            it.slug,
                            it.title,
                            it.description,
                            it.body,
                            it.tagList,
                            it.createdAt,
                            it.updatedAt,
                            it.favorited,
                            it.favoritesCount,
                            it.authorName)
                }
    }
}

