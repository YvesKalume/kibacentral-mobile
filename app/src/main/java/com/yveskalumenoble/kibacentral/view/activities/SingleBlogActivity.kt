package com.yveskalumenoble.kibacentral.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yveskalumenoble.kibacentral.databinding.ActivitySingleBlogBinding
import com.yveskalumenoble.kibacentral.model.Blog

class SingleBlogActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySingleBlogBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle = intent.extras
        val blog: Blog? = bundle?.getParcelable("blog")
        binding.blog = blog
    }
}
