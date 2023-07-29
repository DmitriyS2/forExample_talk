package ru.netology.catsspeak.dto

data class Post(
    val id: Int,
    val content: String,
    val published: String,
    var light:Boolean
)
