package ru.netology.catsspeak.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.catsspeak.*
import ru.netology.catsspeak.dto.Post
import ru.netology.catsspeak.dto.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val blackColor:String  = "#000000"
const val whiteColor:String = "#FFFFFF"
const val colorPig:String = "#FDE801"
const val colorRabbit:String = "#FD4801"
const val colorWoman:String =  "#FF444477"
const val colorEmotion:String = "#50970C"
val avatarPig = R.drawable.pig_savings_24
val avatarRabbit = R.drawable.rabbit_cruelty_free_24
val avatarWoman = R.drawable.woman
val avatarEmotion = R.drawable.ic_emoji_emotions_24
val avatars = listOf<Int>(avatarPig, avatarRabbit, avatarRabbit, avatarEmotion)


private val user0 = User("", 0,"")
fun getUser0():User {
    return user0
}
private val pig: User = User("Pig", avatarPig, colorPig)
fun getPig():User {
    return pig
}
private val smile = User("Smile", avatarEmotion, colorEmotion)
fun getSmile():User {
    return smile
}
private val rabbit: User = User("Rabbit", avatarRabbit, colorRabbit)
fun getRabbit():User {
    return rabbit
}
private val woman: User = User("Woman", avatarWoman, colorWoman)
fun getWoman():User {
    return woman
}

class PostRepositoryInMemoryImpl : PostRepository {

    private var nextId = 1

    private var posts = listOf(
        Post(nextId++, pig, "Привет, заяц! Что делаешь?", "01 января в 12:30", false),
        Post(nextId++, rabbit, "Привет, свин! Морковку ем. Заходи!", "01 января в 12:35", false),
        Post(nextId++, woman, "Привет, звери!", "01 января в 13:00", false),
        Post(nextId++, pig, "Привет, женщина! Как ты?", "01 января в 13:05", false),
        Post(nextId++, rabbit, "Привет! Давно тебя не видно было", "01 января в 13:10", false),
        Post(nextId++, woman, "Хорошо, свин! Да, в командировке была ", "01 января в 13:25", false),
        Post(nextId++, pig, "Заяц, сейчас к тебе загляну. Женщина, пойдем с нами!","01 января в 13:30",false),
        Post(nextId++, rabbit, "Ок, свин. Да, жду вас обоих!", "01 января в 14:00", false),
        Post(nextId++, woman, "Ок. Сейчас буду!", "01 января в 14:10", false)
    )

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun highlightById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                light = !it.light
            )
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun save(post: Post, newUser: User) {
        val dateTime = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a"))
        posts = if (post.id == 0) {
            posts +
                    listOf(
                        post.copy(
                            id = nextId++,
                            user = newUser,
                            published = dateTime
                        )
                    )
        } else {
            posts.map { if (it.id != post.id) it else it.copy(content = post.content) }
        }
        data.value = posts

    }
}