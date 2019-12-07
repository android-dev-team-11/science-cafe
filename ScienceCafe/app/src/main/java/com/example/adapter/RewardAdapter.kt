package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Reward
import com.example.sciencecafe.R
import kotlinx.android.synthetic.main.list_item_reward.view.*

class RewardAdapter(val data: MutableList<Reward>) : RecyclerView.Adapter<RewardAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val rewardText: TextView = this.view.rewardText
    }

    override fun getItemCount(): Int = this.data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_reward, parent, false) as ConstraintLayout

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.data[position]

        holder.rewardText.text = item.name
        holder.rewardText.setOnClickListener{
            println("TEST1")
        }
    }
}