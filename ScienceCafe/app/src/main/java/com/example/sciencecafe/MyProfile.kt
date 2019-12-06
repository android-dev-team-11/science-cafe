package com.example.sciencecafe


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.sciencecafe.databinding.FragmentMyProfileBinding

/**
 * A simple [Fragment] subclass.
 */
class MyProfile : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding
    private lateinit var viewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)

        Log.i("GameFragment", "Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        return binding.root
        //return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }


}
