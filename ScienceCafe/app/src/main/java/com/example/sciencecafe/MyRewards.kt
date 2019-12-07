package com.example.sciencecafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sciencecafe.databinding.FragmentMyRewardsBinding

/**
 * A simple [Fragment] subclass.
 */
class MyRewards : Fragment() {

    private lateinit var binding: FragmentMyRewardsBinding

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyRewardsBinding.inflate(inflater, container, false)



                val sampleRewards = ArrayList<MyRewardItem>()
                sampleRewards .add(MyRewardItem("Exp 1", "Reward Title  1"))
                sampleRewards .add(MyRewardItem("Exp 2", "Reward Title  2"))
                sampleRewards .add(MyRewardItem("Exp 3", "Reward Title  3"))
                sampleRewards .add(MyRewardItem("Exp 4", "Reward Title  4"))

                sampleRewards .add(MyRewardItem("Exp 5", "Reward Title  5"))
                sampleRewards .add(MyRewardItem("Exp 6", "Reward Title  6"))
                sampleRewards .add(MyRewardItem("Exp 7", "Reward Title  7"))
                sampleRewards .add(MyRewardItem("Exp 8", "Reward Title  8"))

                sampleRewards .add(MyRewardItem("Exp 9", "Reward Title  9"))
                sampleRewards .add(MyRewardItem("Exp 10", "Reward Title  10"))
                sampleRewards .add(MyRewardItem("Exp 11", "Reward Title  11"))
                sampleRewards .add(MyRewardItem("Exp 12", "Reward Title  12"))
                val adapter = MyRewardsAdapter(sampleRewards)
                binding.myRewardsList.adapter = adapter
        return binding.root
        //return inflater.inflate(R.layout.fragment_my_rewards, container, false)
    }


}
