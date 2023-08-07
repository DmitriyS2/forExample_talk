package ru.netology.catsspeak.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import ru.netology.catsspeak.dto.Post
import ru.netology.catsspeak.dto.User
import ru.netology.catsspeak.repository.PostRepository
import ru.netology.catsspeak.repository.PostRepositoryInMemoryImpl
import ru.netology.catsspeak.repository.getUser0


private val empty = Post(
    id = 0,
    user = getUser0(),
    content = "",
    published = "",
    light = false
)
fun getEmpty():Post{
    return empty
}

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val edited = MutableLiveData(empty)

    fun changeContentAndSave(content: String, newUser: User) {
        edited.value?.let {
            val text = content.trim()
            if (it.content != text) {
                repository.save(it.copy(content = text), newUser)
            }
        }
        edited.value = empty
    }

    val data = repository.getAll()
    fun highlight(id:Int) = repository.highlightById(id)
    fun remove(id: Int) =repository.removeById(id)
    fun edit(post: Post) {
        edited.value = post
    }

}