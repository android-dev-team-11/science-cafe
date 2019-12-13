package com.example.activities


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.example.adapter.NewsAdapter
import com.example.api.ApiService
import com.example.api.NewsService
import com.example.models.News
import com.example.sciencecafe.R
import com.example.sciencecafe.databinding.FragmentNewsListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
        loadAllNews()
        return binding.root
    }

    fun loadAllNews() {
        val newsService: NewsService = ApiService.apiService.create(NewsService::class.java)
        val observable = newsService.getAllNews()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({t: List<News>? ->
                val adapter = NewsAdapter(t!!)

                adapter?.setOnItemClickListener(object: NewsAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        var newsId:Int = adapter.items[position].id
                        view.findNavController().navigate(NewsListDirections.actionNewsListToNewsDetail(newsId))
                    }
                })
                binding.newsList.adapter = adapter

            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })

    }



}
