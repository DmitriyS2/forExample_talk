package ru.netology.catsspeak.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.catsspeak.repository.PostRepository
import ru.netology.catsspeak.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data = repository.getAll()
    fun highlight(id:Int) = repository.highlight(id)

}