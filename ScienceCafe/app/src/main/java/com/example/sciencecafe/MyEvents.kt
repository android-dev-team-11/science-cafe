package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sciencecafe.databinding.FragmentMyEventsBinding

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

        return binding.root
    }


}
