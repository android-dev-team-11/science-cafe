package com.example.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.api.ApiService
import com.example.api.EventService
import com.example.models.Event
import com.example.models.User
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScannerActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    /*
    * Scanner View that will create the layout for scanning a barcode.
    * If you want a custom layout above the scanner layout, then implement
    * the scanning code in a fragment and use the fragment inside the activity,
    * add callbacks to obtain result from the fragment
    * */
    private lateinit var mScannerView: ZBarScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZBarScannerView(this)
        setContentView(mScannerView)
        val b = intent.extras
        val eventId: Int = b!!.getInt("eventId")
    }

    /*
    * It is required to start and stop camera in lifecycle methods
    * (onResume and onPause)
    * */
    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }

    /*
    * Barcode scanning result is displayed here.
    * (For demo purposes only toast is shown here)
    * For understanding what more can be done with the result,
    * visit the GitHub README(https://github.com/dm77/barcodescanner)
    * */
    override fun handleResult(result: Result?) {
        Toast.makeText(this, result?.contents, Toast.LENGTH_SHORT).show()

        val b = intent.extras
        val eventId: Int = b!!.getInt("eventId")
        addAttendeeById(eventId,result?.contents!!.toInt())
        //Camera will stop after scanning result, so we need to resume the
        //preview in order scan more codes
        mScannerView.resumeCameraPreview(this)
    }

    fun addAttendeeById(id:Int,userId:Int) {
        val eventService: EventService = ApiService.apiService.create(EventService::class.java)
        val observable = eventService.addAttendeeById(id,userId)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({u: List<User>? ->

            },{ error ->
                Log.v("Retrofit","Loading news failed")
            })
    }
}

