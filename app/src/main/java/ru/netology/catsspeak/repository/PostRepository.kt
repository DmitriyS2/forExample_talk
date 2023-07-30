package ru.netology.catsspeak.repository

import androidx.lifecycle.LiveData
import ru.netology.catsspeak.dto.Post

interface PostRepository {
    fun getAll():LiveData<List<Post>>

    fun highlight(id:Int)
}