package com.example.activities

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.api.ApiService
import com.example.api.EventService
import com.example.models.Event
import com.example.sciencecafe.databinding.FragmentPostEventBinding

import com.example.sciencecafe.R
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostEvent : Fragment() {
    private lateinit var binding: FragmentPostEventBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate<FragmentPostEventBinding>(inflater,
            R.layout.fragment_post_event,container,false)
        val name:String = binding.postEventName.text.toString()
        val location = binding.postEventLocation.text.toString()
        val description = binding.eventDescription.text.toString()
        val eventDate = binding.eventDate.text.toString()
        val startTime = binding.startTime.text.toString()
        val endTime = binding.endTime.text.toString()
        binding.add.setOnClickListener {
            addEvent(name,location,eventDate,startTime,endTime,description)
        }
        return binding.root
    }

    fun addEvent(name:String,location:String,eventDate:String,startTime:String,endTime:String,description:String) {
        val eventService: EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.postEvent(name,location,eventDate,startTime,endTime,description)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({e: Event? ->
                print("SDSADSADD"+eventDate)
                findNavController().navigate(PostEventDirections.actionPostEventToMyEvents())
            },{ error ->
                Log.v("Retrofit",name)
            })
    }
}


