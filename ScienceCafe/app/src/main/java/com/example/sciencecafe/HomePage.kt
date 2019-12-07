package com.example.sciencecafe


import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.sciencecafe.databinding.FragmentHomePageBinding

//https://stackoverflow.com/questions/34706399/how-to-use-data-binding-with-fragment/53932633#53932633
/**
 * A simple [Fragment] subclass.
 */
class HomePage : Fragment() {
    private lateinit var binding: FragmentHomePageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.eventButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homePage_to_eventList)
        }
        binding.rewardButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homePage_to_rewardList)
        }
        binding.newsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homePage_to_newsList)
        }
        return binding.root
    }


}
