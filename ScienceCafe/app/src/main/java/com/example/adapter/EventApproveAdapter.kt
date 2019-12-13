package com.example.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.ApiService
import com.example.api.EventService
import com.example.sciencecafe.databinding.EventApproveItemViewBinding
import com.example.models.Event
import com.example.sciencecafe.R
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.activities.ApproveDirections


class EventApproveAdapter(val items: List<Event>): RecyclerView.Adapter<EventApproveAdapter.ViewHolder>() {

    private lateinit var binding: EventApproveItemViewBinding

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = EventApproveItemViewBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }

    fun setOnItemClickListener(listener: EventApproveAdapter.OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    class EventListener(val clickListener: (eventId: Int) -> Unit) {
        fun onClick(event: Event) = clickListener(event.id)
    }

    class ViewHolder constructor(val binding: EventApproveItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        val eventTitle: TextView = binding.eventTitle
        val eventDate: TextView = binding.eventDate
        val eventImage: ImageView = binding.eventImage
        val approve: Button = binding.approve
        val reject: Button = binding.reject


        fun bind(item: Event) {
            eventTitle.text = item.name
            eventDate.text = convertLongToDateString(item.eventDate)
            Picasso.get().load(item.imageUrl).error(R.drawable.default_event).into(eventImage)
            val eventService: EventService = ApiService.apiService.create(EventService::class.java)
            approve.setOnClickListener {
                val observable = eventService.approveEventById(item.id)
                observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({e: Event? ->

                    },{ error ->
                        Log.v("Retrofit","Loading news failed")
                    })
                itemView.findNavController().navigate(ApproveDirections.actionApproveToEventDetail(item.id))
            }
            reject.setOnClickListener {
                val observable = eventService.rejectEventById(item.id)
                observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({e: Event? ->

                    },{ error ->
                        Log.v("Retrofit","Loading news failed")
                    })
                itemView.findNavController().navigate(ApproveDirections.actionApproveToEventDetail(item.id))
            }
        }




    }
}