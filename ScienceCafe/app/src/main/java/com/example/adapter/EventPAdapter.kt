package com.example.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Event
import com.example.sciencecafe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_eventp.view.*
import kotlinx.android.synthetic.main.list_item_eventp.*

class EventPAdapter(val data: MutableList<Event>) : RecyclerView.Adapter<EventPAdapter.ViewHolder>() {


    override fun getItemCount(): Int = this.data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventPAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_eventp, parent, false) as ConstraintLayout

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.data[position]

        Picasso.get().load(item.imageUrl).into(holder.image)
        holder?.name?.text = item.name
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
        val name: TextView = view.name
    }

}