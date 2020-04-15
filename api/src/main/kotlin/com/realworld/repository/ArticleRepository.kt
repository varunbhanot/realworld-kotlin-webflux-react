package com.realworld.repository

import com.realworld.model.Article
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import java.util.*

interface ArticleRepository: ReactiveCassandraRepository<Article, UUID>