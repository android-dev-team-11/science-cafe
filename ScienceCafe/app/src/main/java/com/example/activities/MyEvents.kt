package com.example.activities


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.adapter.EventAdapter
import com.example.api.ApiService
import com.example.api.EventService
import com.example.models.Event
import com.example.sciencecafe.databinding.FragmentMyEventsBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 */
class MyEvents : Fragment() {
    private lateinit var binding: FragmentMyEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_my_events, container, false)
        binding = FragmentMyEventsBinding.inflate(inflater, container, false)
//        binding.add.setOnClickListener {
//            findNavController().navigate(MyEventsDirections.actionMyEventsToPostEvent())
//        }
        loadAllEvents()
        return binding.root
    }

    fun loadAllEvents() {
        val eventService: EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.getOwnEvents()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {e:List<Event>?->
                    val adapter = EventAdapter(e!!)
                    adapter?.setOnItemClickListener(object: EventAdapter.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            var eventId:Int = adapter.items[position].id
                            view.findNavController().navigate(MyEventsDirections.actionMyEventsToCheckIn(eventId))
                        }
                    })
                    binding.myEventList.adapter = adapter
                    adapter.notifyDataSetChanged()
                },{ error ->
                    Log.v("Retrofit",error.message)
                })
    }

}
