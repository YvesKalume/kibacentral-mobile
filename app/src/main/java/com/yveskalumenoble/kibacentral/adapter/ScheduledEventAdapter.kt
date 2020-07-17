package com.yveskalumenoble.kibacentral.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yveskalumenoble.kibacentral.databinding.EventScheduledItemBinding
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.util.OnItemClickListener

class ScheduledEventAdapter(val itemClickListener: OnItemClickListener) : ListAdapter<Event,ScheduledEventAdapter.EventViewHolder>(Companion){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EventScheduledItemBinding.inflate(layoutInflater,parent,false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        val binding = holder.binding
        binding.event = event
        binding.root.setOnClickListener {
            itemClickListener.onItemClick(event)
        }
    }

    companion object : DiffUtil.ItemCallback<Event>(){
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return newItem == oldItem
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return newItem.uid == oldItem.uid
        }

    }

    class EventViewHolder(val binding: EventScheduledItemBinding) : RecyclerView.ViewHolder(binding.root)

}