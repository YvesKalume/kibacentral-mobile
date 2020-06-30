package com.yveskalumenoble.kibacentral.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yveskalumenoble.kibacentral.model.Event

class EventViewModel : ViewModel() {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    private val _scheduledEvents = MutableLiveData<List<Event>> ()
    val scheduledEvents: LiveData<List<Event>>
        get() = _scheduledEvents

    init {
        getData()
    }

    fun getData() {
        firestore.collection("events")
            .orderBy("datetime",Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null || firebaseFirestoreException != null){
                    return@addSnapshotListener
                }
                val events = mutableListOf<Event>()

                querySnapshot.forEach {
                    val event= it.toObject(Event::class.java)
                    event.id = it.id
                    event.isScheduled
                    events.add(event)
                }
                _events.value = events
                /*.filter {

                    val currentTime = System.currentTimeMillis()
                    val eventTime = it.datetime!!.time

                    return@filter currentTime <= eventTime

                }*/

                _scheduledEvents.value = events.filter {
                    return@filter it.isScheduled
                }
            }
    }
}