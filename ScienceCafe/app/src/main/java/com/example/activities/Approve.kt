package com.example.activities


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.adapter.EventApproveAdapter
import com.example.api.ApiService
import com.example.api.EventService
import com.example.models.Event
import com.example.sciencecafe.databinding.FragmentApproveBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class Approve : Fragment() {
    private lateinit var binding: FragmentApproveBinding
//    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentApproveBinding.inflate(inflater, container, false)
        loadAllEvents()
        return this.binding.root
    }

    fun loadAllEvents() {
        val eventService: EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.getAllPendingEvents()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {e:List<com.example.models.Event>?->
                    val adapter = EventApproveAdapter(e!!)
                    adapter?.setOnItemClickListener(object: EventApproveAdapter.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            var eventId:Int = adapter.items[position].id
                            view.findNavController().navigate(ApproveDirections.actionApproveToEventDetail(eventId))
                        }
                    })
                    binding.approveEventList.adapter = adapter
                    adapter.notifyDataSetChanged()
                },{ error ->
                    Log.v("Retrofit",error.message)
                })
    }
}
