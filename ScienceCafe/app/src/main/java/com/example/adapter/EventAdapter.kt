package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sciencecafe.databinding.EventItemViewBinding
import com.example.models.Event
import com.example.sciencecafe.R
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso

class EventAdapter(val items: List<Event>): RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private lateinit var binding: EventItemViewBinding

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = EventItemViewBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }

    fun setOnItemClickListener(listener: EventAdapter.OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    class EventListener(val clickListener: (eventId: Int) -> Unit) {
        fun onClick(event: Event) = clickListener(event.id)
    }

    class ViewHolder constructor(val binding: EventItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        val eventTitle: TextView = binding.eventTitle
        val eventDate: TextView = binding.eventDate
        val eventImage: ImageView = binding.eventImage

        fun bind(item: Event) {
            eventTitle.text = item.name
            eventDate.text = convertLongToDateString(item.eventDate)
            Picasso.get().load(item.imageUrl).error(R.drawable.default_event).into(eventImage)
        }


    }
}