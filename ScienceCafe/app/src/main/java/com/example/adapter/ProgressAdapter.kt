package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.models.Progress
import com.example.sciencecafe.R
import com.example.sciencecafe.databinding.ProgramItemViewBinding
import com.example.sciencecafe.databinding.ProgressItemBinding
import com.example.sciencecafe.databinding.RewardItemViewBinding
import com.example.utils.convertLongToDateString

class ProgressAdapter(val items: List<Progress>) : RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    private lateinit var binding: ProgressItemBinding

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ProgressItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }


    class ViewHolder constructor(val binding: ProgressItemBinding) : RecyclerView.ViewHolder(binding.root){
        val rewardTitle: TextView = binding.title
        val progress: ProgressBar = binding.progressBar
        val percent: TextView = binding.percent

        fun bind(item: Progress) {
            rewardTitle.text = item.reward.name
            val value = progress.max * item.percentage / 100;
            percent.text = "Progress: " + item.percentage + "%"
            progress.progress = value
        }

    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }


    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    class RewardListener(val clickListener: (rewardId: Int) -> Unit) {
        fun onClick(progress: Progress) = clickListener(progress.reward.id)
    }
}