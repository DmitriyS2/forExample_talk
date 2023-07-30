package ru.netology.catsspeak.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.catsspeak.*
import ru.netology.catsspeak.databinding.InfoPostBinding
import ru.netology.catsspeak.dto.Post

typealias OnLightListener = (post: Post) -> Unit

class PostsAdapter(
    private val onLightListener: OnLightListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = InfoPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLightListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: InfoPostBinding,
    private val onLightListener: OnLightListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            name.text = post.user.name
            published.text = post.published
            content.text = post.content
            content.setBackgroundColor(Color.parseColor(if(post.light) post.user.colorLight else blackColor))
            content.setTextColor(Color.parseColor(if(post.light) blackColor else whiteColor))
            avatar.setImageResource(
                post.user.avatarka
            )
            avatar.setOnClickListener {
                onLightListener(post)
            }
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}