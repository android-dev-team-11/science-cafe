package com.example.sciencecafe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.model.Reward
import com.example.sciencecafe.databinding.FragmentRewardDetailBinding

class RewardDetail : Fragment() {
    private lateinit var binding: FragmentRewardDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentRewardDetailBinding.inflate(inflater, container, false)

        val reward: Reward = this.createReward()
        binding.reward = reward

        return this.binding.root
    }

    private fun createReward(): Reward {
        val reward = Reward()

        reward.name = "Reward Title"
        reward.description = "Lorem ipsum dolor sit " +
                "amet, consectetur adipiscing elit. " +
                "Aenean euismod bibendum laoreet. " +
                "Proin gravida dolor sit amet lacus " +
                "accumsan et viverra justo commodo. " +
                "Proin sodales pulvinar tempor. " +
                "Cum sociis natoque penatibus et magnis " +
                "dis parturient montes, nascetur ridiculus mus. " +
                "Nam fermentum, nulla luctus pharetra vulputate, " +
                "felis tellus mollis orci, sed rhoncus sapien nunc eget." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing " +
                "elit. Aenean euismod bibendum laoreet. Proin gravida " +
                "dolor sit amet lacus accumsan et viverra justo " +
                "commodo. Proin sodales pulvinar tempor. Cum " +
                "sociis natoque penatibus et magnis dis " +
                "parturient montes, nascetur ridiculus mus. " +
                "Nam fermentum, nulla luctus pharetra vulputate, " +
                "felis tellus mollis orci, sed rhoncus sapien nunc eget." +
                "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Aenean euismod bibendum laoreet. " +
                "Proin gravida dolor sit amet lacus accumsan et viverra justo commodo."

        return reward
    }
}
