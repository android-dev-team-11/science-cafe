package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sciencecafe.databinding.FragmentNewsDetailBindingImpl


/**
 * A simple [Fragment] subclass.
 */
class NewsDetail : Fragment() {

    private lateinit var binding: FragmentNewsDetailBindingImpl

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }


}
