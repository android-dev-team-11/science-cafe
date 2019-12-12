package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.model.Event
import com.example.sciencecafe.databinding.FragmentEventListBinding
import com.example.adapter.EventPAdapter


/**
 * A simple [Fragment] subclass.
 */
class EventList : Fragment() {

    private lateinit var binding: FragmentEventListBinding
    private lateinit var adapter: EventPAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventListBinding.inflate(inflater, container, false)


        binding.rewardButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_eventList_to_rewardList)
        }
        binding.newsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_eventList_to_newsList)
        }


        val eventList: MutableList<Event>  = this.createEventList()
        this.adapter = EventPAdapter(eventList)
        this.binding.EventListP.adapter = this. adapter

        return this.binding.root
        //return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    private fun createEventList(): MutableList<Event> {
        val eventList = mutableListOf<Event>()

        val event1 = Event()
        val event2 = Event()
        val event3 = Event()
        val event4 = Event()

        event1.imageUrl = "http://images.landofnod.com/is/image/LandOfNod/Letter_Giant_Enough_A_231533_LL/\$web_zoom\$&wid=550&hei=550&/1308310656/not-giant-enough-letter-a.jpg"
        event2.imageUrl = "https://s3.amazonaws.com/static.graphemica.com/glyphs/i500s/000/007/209/original/0042-500x500.png?1275320964"
        event3.imageUrl = "https://s3.amazonaws.com/static.graphemica.com/glyphs/i500s/000/007/210/original/0043-500x500.png?1275320965"
        event4.imageUrl = "https://s3.amazonaws.com/static.graphemica.com/glyphs/i500s/000/007/210/original/0043-500x500.png?1275320965"

        event1.name = "Event1"
        event2.name = "Event2"
        event3.name = "Event3"
        event4.name = "Event4"

        eventList.add(event1)
        eventList.add(event2)
        eventList.add(event3)
        eventList.add(event4)


        return eventList
    }


}
