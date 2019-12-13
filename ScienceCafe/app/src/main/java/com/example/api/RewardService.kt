package com.example.api

import com.example.models.Reward
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path

interface RewardService {
    @GET("/approvedRewards")
    fun getAllApprovedRewards(): Observable<List<Reward>>

    @GET("/rewards")
    fun getAllRewards(): Observable<List<Reward>>

    @GET("/reward/{rewardId}")
    fun getRewardById(@Path("rewardId") rewardId:Int): Observable<Reward>


}