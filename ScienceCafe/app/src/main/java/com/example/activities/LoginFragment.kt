package com.example.activities


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.sciencecafe.R
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.api.ApiService
import com.example.api.UserService
import com.example.sciencecafe.databinding.FragmentLoginBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import com.auth0.android.jwt.JWT
import com.example.api.AuthenticationInterceptor
import com.example.models.LoginRequest
import com.example.models.LoginResponse
import com.example.models.User
import com.example.utils.PreferenceHelper
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.RequestBody


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate<FragmentLoginBinding>(inflater,
            R.layout.fragment_login,container,false)
        val login:Button = binding.login
        val username:EditText = binding.username
        val password:EditText = binding.password
        var warnings:TextView = binding.warning
        val userService: UserService = ApiService.apiService.create(UserService::class.java)
        val register:Button = binding.register
        register.setOnClickListener {view:View->
            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        login.setOnClickListener{
            if (username.text.equals("") || password.text.equals("")) {
                warnings.text = "Please enter username and password"
            } else {
                val usernameString:String = username.text.toString()
                val passwordString:String = password.text.toString()
                val loginRequest:LoginRequest = LoginRequest(usernameString,passwordString)
                val observable = userService.login(loginRequest)
                observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {r:LoginResponse?->
                            val jwt = r!!.jwt
                            if (jwt==null) {
                                warnings.text ="Wrong username and password"
                            } else if (jwt.equals("inactive")) {
                                warnings.text ="Inactive account!Please go to your email to activate your account."
                            } else {
                                val decoded = JWT(jwt)
                                val isAdmin:Boolean = decoded.getClaim("isAdmin").asBoolean()!!
                                val name:String = decoded.getClaim("firstName").asString()!! + " " + decoded.getClaim("lastName").asString()!!
                                AuthenticationInterceptor.jwtToken = jwt
                                val pref = activity?.getPreferences(Context.MODE_PRIVATE)
                                val edit = pref!!.edit()
                                with(edit) {
                                    edit.putBoolean("isAdmin",isAdmin)
                                    edit.putBoolean("isLogin",true)
                                    edit.putString("name",name)
                                    edit.putString("token",jwt)
                                    commit()
                                }
                                val intent = Intent(context, MainActivity::class.java)
                                context!!.startActivity(intent)
                            }
                         },{ error ->
                            Log.v("Retrofit",error.message)
                            warnings.text ="Wrong username and password"
                        })
            }
        }

        return binding.root
    }


}

