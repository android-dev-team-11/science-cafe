package com.example.activities


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.adapter.NewsAdapter
import com.example.models.Reward
import com.example.adapter.RewardAdapter
import com.example.api.ApiService
import com.example.api.RewardService
import com.example.models.News
import com.example.sciencecafe.R
import com.example.sciencecafe.databinding.FragmentRewardListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RewardList : Fragment() {
    private lateinit var adapter: RewardAdapter
    private lateinit var binding: FragmentRewardListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        this.binding = FragmentRewardListBinding.inflate(inflater, container, false)
        loadAllRewards()
        return binding.root
        //return inflater.inflate(R.layout.fragment_reward_list, container, false)
    }
    fun loadAllRewards() {
        val rewardService: RewardService = ApiService.apiService.create(RewardService::class.java)
        val observable = rewardService.getAllApprovedRewards()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({r: List<Reward>? ->
                val adapter = RewardAdapter(r!!)

                adapter?.setOnItemClickListener(object: RewardAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        var rewardId:Int = adapter.items[position].id
                        view.findNavController().navigate(RewardListDirections.actionRewardListToRewardDetail(rewardId))
                    }
                })
                binding.rewardList.adapter = adapter
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })

    }
}
