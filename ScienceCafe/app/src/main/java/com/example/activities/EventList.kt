package com.example.activities


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.adapter.EventAdapter
import com.example.api.ApiService
import com.example.api.EventService
import com.example.models.Event
import com.example.sciencecafe.R
import com.example.sciencecafe.databinding.FragmentEventListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 */
class EventList : Fragment() {

    private lateinit var binding: FragmentEventListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        loadAllEvents()
        return binding.root
        //return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    fun loadAllEvents() {
        val eventService: EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.getAllApprovedEvents()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {e:List<Event>?->
                    val adapter = EventAdapter(e!!)
                    adapter?.setOnItemClickListener(object: EventAdapter.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            var eventId:Int = adapter.items[position].id
                            view.findNavController().navigate(EventListDirections.actionEventListToEventDetail(eventId))
                        }
                    })
                    binding.eventList.adapter = adapter
                    adapter.notifyDataSetChanged()
                },{ error ->
                    Log.v("Retrofit",error.message)
                })
    }


}
