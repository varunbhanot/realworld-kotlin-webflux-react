package com.realworld.service

import com.github.slugify.Slugify
import com.realworld.model.api.NewArticle
import com.realworld.model.domain.article.Article
import com.realworld.repository.article.ArticleByFavoritedRepository
import com.realworld.repository.article.ArticleBySlugRepository
import com.realworld.repository.article.ArticleRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*

@Service
class ArticleService(private val repository: ArticleRepository,
                     private val articleBySlugRepository: ArticleBySlugRepository,
                     private val articleByFavoritedRepository: ArticleByFavoritedRepository) {

    fun getAllArticles(): Flux<Article> {
        return repository.findAll()
    }

    fun getArticlesByTag(tag: String): Flux<Article> {
        return repository.findByTagListContaining(tag)
    }

    fun getArticleByFavorited(favorited: String): Flux<Article> {
        return articleByFavoritedRepository.findByFavorited(favorited)
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

    fun getArticleCount(): Mono<Long> {
        return repository.count()
    }

    fun saveNewArticle(newArticle: NewArticle): Mono<Article> {
        return newArticle.let {
            repository.insert(Article(
                    UUID.randomUUID(),
                    Slugify().slugify(it.title),
                    it.title!!,
                    it.description!!,
                    it.body!!,
                    it.tagList,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    "",
                    0,
                    ""))
        }
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

