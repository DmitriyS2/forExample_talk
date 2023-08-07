package ru.netology.catsspeak.repository

import androidx.lifecycle.LiveData
import ru.netology.catsspeak.dto.Post
import ru.netology.catsspeak.dto.User

interface PostRepository {
    fun getAll():LiveData<List<Post>>

    fun highlightById(id:Int)

    fun removeById(id: Int)

    fun save(post: Post, user: User)
}