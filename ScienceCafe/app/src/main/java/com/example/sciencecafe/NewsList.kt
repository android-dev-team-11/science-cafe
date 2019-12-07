package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.sciencecafe.databinding.FragmentNewsListBinding

/**
 * A simple [Fragment] subclass.
 */
class NewsList : Fragment() {
    lateinit var binding: FragmentNewsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentNewsListBinding>(
            inflater,
            R.layout.fragment_news_list, container, false
        )
        binding.eventButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_newsList_to_eventList)
        }
        binding.rewardButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_newsList_to_rewardList)
        }

        val news = ArrayList<NewsItem>()
        news.add(NewsItem("Author", "date", "NewsTitle"))
        news.add(NewsItem("IGN", "2019-11-01", "Star Wars The Rise of Skywalker"))
        news.add(NewsItem("New York Times", "2019-12-11", "Economy Plummeting"))
        news.add(NewsItem("dexerto", "2019-12-12", "Dexerto Stuff"))
        news.add(NewsItem("Rotten Tomatoes", "2019-12-13", "Movies Movies"))
        news.add(NewsItem("Generic Author", "2019-12-14", "Generic Title"))
        news.add(NewsItem("Wannabe Author", "2019-12-15", "Wannabe Title"))

        val adapter = NewsAdapter(news)
        binding.newsList.adapter = adapter
        return binding.root
    }


}
