package com.yveskalumenoble.kibacentral.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.model.UserEvent
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



    fun scheduleEvent(event: Event){
        val firestore= FirebaseFirestore.getInstance()
        firestore.collection(CONSTANT.scheduledEvents)
            .document("yveskalumenoble@gmail.com")
            .collection(CONSTANT.events)
            .add(event)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    saveUserEvent(event.uid, it.result?.id)
                }
            }
            .addOnFailureListener {
                Log.d("SingleEventActivity","error : ${it.message}")
            }
    }

    private fun saveUserEvent(eventUid: String? , docId: String?){
        val firestore= FirebaseFirestore.getInstance()

        val userEvent = UserEvent("yveskalumenoble@gmail.com",eventUid,docId)
        firestore.collection(CONSTANT.userEvent)
            .add(userEvent)
            .addOnCompleteListener {
                Log.d("SingleEventActivity","added")
            }
            .addOnFailureListener {
                Log.d("SingleEventActivity","error : ${it.message}")
            }
    }

    fun isScheduled(event: Event) : LiveData<Boolean> {
        val firestore= FirebaseFirestore.getInstance()
        val isScheduled = MutableLiveData<Boolean>()

        firestore.collection(CONSTANT.userEvent)
            .whereEqualTo("userUid","yveskalumenoble@gmail.com")
            .whereEqualTo("eventUid",event.uid)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (querySnapshot == null || firebaseFirestoreException != null){
                    return@addSnapshotListener
                }

                isScheduled.value = !querySnapshot.isEmpty
                Log.d("SingleEventActivity","planifié bool: $isScheduled")
                return@addSnapshotListener
            }
        return isScheduled
    }

    fun removeScheduledEvent(event: Event) {

    }
}