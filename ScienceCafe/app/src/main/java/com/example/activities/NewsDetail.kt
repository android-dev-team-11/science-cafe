package com.example.activities


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.adapter.NewsAdapter
import com.example.api.ApiService
import com.example.api.NewsService
import com.example.models.News
import com.example.sciencecafe.databinding.FragmentNewsDetailBinding
import com.example.sciencecafe.databinding.FragmentProgramListBinding
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso
import com.example.sciencecafe.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class NewsDetail : Fragment() {

    private lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        val args = NewsDetailArgs.fromBundle(arguments!!)
        loadNewsById(args.newId)
        return binding.root
    }

    fun loadNewsById(id:Int) {
        val newsService: NewsService = ApiService.apiService.create(NewsService::class.java)
        val observable = newsService.getNewsById(id)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({t: News? ->
                binding.author.text = t!!.author
                binding.postedDate.text = convertLongToDateString(t!!.postedDate)
                binding.newsTitle.text = t!!.title
                Picasso.get().load(t!!.imageUrl).error(R.drawable.default_news).into(binding.newsImage)
                binding.content.loadData(t!!.content,"text/html; charset=utf-8", "utf-8")
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }

}
