package com.example.api

import com.example.models.Event
import io.reactivex.Observable
import com.example.models.User
import retrofit2.http.*


interface EventService {
    @GET("/approvedEvents")
    fun getAllApprovedEvents(): Observable<List<Event>>

    @GET("/events")
    fun getAllEvents(): Observable<List<Event>>

    @GET("/pendingEvents")
    fun getAllPendingEvents(): Observable<List<Event>>

    @GET("/ownApprovedEvents")
    fun getOwnEvents(): Observable<List<Event>>

    @GET("/event/{eventId}")
    fun getEventById(@Path("eventId") eventId:Int): Observable<Event>

    @PUT("/event/approve/{id}")
    fun approveEventById(@Path("id") eventId: Int): Observable<Event>

    @POST("/event/{id}/addAttendee/{userId}")
    fun addAttendeeById(@Path("id") eventId: Int, @Path("userId") userId: Int): Observable<List<User>>

    @PUT("/event/reject/{id}")
    fun rejectEventById(@Path("id") eventId: Int): Observable<Event>

    @GET("/reward/{id}/events")
    fun getQualifiedEvents(@Path("id") id:Int): Observable<List<Event>>

    @GET("/user/events")
    fun getAttendedEvents(): Observable<List<Event>>

    @FormUrlEncoded
    @POST("/events")
    fun postEvent(@Field("name") name:String,@Field("location") location:String,
                  @Field("description") description:String, @Field("eventDate") eventDate:String,
                  @Field("startTime") startTime:String, @Field("endTime") endTime:String,
                  @Field("status") staus:String = "0"): Observable<Event>
}