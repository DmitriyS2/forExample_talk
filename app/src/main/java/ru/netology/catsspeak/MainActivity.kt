package ru.netology.catsspeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.catsspeak.adapter.PostsAdapter
import ru.netology.catsspeak.databinding.ActivityMainBinding
import ru.netology.catsspeak.viewmodel.PostViewModel

const val blackColor:String  = "#000000"
const val whiteColor:String = "#FFFFFF"
const val colorPig:String = "#FDE801"
const val colorRabbit:String = "#FD4801"
const val colorWoman:String =  "#FF444477"
val avatarPig = R.drawable.pig_savings_24
val avatarRabbit = R.drawable.rabbit_cruelty_free_24
val avatarWoman = R.drawable.woman

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter{
            viewModel.highlight(it.id)
        }
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}