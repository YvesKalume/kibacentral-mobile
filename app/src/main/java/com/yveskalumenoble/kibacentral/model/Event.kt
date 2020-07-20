package com.yveskalumenoble.kibacentral.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
class Event (

    @DocumentId
    var uid: String= "",

    var title: String = "",

    var description: String ="",


    @field:ServerTimestamp var datetime: Date? = null,


    var lieu: String = "",


    var imageUri: String? = ""
): Parcelable