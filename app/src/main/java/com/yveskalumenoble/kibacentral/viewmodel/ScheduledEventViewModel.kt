package com.yveskalumenoble.kibacentral.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.util.CONSTANT

class ScheduledEventViewModel : ViewModel() {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    init {
        getScheduledEvents()
    }

    fun getScheduledEvents() {
        firestore.collection(CONSTANT.scheduledEvents)
            .document("yveskalumenoble@gmail.com")
            .collection(CONSTANT.events)
            .orderBy("datetime",Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null || firebaseFirestoreException != null){
                    return@addSnapshotListener
                }
                val events = mutableListOf<Event>()

                _events.value = querySnapshot.toObjects(Event::class.java)
            }
    }


    fun deleteScheduledEvents(event: Event){
        firestore.collection(CONSTANT.scheduledEvents)
            .document("yvekalumenoble@gmail.com")
            .collection(CONSTANT.events)
            .document(event.uid)
            .delete()

        firestore.collection(CONSTANT.userEvent)
            .whereEqualTo("userUid","yveskalumenoble@gmail.com")
            .whereEqualTo("scheduledDocId",event.uid)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null || firebaseFirestoreException != null){
                    return@addSnapshotListener
                }
                querySnapshot.documents.forEach {
                    it.reference.delete()
                }
            }
    }
}