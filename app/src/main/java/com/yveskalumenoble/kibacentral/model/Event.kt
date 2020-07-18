package com.yveskalumenoble.kibacentral.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.android.parcel.Parcelize
import java.util.*

/*@Entity(tableName = "event_table")*/
@Parcelize
class Event (

    @DocumentId
    var uid: String= "",

    //@ColumnInfo(name = "title")
    var title: String = "",

    //@ColumnInfo(name = "description")
    var description: String ="",

    //@ColumnInfo(name = "datetime")
    @field:ServerTimestamp var datetime: Date? = null,

    //@ColumnInfo(name = "lieu")
    var lieu: String = "",

    //@ColumnInfo(name = "imageUri")
    var imageUri: String? = "",

    var isScheduled : Boolean = false
): Parcelable