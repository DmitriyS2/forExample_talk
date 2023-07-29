package ru.netology.catsspeak.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.catsspeak.repository.PostRepository
import ru.netology.catsspeak.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val dataPig = repository.getPig()
    val dataRabbit = repository.getRabbit()
    val dataWoman = repository.getWoman()

    fun highlightPig() = repository.highlightPig()
    fun highlightRabbit() = repository.highLightRabbit()
    fun highlightWoman() = repository.highLightWoman()

}