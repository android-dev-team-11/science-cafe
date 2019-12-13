package com.example.activities

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.sciencecafe.databinding.FragmentSignUpBinding
import android.widget.Toast
import com.example.adapter.AttendeeAdapter
import com.example.api.ApiService
import com.example.api.UserService
import com.example.models.User
import com.example.sciencecafe.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate<FragmentSignUpBinding>(inflater,
            R.layout.fragment_sign_up,container,false)
        val login:Button = binding.login
        val firstname = binding.firstName
        val lastname = binding.lastName
        val username = binding.username
        val password = binding.password
        val email = binding.email
        val repeatPassword:EditText = binding.repeatPassword
        login.setOnClickListener {view:View->
            view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.register.setOnClickListener {view:View->
            val user:User = User()
            user.username = username.text.toString()
            user.firstName = firstname.text.toString()
            user.lastName = lastname.text.toString()
            user.password = password.text.toString()
            user.email = email.text.toString()
            register(user)
            view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        return binding.root
    }

    fun register(user:User) {
        val userService: UserService = ApiService.apiService.create(UserService::class.java)
        val observable = userService.register(user)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({u: User? ->
                Toast.makeText(activity,"Successful registration! Please login to your email to activate your account.", Toast.LENGTH_LONG)

            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }

}
