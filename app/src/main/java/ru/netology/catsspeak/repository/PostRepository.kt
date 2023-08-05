package ru.netology.catsspeak.repository

import androidx.lifecycle.LiveData
import ru.netology.catsspeak.dto.Post

interface PostRepository {
    fun getAll():LiveData<List<Post>>

    fun highlightById(id:Int)

    fun removeById(id: Int)

    fun save(post: Post)
}