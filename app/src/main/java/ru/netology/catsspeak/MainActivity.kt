package ru.netology.catsspeak

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.catsspeak.databinding.ActivityMainBinding
import ru.netology.catsspeak.viewmodel.PostViewModel

var colorBack: String = "#000000"
var colorFont: String = "#FFFFFF"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.dataPig.observe(this) { postPig ->
            with(binding) {
                contentPig.setBackgroundColor(Color.parseColor(colorBack))
                contentPig.setTextColor(Color.parseColor(colorFont))
                contentPig.text = postPig.content
                publishedPig.text = postPig.published
            }
        }
        viewModel.dataRabbit.observe(this) { postRabbit ->
            with(binding) {
                contentRabbit.setBackgroundColor(Color.parseColor(colorBack))
                contentRabbit.setTextColor(Color.parseColor(colorFont))
                contentRabbit.text = postRabbit.content
                publishedRabbit.text = postRabbit.published
            }
        }
        viewModel.dataWoman.observe(this) { postWoman ->
            with(binding) {
                contentWoman.setBackgroundColor(Color.parseColor(colorBack))
                contentWoman.setTextColor(Color.parseColor(colorFont))
                contentWoman.text = postWoman.content
                publishedWoman.text = postWoman.published
            }
        }

        binding.avatarPig.setOnClickListener {
            viewModel.highlightPig()
        }
        binding.avatarRabbit.setOnClickListener {
            viewModel.highlightRabbit()
        }
        binding.avatarWoman.setOnClickListener {
            viewModel.highlightWoman()
        }
    }
}