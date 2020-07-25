package com.yveskalumenoble.kibacentral.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yveskalumenoble.kibacentral.model.Blog
import com.yveskalumenoble.kibacentral.util.CONSTANT

class BlogViewModel : ViewModel() {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _blogs = MutableLiveData<List<Blog>>()
    val blogs: LiveData<List<Blog>>
        get() = _blogs

    init {
        getBlogs()
    }

    private fun getBlogs() {
        firestore.collection(CONSTANT.blog)
            .orderBy("datetime",Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null || firebaseFirestoreException != null){
                    return@addSnapshotListener
                }
                _blogs.value = querySnapshot.toObjects(Blog::class.java)
            }
    }

    fun searchBlog(text: String?) {
        _blogs.value?.filter {
            val title = it.title
            return@filter title == text
        }
    }
}