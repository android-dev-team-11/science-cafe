package com.example.api

import com.example.models.Program
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path

interface ProgramService {
    @GET("/programs")
    fun getAllPrograms(): Observable<List<Program>>

    @GET("/program/{programId}")
    fun getProgramById(@Path("programId") rewardId:Int): Observable<Program>
}