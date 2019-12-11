package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.sciencecafe.databinding.FragmentEventDetailBinding

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

        binding.rewardButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_eventDetail_to_rewardList)
        }
        binding.newsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_eventDetail_to_newsList)
        }

        binding.eventButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_eventDetail_to_eventList) }

        return binding.root
        //return inflater.inflate(R.layout.fragment_event_detail, container, false)
    }


}
