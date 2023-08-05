package ru.netology.catsspeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.catsspeak.adapter.OnInteractionListener
import ru.netology.catsspeak.adapter.PostsAdapter
import ru.netology.catsspeak.databinding.ActivityMainBinding
import ru.netology.catsspeak.dto.Post
import ru.netology.catsspeak.util.AndroidUtils
import ru.netology.catsspeak.util.focusAndShowKeyboard
import ru.netology.catsspeak.viewmodel.PostViewModel
import ru.netology.catsspeak.viewmodel.getEmpty

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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object:OnInteractionListener {
            override fun remove(post: Post) {
                viewModel.remove(post.id)
            }
            override fun edit(post: Post) {
                binding.groupEdit.visibility = View.VISIBLE
                binding.editText.setText(post.content)
                viewModel.edit(post)
            }
            override fun highlight(post: Post) {
                viewModel.highlight(post.id)
            }

        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPost = posts.size > adapter.currentList.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(posts.size)
                }
            }
        }

        viewModel.edited.observe(this) {
            if (it.id != 0) {
                binding.content.setText(it.content)
                binding.content.focusAndShowKeyboard()
            }
        }

        binding.save.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(
                    this,
                    "Content can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            viewModel.changeContentAndSave(text)

            binding.content.setText("")
            binding.groupEdit.visibility = View.GONE
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(it)
        }
        binding.cancelEdit.setOnClickListener {
            binding.content.setText("")
            binding.groupEdit.visibility = View.GONE
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(it)
            viewModel.edit(getEmpty())
        }
    }
}