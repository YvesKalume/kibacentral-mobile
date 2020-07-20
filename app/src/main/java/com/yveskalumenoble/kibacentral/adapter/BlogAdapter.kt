package com.yveskalumenoble.kibacentral.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yveskalumenoble.kibacentral.databinding.BlogItemBinding
import com.yveskalumenoble.kibacentral.model.Blog
import com.yveskalumenoble.kibacentral.util.OnItemClickListener

class BlogAdapter(private val itemClickListener: OnItemClickListener) : ListAdapter<Blog,BlogAdapter.BlogViewHolder>(Companion){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BlogItemBinding.inflate(layoutInflater,parent,false)
        return BlogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blog = getItem(position)
        val binding = holder.binding
        binding.blog = blog
        binding.root.setOnClickListener {
            itemClickListener.onBlogItemClik(blog)
        }
    }

    companion object : DiffUtil.ItemCallback<Blog>(){
        override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean {
            return oldItem.uid == newItem.uid
        }

    }
    class BlogViewHolder(val binding: BlogItemBinding) : RecyclerView.ViewHolder(binding.root)
}