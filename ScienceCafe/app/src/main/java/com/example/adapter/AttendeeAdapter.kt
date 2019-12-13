package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.models.User
import com.example.sciencecafe.R
import com.example.sciencecafe.databinding.AttendeeItemBinding


class AttendeeAdapter(val items: List<User>) : RecyclerView.Adapter<AttendeeAdapter.ViewHolder>() {

    private lateinit var binding: AttendeeItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = AttendeeItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }


    class ViewHolder constructor(val binding: AttendeeItemBinding) : RecyclerView.ViewHolder(binding.root){
        val name: TextView = binding.attendeeName
        val username: TextView = binding.attendeeUsername
        val email: TextView = binding.email

        fun bind(item: User) {
            name.text = "Name: " + item.firstName + " " + item.lastName
            username.text = "Username: " + item.username
            email.text = "Email: " + item.email
        }

    }


}