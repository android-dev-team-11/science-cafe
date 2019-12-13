package com.example.activities


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.adapter.EventAdapter
import com.example.adapter.ProgressAdapter
import com.example.api.ApiService
import com.example.api.EventService
import com.example.api.UserService
import com.example.models.Event
import com.example.models.Progress
import com.example.sciencecafe.databinding.FragmentMyProfileBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 */
class MyProfile : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding
    private lateinit var viewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        loadAllProgress()
        loadAttendedEvents()
        binding.logout.setOnClickListener {
            val pref = activity?.getPreferences(Context.MODE_PRIVATE)
            val edit = pref!!.edit()
            with(edit) {
                edit.putBoolean("isAdmin",false)
                edit.putBoolean("isLogin",false)
                edit.putString("name","User Center")
                commit()
            }
            val intent = Intent(context, MainActivity::class.java)
            context!!.startActivity(intent)
        }
        return binding.root
        //return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    fun loadAllProgress() {
        val userService: UserService = ApiService.apiService.create(UserService::class.java)
        val observable = userService.getProgresses()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {p:List<Progress>?->
                    val adapter = ProgressAdapter(p!!)
                    adapter?.setOnItemClickListener(object: ProgressAdapter.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            var rewardId:Int = adapter.items[position].reward.id
                            view.findNavController().navigate(MyProfileDirections.actionMyProfileToRewardDetail(rewardId))
                        }
                    })
                    binding.progressList.adapter = adapter
                },{ error ->
                    Log.v("Retrofit",error.message)
                })
    }

    fun loadAttendedEvents() {
        val eventService: EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.getAttendedEvents()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {e:List<Event>?->
                    val adapter = EventAdapter(e!!)
                    adapter?.setOnItemClickListener(object: EventAdapter.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            var eventId:Int = adapter.items[position].id
                            view.findNavController().navigate(MyProfileDirections.actionMyProfileToEventDetail(eventId))
                        }
                    })
                    binding.attendedList.adapter = adapter
                },{ error ->
                    Log.v("Retrofit",error.message)
                })
    }



}
