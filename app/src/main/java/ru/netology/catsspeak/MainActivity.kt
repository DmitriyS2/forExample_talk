package ru.netology.catsspeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.catsspeak.adapter.OnInteractionListener
import ru.netology.catsspeak.adapter.PostsAdapter
import ru.netology.catsspeak.databinding.ActivityMainBinding
import ru.netology.catsspeak.dto.Post
import ru.netology.catsspeak.dto.User
import ru.netology.catsspeak.repository.getPig
import ru.netology.catsspeak.repository.getRabbit
import ru.netology.catsspeak.repository.getSmile
import ru.netology.catsspeak.repository.getUser0
import ru.netology.catsspeak.repository.getWoman
import ru.netology.catsspeak.util.AndroidUtils
import ru.netology.catsspeak.util.focusAndShowKeyboard
import ru.netology.catsspeak.viewmodel.PostViewModel
import ru.netology.catsspeak.viewmodel.getEmpty
import java.util.Collections.copy


class MainActivity : AppCompatActivity() {

    var newUser: User = getUser0()
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
                newUser = post.user
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

            if (newUser.name.isEmpty()) {
                Toast.makeText(
                    this,
                    "User must be selected",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            viewModel.changeContentAndSave(text, newUser)
            newUser = getUser0()

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



        binding.menuUser.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.menu_users)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.smile -> {
                            newUser = getSmile()
                            //onInteractionListener.remove(post)
                            true
                        }
                        R.id.pig -> {
                            newUser = getPig()
                            //onInteractionListener.edit(post)
                            true
                        }
                        R.id.rabbit -> {
                            newUser = getRabbit()
                            //onInteractionListener.highlight(post)
                            true
                        }
                        R.id.woman -> {
                            newUser = getWoman()
                            true
                        }
                        else -> false
                    }
                }
            }.show()
        }
    }
}