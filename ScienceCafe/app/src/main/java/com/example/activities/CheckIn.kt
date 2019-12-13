package com.example.activities

import android.content.Context
import android.net.Uri
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adapter.AttendeeAdapter
import com.example.api.ApiService
import com.example.api.EventService
import com.example.api.UserService
import com.example.models.Event
import com.example.models.User

import com.example.sciencecafe.R
import com.example.sciencecafe.databinding.FragmentCheckInBinding
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers




class CheckIn : Fragment() {
    private lateinit var binding: FragmentCheckInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckInBinding.inflate(inflater, container, false)
        val args = CheckInArgs.fromBundle(arguments!!)
        loadEventById(args.eventId)
        loadAllAttendees(args.eventId)
        binding.scan.setOnClickListener {
            val intent = Intent(context, ScannerActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("eventId", args.eventId)
            intent.putExtras(bundle)
            context!!.startActivity(intent)
        }
        return binding.root
        //return inflater.inflate(R.layout.fragment_event_detail, container, false)
    }

    fun loadEventById(id:Int) {
        val eventService: EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.getEventById(id)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({e: Event? ->
                binding.myEventName.text = e!!.name
                binding.myLocation.text = "Location: " + e!!.location
                binding.myEventDate.text = convertLongToDateString(e!!.eventDate) + " " + e!!.startTime + " ~ " + e!!.endTime
                Picasso.get().load(e!!.imageUrl).error(R.drawable.default_event).into(binding.myEventImage)
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }

    fun loadAllAttendees(id:Int) {
        val userService: UserService = ApiService.apiService.create(UserService::class.java)
        val observable = userService.getAttendeesById(id)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({u: List<User>? ->
                val adapter = AttendeeAdapter(u!!)
                binding.attendeeList.adapter = adapter
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }
}
