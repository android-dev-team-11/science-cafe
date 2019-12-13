package com.example.activities


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.api.ApiService
import com.example.api.UserService
import com.example.models.User
import com.example.sciencecafe.databinding.FragmentQrcodeBinding
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.glxn.qrgen.android.QRCode
/**
 * A simple [Fragment] subclass.
 */
class MyQRCode : Fragment() {

    private lateinit var binding: FragmentQrcodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQrcodeBinding.inflate(inflater, container, false)
        generateQRCode()
        return binding.root
        //return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }

    fun generateQRCode() {
        val userService: UserService = ApiService.apiService.create(UserService::class.java)
        val observable = userService.getProfile()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({u: User? ->
                val bitmap = QRCode.from(""+u!!.id).withSize(1000,1000).bitmap()
                binding.qrcode.setImageBitmap(bitmap)
            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }


}
