package com.yveskalumenoble.kibacentral.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.util.CONSTANT

class EventViewModel : ViewModel() {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getEvents() : LiveData<List<Event>> {
        val events = MutableLiveData<List<Event>>()

        firestore.collection("events")
            .orderBy("datetime",Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null || firebaseFirestoreException != null){
                    return@addSnapshotListener
                }
                val eventsList = mutableListOf<Event>()

                querySnapshot.forEach {
                    val event= it.toObject(Event::class.java)
                    eventsList.add(event)
                }
                events.value = eventsList
                /*.filter {

                    val currentTime = System.currentTimeMillis()
                    val eventTime = it.datetime!!.time

                    return@filter currentTime <= eventTime

                }*/
            }
        return events
    }
}