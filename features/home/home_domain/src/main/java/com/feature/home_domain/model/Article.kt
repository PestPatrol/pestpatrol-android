package com.feature.home_domain.model

data class Article(
    val articleId: String,
    val nanoseconds: Int,
    val seconds: Int,
    val pictureLink: String,
    val category: String,
    val title: String,
    val content: String
)