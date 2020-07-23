package com.yveskalumenoble.kibacentral.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class UserEvent(
    val userUid: String? = "",
    val eventUid: String? = "",

    @field:ServerTimestamp
    val eventDatetime: Date? = null
)