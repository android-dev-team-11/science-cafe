package com.example.sciencecafe

import android.util.Log
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    init {
        Log.i("ProfileiewModel", "ProfileViewModel created!")
    }



    override fun onCleared() {
        super.onCleared()
        Log.i("ProfileViewModel", "ProfileViewModel destroyed!")
    }
}