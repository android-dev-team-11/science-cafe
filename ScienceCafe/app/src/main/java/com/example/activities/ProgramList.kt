package com.example.activities


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.adapter.ProgramAdapter
import com.example.api.ApiService
import com.example.api.ProgramService
import com.example.models.Program

import com.example.sciencecafe.R
import com.example.sciencecafe.databinding.FragmentProgramListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ProgramList : Fragment() {

    private lateinit var binding: FragmentProgramListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgramListBinding.inflate(inflater, container, false)
        loadAllPrograms()
        return binding.root
    }

    fun loadAllPrograms() {
        val programService: ProgramService = ApiService.apiService.create(ProgramService::class.java)
        val observable = programService.getAllPrograms()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {p:List<Program>?->
                    val adapter = ProgramAdapter(p!!)
                    adapter?.setOnItemClickListener(object: ProgramAdapter.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            var programId:Int = adapter.items[position].id
                            view.findNavController().navigate(ProgramListDirections.actionProgramListToProgramDetail(programId))
                        }
                    })
                    binding.programList.adapter = adapter
                },{ error ->
                    Log.v("Retrofit",error.message)
                })
    }

}
