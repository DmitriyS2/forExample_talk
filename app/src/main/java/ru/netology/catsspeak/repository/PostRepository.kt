package ru.netology.catsspeak.repository

import androidx.lifecycle.LiveData
import ru.netology.catsspeak.dto.Post

interface PostRepository {
    fun getPig():LiveData<Post>
    fun getRabbit():LiveData<Post>
    fun getWoman():LiveData<Post>

    fun highlightPig()
    fun highLightRabbit()
    fun highLightWoman()
}