package ru.netology.catsspeak.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.catsspeak.*
import ru.netology.catsspeak.dto.Post
import ru.netology.catsspeak.dto.User

class PostRepositoryInMemoryImpl : PostRepository {
    private val pig:User = User("Pig", avatarPig, colorPig)
    private val rabbit:User = User("Rabbit", avatarRabbit, colorRabbit)
    private val woman:User = User("Woman", avatarWoman, colorWoman)

    private var posts = listOf(
        Post(1, pig,"Привет, заяц! Что делаешь?", "01 января в 12:30", false),
        Post(2, rabbit,"Привет, свин! Морковку ем. Заходи!", "01 января в 12:35", false),
        Post(3, woman,"Привет, звери!", "01 января в 13:00", false),
        Post(4, pig,"Привет, женщина! Как ты?", "01 января в 13:05", false),
        Post(5, rabbit,"Привет! Давно тебя не видно было", "01 января в 13:10", false),
        Post(6, woman,"Хорошо, свин! Да, в командировке была ", "01 января в 13:25", false),
        Post(7, pig,"Заяц, сейчас к тебе загляну. Женщина, пойдем с нами!", "01 января в 13:30", false),
        Post(8, rabbit,"Ок, свин. Да, жду вас обоих!", "01 января в 14:00", false),
        Post(9, woman,"Ок. Сейчас буду!", "01 января в 14:10", false)
    )

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun highlight(id: Int) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                    light = !it.light
                )
        }
        data.value = posts
    }
}