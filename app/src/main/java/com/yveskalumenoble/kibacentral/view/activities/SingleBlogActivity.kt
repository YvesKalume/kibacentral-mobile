package com.yveskalumenoble.kibacentral.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.databinding.ActivitySingleBlogBinding
import com.yveskalumenoble.kibacentral.model.Blog

class SingleBlogActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySingleBlogBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val actionbar = supportActionBar
        actionbar!!.setHomeAsUpIndicator(R.drawable.ic_chevron_left)
        actionbar.setDisplayShowTitleEnabled(false)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        val blog: Blog? = bundle?.getParcelable("blog")
        binding.blog = blog
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
