package ru.netology.catsspeak.dto

data class Post(
    val id: Int,
    val user:User,
    val content: String,
    val published: String,
    var light:Boolean
)
