package com.example.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.api.ApiService
import com.example.api.NewsService
import com.example.api.ProgramService
import com.example.models.Program
import com.example.models.News
import com.example.sciencecafe.databinding.FragmentProgramDetailBinding
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.example.sciencecafe.R

class ProgramDetail : Fragment() {
    private lateinit var binding: FragmentProgramDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentProgramDetailBinding.inflate(inflater, container, false)
        val args = ProgramDetailArgs.fromBundle(arguments!!)
        loadProrgamById(args.programId)
        return this.binding.root
    }

    fun loadProrgamById(id:Int) {
        val programService: ProgramService = ApiService.apiService.create(ProgramService::class.java)
        val observable = programService.getProgramById(id)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({p: Program? ->
                binding.programFullname.text = p!!.fullName
                binding.programName.text = p!!.name
                Picasso.get().load(p!!.imageUrl).error(R.drawable.default_program).into(binding.programImage)
                binding.programDescription.loadData(p!!.description,"text/html; charset=utf-8", "utf-8")
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }
}
