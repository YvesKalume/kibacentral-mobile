package com.yveskalumenoble.kibacentral.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yveskalumenoble.kibacentral.model.Event

class ScheduledEventViewModel : ViewModel() {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    init {
        getScheduledEvents()
    }

    fun getScheduledEvents() {
        firestore.collection("scheduled_events")
            .orderBy("datetime",Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null || firebaseFirestoreException != null){
                    return@addSnapshotListener
                }
                val events = mutableListOf<Event>()

                querySnapshot.forEach {
                    val event= it.toObject(Event::class.java)
                    event.uid = it.id
                    events.add(event)
                }
                _events.value = events
            }
    }

    fun deleteScheduledEvents(event: Event){
        firestore.collection("scheduled_events")
            .document(event.uid).delete()
    }
}