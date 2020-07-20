package com.yveskalumenoble.kibacentral.util

import android.view.View
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

@BindingAdapter("humanDate")
fun TextView.sethumanDate(eventDate: Date){
    val date = SimpleDateFormat("dd/MM/yyyy",Locale.FRANCE).format(eventDate)
    val time = SimpleDateFormat("HH:mm", Locale.FRANCE).format(eventDate)
    val day = SimpleDateFormat("EEEE", Locale.FRANCE).format(eventDate)
    text = "$day, le $date à $time"
}

@BindingAdapter("dateFormat")
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
fun TextView.setDayOfWeek(date: Date){

    val day = SimpleDateFormat("EEEE", Locale.FRANCE).format(date)
    text = day
}

@BindingAdapter("timeFormat")
fun TextView.setTime(date: Date){
    val time = SimpleDateFormat("HH:mm", Locale.FRANCE).format(date)
    text = "à $time"
}

@BindingAdapter("eventDescription")
fun TextView.setEventDescription(item: Event?){
    text = item?.description
}

@BindingAdapter("bindImage")
fun bindEventImage(imageView: ImageView, imageUri: String){
    Glide.with(imageView.context).load(imageUri).into(imageView)
}

/*
@BindingAdapter("bindBackgrounImage")
fun bindBackgrounImage(view: View,imageUri: String){
    Glide.with(view.context).load(imageUri)
}*/
