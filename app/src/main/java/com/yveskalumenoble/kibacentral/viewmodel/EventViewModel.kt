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

        firestore.collection(CONSTANT.events)
            .orderBy("datetime",Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null || firebaseFirestoreException != null){
                    return@addSnapshotListener
                }


                events.value = querySnapshot.toObjects(Event::class.java)

                /*.filter {

                    val currentTime = System.currentTimeMillis()
                    val eventTime = it.datetime!!.time

                    return@filter currentTime <= eventTime

                }*/
            }
        return events
    }



    fun isScheduled(event: Event) : LiveData<Boolean> {
        val isScheduled = MutableLiveData<Boolean>()
        firestore.collection(CONSTANT.scheduledEvents)
            .document("yveskalumenoble@gmail.com")
            .collection(CONSTANT.events)
            .whereArrayContains("uid", event.uid)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (querySnapshot == null || firebaseFirestoreException != null){
                return@addSnapshotListener
            }
                isScheduled.value = !querySnapshot.isEmpty
        }
        return isScheduled
    }
}