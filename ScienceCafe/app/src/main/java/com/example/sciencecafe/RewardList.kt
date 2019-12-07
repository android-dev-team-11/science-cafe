package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adapter.RewardAdapter
import com.example.model.Reward
import androidx.navigation.findNavController
import com.example.sciencecafe.databinding.FragmentRewardListBinding

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

        val rewardsList: MutableList<Reward> = this.createRewardsList()
        this.adapter = RewardAdapter(rewardsList)
        this.binding.rewardList.adapter = adapter

        binding.eventButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_rewardList_to_eventList)
        }

        binding.newsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_rewardList_to_newsList)
        }
        return binding.root
        //return inflater.inflate(R.layout.fragment_reward_list, container, false)
    }

    private fun createRewardsList(): MutableList<Reward> {
        val rewardsList = mutableListOf<Reward>()

        for (count in 1..100) {
            val reward = Reward()
            reward.name = "Reward ${count}"

            rewardsList.add(reward)
        }

        return rewardsList
    }
}
