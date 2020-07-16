package com.yveskalumenoble.kibacentral.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.adapter.ScheduledEventAdapter
import com.yveskalumenoble.kibacentral.databinding.FragmentEventsScheduledBinding
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.util.OnItemClickListener
import com.yveskalumenoble.kibacentral.viewmodel.EventViewModel
import com.yveskalumenoble.kibacentral.viewmodel.ScheduledEventViewModel

/**
 * A simple [Fragment] subclass.
 */
class EventsScheduledFragment : Fragment(), OnItemClickListener {

    private lateinit var viewModel : ScheduledEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentEventsScheduledBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_events_scheduled,
            container,false)

        viewModel = ViewModelProviders.of(this).get(ScheduledEventViewModel::class.java)
        val mAdapter = ScheduledEventAdapter(this)

        viewModel.events.observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.submitList(it)
            }
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        return binding.root
    }

    override fun onItemClick(event: Event) {
        Toast.makeText(this.context,event.title,Toast.LENGTH_SHORT).show()
    }

}
