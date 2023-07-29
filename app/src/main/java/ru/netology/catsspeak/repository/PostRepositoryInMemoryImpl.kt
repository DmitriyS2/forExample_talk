package ru.netology.catsspeak.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.catsspeak.colorBack
import ru.netology.catsspeak.colorFont
import ru.netology.catsspeak.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var postPig = Post(1, "Привет, заяц! Что делаешь?", "01 января в 12:30", false)
    private var postRabbit =
        Post(2, "Привет, свин! Морковку ем. Заходи!", "01 января в 14:00", false)
    private var postWoman = Post(3, "Привет, звери!", "01 января в 18:00", false)

    private val dataPig = MutableLiveData(postPig)
    private val dataRabbit = MutableLiveData(postRabbit)
    private val dataWoman = MutableLiveData(postWoman)

    override fun getPig(): LiveData<Post> = dataPig
    override fun getRabbit(): LiveData<Post> = dataRabbit
    override fun getWoman(): LiveData<Post> = dataWoman

    override fun highlightPig() {
        if (postPig.light) {
            colorBack = "#000000"
            colorFont = "#FFFFFF"
        } else {
            colorBack = "#FDE801"
            colorFont = "#000000"
        }
        postPig = postPig.copy(light = !postPig.light)
        dataPig.value = postPig
    }

    override fun highLightRabbit() {
        if (postRabbit.light) {
            colorBack = "#000000"
            colorFont = "#FFFFFF"
        } else {
            colorBack = "#FD4801"
            colorFont = "#000000"
        }
        postRabbit = postRabbit.copy(light = !postRabbit.light)
        dataRabbit.value = postRabbit
    }

    override fun highLightWoman() {
        if (postWoman.light) {
            colorBack = "#000000"
            colorFont = "#FFFFFF"
        } else {
            colorBack = "#FF444477"
            colorFont = "#000000"
        }
        postWoman = postWoman.copy(light = !postWoman.light)
        dataWoman.value = postWoman
    }


}