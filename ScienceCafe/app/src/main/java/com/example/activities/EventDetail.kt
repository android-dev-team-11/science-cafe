package com.example.activities


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.api.ApiService
import com.example.api.EventService
import com.example.models.Event
import com.example.sciencecafe.databinding.FragmentEventDetailBinding
import com.example.utils.convertLongToDateString
import com.example.utils.convertLongToTimeString
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.example.sciencecafe.R
/**
 * A simple [Fragment] subclass.
 */
class EventDetail : Fragment() {
    private lateinit var binding: FragmentEventDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        val args = EventDetailArgs.fromBundle(arguments!!)
        loadEventById(args.eventId)
        return binding.root
        //return inflater.inflate(R.layout.fragment_event_detail, container, false)
    }

    fun loadEventById(id:Int) {
        val eventService: EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.getEventById(id)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({e: Event? ->
                binding.eventName.text = e!!.name
                binding.location.text = "Location: " + e!!.location
                binding.eventDate.text = convertLongToDateString(e!!.eventDate) + " " + e!!.startTime + " ~ " + e!!.endTime
                Picasso.get().load(e!!.imageUrl).error(R.drawable.default_event).into(binding.eventImage)
                binding.eventDescription.loadData(e!!.description,"text/html; charset=utf-8", "utf-8")
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }
}
