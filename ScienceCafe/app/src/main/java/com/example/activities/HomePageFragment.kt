package com.example.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.sciencecafe.databinding.FragmentHomePageBinding
import com.example.sciencecafe.R
import com.example.adapter.EventAdapter
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.api.ApiService
import com.example.api.AuthenticationInterceptor
import com.example.api.EventService
import com.example.api.NewsService
import androidx.navigation.findNavController
import com.example.models.News
import com.example.models.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private var topNews:MutableList<News> =  mutableListOf<News>()
    private var events:MutableList<Event> = mutableListOf<Event>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate<FragmentHomePageBinding>(inflater,
            R.layout.fragment_home_page,container,false)
        binding.moreEvents.setOnClickListener { view : View ->
            view.findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToEventList())
        }
        loadAllNews()
        loadAllEvents()

        return binding.root
    }

    fun loadAllEvents() {
        val eventService:EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.getAllApprovedEvents()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {e:List<Event>?->
                    events.clear()
                    for (i in events) {
                        Log.v("url",i.imageUrl)
                    }
                    if (e!!.size > 3) {
                        for(i in 0..2) {
                            events.add(e[i])
                        }
                    } else {
                        events.addAll(e!!)
                    }
                    val adapter = EventAdapter(events)
                    adapter?.setOnItemClickListener(object: EventAdapter.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            var eventId:Int = adapter.items[position].id
                            view.findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToEventDetail(eventId))
                        }
                    })
                    binding.eventList.adapter = adapter
                },{ error ->
                    Log.v("Retrofit",error.message)
                })

    }

    fun loadAllNews() {
        val newsService:NewsService = ApiService.apiService.create(NewsService::class.java)
        val observable = newsService.getAllTopNews()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({t: List<News>? ->
                topNews.clear()
                topNews.addAll(t!!)
                val imageList = ArrayList<SlideModel>()
                for(news in topNews) {
                    imageList.add(SlideModel(news.imageUrl,news.title,true))
                }
                val imageSlider = binding.imageSlider
                imageSlider.setImageList(imageList)
                imageSlider.setItemClickListener(object : ItemClickListener {
                    override fun onItemSelected(position: Int) {
                        var newsId:Int = 201
                        for (n in topNews) {
                            if (n.imageUrl.equals(imageList.get(position).imageUrl) && n.title.equals(imageList.get(position).title)) {
                                newsId = n.id
                                break
                            }
                        }
                        binding.homePageFragment.findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToNewsDetail(newsId))
                    }
                })
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })

    }

}
