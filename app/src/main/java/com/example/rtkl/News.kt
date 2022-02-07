package com.example.rtkl

data class News(
    val totalResults: Int,
    val status: String,
    val articles: List<Article>
) {
}