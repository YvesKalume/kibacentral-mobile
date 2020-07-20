package com.yveskalumenoble.kibacentral.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Blog(

    @DocumentId
    var uid: String? = null,

    var title: String? = null,

    var imageUri: String? = null,

    var content: String? = null,

    @field:ServerTimestamp
    var datetime: Date? = null

): Parcelable