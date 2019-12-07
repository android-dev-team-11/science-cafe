package com.example.sciencecafe

import android.util.Log
import androidx.lifecycle.ViewModel

class MyRewardsViewModelModel : ViewModel() {
    init {
        Log.i("MyRewardsViewModelModel", "MyRewardsViewModelModel created!")
    }



    override fun onCleared() {
        super.onCleared()
        Log.i("MyRewardsViewModelModel", "MyRewardsViewModelModel destroyed!")
    }
}