package com.yveskalumenoble.kibacentral.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yveskalumenoble.kibacentral.databinding.EventItemBinding
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.util.OnItemClickListener

class EventAdapter(val itemClickListener: OnItemClickListener) : ListAdapter<Event,EventAdapter.EventViewHolder>(Companion){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(layoutInflater,parent,false)
        return EventViewHolder(binding)
    }


    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        val binding= holder.binding
        binding.event = event
        binding.root.setOnClickListener {
            itemClickListener.onItemClick(event)
        }
    }


    companion object : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.uid == newItem.uid
        }
    }

    class EventViewHolder(val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root)


}
