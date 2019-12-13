package com.example.api

import com.example.models.LoginRequest
import com.example.models.LoginResponse
import com.example.models.Progress
import com.example.models.User
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.http.*

interface UserService {
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Observable<LoginResponse>

    @POST("/registers")
    fun register(@Body user: User): Observable<User>

    @GET("/user/progresses")
    fun getProgresses(): Observable<List<Progress>>

    @GET("/event/{eventId}/attendees")
    fun getAttendeesById(@Path("eventId") eventId:Int): Observable<List<User>>

    @GET("/profile")
    fun getProfile(): Observable<User>
}