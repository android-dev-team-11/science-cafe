package com.example.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.adapter.EventAdapter
import com.example.api.ApiService
import com.example.api.EventService
import com.example.api.NewsService
import com.example.api.RewardService
import com.example.models.Event
import com.example.models.Reward
import com.example.models.News
import com.example.sciencecafe.R
import com.example.sciencecafe.databinding.FragmentRewardDetailBinding
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RewardDetail : Fragment() {
    private lateinit var binding: FragmentRewardDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentRewardDetailBinding.inflate(inflater, container, false)
        val args = RewardDetailArgs.fromBundle(arguments!!)
        loadRewardById(args.rewardId)
        loadQualifiedEventsById(args.rewardId)
        return this.binding.root
    }

    fun loadRewardById(id:Int) {
        val rewardService: RewardService = ApiService.apiService.create(RewardService::class.java)
        val observable = rewardService.getRewardById(id)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({r: Reward? ->
                binding.criteria.text =  "Criteria: " + r!!.criteria
                binding.rewardName.text = r!!.name
                binding.dateRange.text = convertLongToDateString(r!!.startDate) + " ~ " + convertLongToDateString(r!!.endDate)
                binding.rewardDescription.loadData(r!!.description,"text/html; charset=utf-8", "utf-8")
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }

    fun loadQualifiedEventsById(id:Int) {
        val eventService: EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.getQualifiedEvents(id)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({e: List<Event>? ->
              val adapter = EventAdapter(e!!)
                adapter?.setOnItemClickListener(object: EventAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        var eventId:Int = adapter.items[position].id
                        view.findNavController().navigate(RewardDetailDirections.actionRewardDetailToEventDetail(eventId))
                    }
                })
                binding.qualifiedEventList.adapter = adapter
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }
}
