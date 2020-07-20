package com.yveskalumenoble.kibacentral.util

import com.yveskalumenoble.kibacentral.model.Blog
import com.yveskalumenoble.kibacentral.model.Event

interface OnItemClickListener {
    fun onItemClick(event: Event)
    fun onBlogItemClik(blog: Blog)
}