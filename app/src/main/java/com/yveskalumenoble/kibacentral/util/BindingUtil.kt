package com.yveskalumenoble.kibacentral.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.yveskalumenoble.kibacentral.model.Event
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("eventTitle")
fun TextView.setEventTitle(item: Event?){
    text = item?.title
}

@BindingAdapter("eventLieu")
fun TextView.setEventLieu(item: Event?){
    text = item?.lieu
}

@BindingAdapter("eventDateTime")
fun TextView.setEventDateTime(eventDate: Date){
    val date = SimpleDateFormat("dd/MM/yyyy",Locale.FRANCE).format(eventDate)
    val time = SimpleDateFormat("HH:mm", Locale.FRANCE).format(eventDate)
    val day = SimpleDateFormat("EEEE", Locale.FRANCE).format(eventDate)
    text = "$day, le $date à $time"
}

@BindingAdapter("eventDate")
fun TextView.setEventDate(eventDate: Date){
    val day = SimpleDateFormat("EEEE", Locale.FRANCE).format(eventDate)
    val date = SimpleDateFormat("dd/MM/yyyy",Locale.FRANCE).format(eventDate)
    text = "$day, le $date"
}

@BindingAdapter("dayOfMonth")
fun TextView.setDayOfMonth(eventDate : Date){
    text = SimpleDateFormat("dd", Locale.FRANCE).format(eventDate)
}

@BindingAdapter("dayOfWeek")
fun TextView.setDayOfWeek(eventDate: Date){

    val day = SimpleDateFormat("EEEE", Locale.FRANCE).format(eventDate)
    text = day
}

@BindingAdapter("eventTime")
fun TextView.setTime(eventDate: Date){
    val time = SimpleDateFormat("HH:mm", Locale.FRANCE).format(eventDate)
    text = "à $time"
}

@BindingAdapter("eventDescription")
fun TextView.setEventDescription(item: Event?){
    text = item?.description
}

@BindingAdapter("eventImage")
fun bindEventImage(imageView: ImageView, item: Event?){
    Glide.with(imageView.context).load(item?.imageUri).into(imageView)
}