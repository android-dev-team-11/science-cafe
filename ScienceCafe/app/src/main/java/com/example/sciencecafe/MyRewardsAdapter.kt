package com.example.sciencecafe

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


import kotlinx.android.synthetic.main.my_reward_card_view.view.*
import kotlinx.android.synthetic.main.my_reward_card_view.view.my_rewards_title_view
import kotlinx.android.synthetic.main.my_reward_card_view.view.edit_button_my_rewards
import kotlinx.android.synthetic.main.my_reward_card_view.view.delete_button_my_rewards


class MyRewardsAdapter(val items : ArrayList<MyRewardItem>) : RecyclerView.Adapter<MyRewardsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.my_reward_card_view, parent, false)
        return ViewHolder(v)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myreward: MyRewardItem = items[position]
        holder.rewardsTitleV.text = myreward.myRewardsTitle

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rewardsTitleV: TextView = itemView.my_rewards_title_view


    }
}