package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adapter.EventAdapter
import com.example.model.Event
import com.example.sciencecafe.databinding.FragmentApproveBinding


class Approve : Fragment() {
    private lateinit var binding: FragmentApproveBinding
    private lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentApproveBinding.inflate(inflater, container, false)

        val eventList: MutableList<Event> = this.createEventList()
        this.adapter = EventAdapter(eventList)
        this.binding.eventList.adapter = this.adapter

        return this.binding.root
    }

    private fun createEventList(): MutableList<Event> {
        val eventList = mutableListOf<Event>()

        val event1 = Event()
        val event2 = Event()
        val event3 = Event()

        event1.imageUrl = "http://images.landofnod.com/is/image/LandOfNod/Letter_Giant_Enough_A_231533_LL/\$web_zoom\$&wid=550&hei=550&/1308310656/not-giant-enough-letter-a.jpg"
        event2.imageUrl = "https://s3.amazonaws.com/static.graphemica.com/glyphs/i500s/000/007/209/original/0042-500x500.png?1275320964"
        event3.imageUrl = "https://s3.amazonaws.com/static.graphemica.com/glyphs/i500s/000/007/210/original/0043-500x500.png?1275320965"

        eventList.add(event1)
        eventList.add(event2)
        eventList.add(event3)

        return eventList
    }
}
