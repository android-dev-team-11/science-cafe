package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sciencecafe.databinding.FragmentQrcodeBinding

/**
 * A simple [Fragment] subclass.
 */
class QRCode : Fragment() {

    private lateinit var binding: FragmentQrcodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQrcodeBinding.inflate(inflater, container, false)

        return binding.root
        //return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }


}
