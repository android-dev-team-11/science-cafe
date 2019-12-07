package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.sciencecafe.databinding.FragmentEventListBinding

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


        binding.rewardButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_eventList_to_rewardList)
        }
        binding.newsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_eventList_to_newsList)
        }
        return binding.root
        //return inflater.inflate(R.layout.fragment_event_list, container, false)
    }


}
