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

    private lateinit var alarmManager: AlarmManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAllEventsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_events,
            container,false)

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)

        alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        createNotificationChannel()

        val mAdapter = EventAdapter(this)

        eventViewModel.getEvents().observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.submitList(it)
            }

            it.forEach { event ->
               setAlarm(event)
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

    private fun setAlarm(event: Event){
        val intent = Intent(context, EventReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val day = event.datetime!!.day
        val month = event.datetime!!.month
        val year = event.datetime!!.year
        val date = Date(year,month,day,0,0)

        alarmManager.set(AlarmManager.RTC_WAKEUP,date.time,pendingIntent)
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                CONSTANT.notificationChanelId,"Kibacentral Notification Channel"
                , NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Event Notification"
            val notificationManager = requireActivity().getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
