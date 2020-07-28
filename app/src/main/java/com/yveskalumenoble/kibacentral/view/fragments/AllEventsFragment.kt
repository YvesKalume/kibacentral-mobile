package com.yveskalumenoble.kibacentral.view.fragments

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.adapter.EventAdapter
import com.yveskalumenoble.kibacentral.databinding.FragmentAllEventsBinding
import com.yveskalumenoble.kibacentral.model.Blog
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.receiver.EventReceiver
import com.yveskalumenoble.kibacentral.util.CONSTANT
import com.yveskalumenoble.kibacentral.util.OnItemClickListener
import com.yveskalumenoble.kibacentral.view.activities.SingleEventActivity
import com.yveskalumenoble.kibacentral.viewmodel.EventViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class AllEventsFragment : Fragment(), OnItemClickListener {

    private lateinit var eventViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAllEventsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_events,
            container,false)

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)


        val mAdapter = EventAdapter(this)

        eventViewModel.getEvents().observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.submitList(it)
            }
        })

        binding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        return binding.root
    }

    override fun onItemClick(event: Event) {
        val intent = Intent(activity!!.baseContext,SingleEventActivity::class.java)
        intent.putExtra("event",event)
        startActivity(intent)
    }

    override fun onBlogItemClik(blog: Blog) {

    }
}
