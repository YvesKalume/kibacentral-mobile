package com.yveskalumenoble.kibacentral.model

data class UserEvent(
    val userUid: String?,
    val eventUid: String?,
    /*l'id du document dans la collection scheduledEvents, pour permettre de l'effacer*/
    val scheduledDocId: String? )