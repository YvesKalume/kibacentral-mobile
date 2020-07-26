package com.yveskalumenoble.kibacentral.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.adapter.BlogAdapter
import com.yveskalumenoble.kibacentral.databinding.FragmentBlogBinding
import com.yveskalumenoble.kibacentral.model.Blog
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.util.OnItemClickListener
import com.yveskalumenoble.kibacentral.view.activities.SingleBlogActivity
import com.yveskalumenoble.kibacentral.viewmodel.BlogViewModel

/**
 * A simple [Fragment] subclass.
 */
class BlogFragment : Fragment(),OnItemClickListener {

    private lateinit var blogViewModel: BlogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentBlogBinding.inflate(inflater,container,false)

        blogViewModel = ViewModelProviders.of(this).get(BlogViewModel::class.java)

        val adapter = BlogAdapter(this)

        blogViewModel.blogs.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    blogViewModel.searchBlog(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


        binding.blogRecyclerView.adapter = adapter

        return binding.root
    }


    override fun onBlogItemClik(blog: Blog) {
        val intent = Intent(context,SingleBlogActivity::class.java)
        intent.putExtra("blog",blog)
        startActivity(intent)
    }

    override fun onItemClick(event: Event) {

    }

}
