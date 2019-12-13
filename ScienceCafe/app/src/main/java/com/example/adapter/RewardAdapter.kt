package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.models.Reward
import com.example.models.Event
import com.example.sciencecafe.R
import com.example.sciencecafe.databinding.EventItemViewBinding
import com.example.sciencecafe.databinding.RewardItemViewBinding
import com.example.utils.convertLongToDateString

class RewardAdapter(val items: List<Reward>) : RecyclerView.Adapter<RewardAdapter.ViewHolder>() {

    private lateinit var binding: RewardItemViewBinding

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RewardItemViewBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }


    class ViewHolder constructor(val binding: RewardItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        val rewardTitle: TextView = binding.title
        val criteria: TextView = binding.criteria

        fun bind(item: Reward) {
            rewardTitle.text = item.name
            criteria.text = "Criteria:" + item.criteria
        }

    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }


    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    class RewardListener(val clickListener: (rewardId: Int) -> Unit) {
        fun onClick(reward: Reward) = clickListener(reward.id)
    }
}