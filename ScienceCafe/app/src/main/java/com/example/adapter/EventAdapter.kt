package com.example.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Event
import com.example.sciencecafe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_event.view.*

class EventAdapter(val data: MutableList<Event>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
        val approveButton: Button = view.approveButton
        val rejectButton: Button = view.rejectButton
    }

    override fun getItemCount(): Int = this.data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_event, parent, false) as LinearLayout

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.data[position]

        Picasso.get().load(item.imageUrl).into(holder.image)

        holder.approveButton.setOnClickListener{
            println("Test 14")
        }

        holder.rejectButton.setOnClickListener{
            println("Test 36")
        }
    }
}