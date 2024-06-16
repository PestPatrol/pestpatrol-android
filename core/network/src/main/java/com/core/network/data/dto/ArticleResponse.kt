package com.core.network.data.dto

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

    @field:SerializedName("data")
    val data: ArticleData? = null,

    @field:SerializedName("articleId")
    val articleId: String? = null
)

data class ArticleCreatedAt(

    @field:SerializedName("_nanoseconds")
    val nanoseconds: Int? = null,

    @field:SerializedName("_seconds")
    val seconds: Int? = null
)

data class ArticleData(

    @field:SerializedName("createdAt")
    val createdAt: ArticleCreatedAt? = null,

    @field:SerializedName("pictureLink")
    val pictureLink: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("content")
    val content: String? = null
)
