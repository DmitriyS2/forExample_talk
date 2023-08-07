package ru.netology.catsspeak.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.catsspeak.*
import ru.netology.catsspeak.databinding.InfoPostBinding
import ru.netology.catsspeak.dto.Post
import ru.netology.catsspeak.repository.blackColor
import ru.netology.catsspeak.repository.getSmile
import ru.netology.catsspeak.repository.whiteColor
import java.util.PrimitiveIterator

interface OnInteractionListener {
    fun remove(post: Post)
    fun edit(post: Post)
    fun highlight(post: Post)
}

//interface OnUserListener {
//    fun choicePig()
//    fun choiceRabbit()
//    fun choiceWoman()
//    fun choiceSmile()
//}


class PostsAdapter(
    private val onInteractionListener: OnInteractionListener,
    //private val onUserListener: OnUserListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = InfoPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener,
            //onUserListener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: InfoPostBinding,
    private val onInteractionListener: OnInteractionListener,
    //private val onUserListener: OnUserListener
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

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu_options)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.remove(post)
                                true
                            }

                            R.id.edit -> {
                                onInteractionListener.edit(post)
                                true
                            }
                            R.id.highlight -> {
                                onInteractionListener.highlight(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
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