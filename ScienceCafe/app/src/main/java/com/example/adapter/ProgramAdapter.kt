package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.models.News
import com.example.sciencecafe.databinding.ProgramItemViewBinding
import com.example.models.Program
import com.example.sciencecafe.R
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso

class ProgramAdapter(val items: List<Program>): RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {

    private lateinit var binding: ProgramItemViewBinding

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ProgramItemViewBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }


    class ViewHolder constructor(val binding: ProgramItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        val programName: TextView = binding.programName
        val programFullname: TextView = binding.programFullname
        val programImage: ImageView = binding.programImage

        fun bind(item: Program) {
            programName.text = item.name
            programFullname.text = item.fullName
            Picasso.get().load(item.imageUrl).error(R.drawable.default_program).into(programImage)
        }

    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    class ProgramListener(val clickListener: (programId: Int) -> Unit) {
        fun onClick(program: Program) = clickListener(program.id)
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
}