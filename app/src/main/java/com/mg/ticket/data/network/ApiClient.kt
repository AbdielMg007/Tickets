package com.mg.ticket.data.network

import com.mg.ticket.data.model.CallTickets
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiClient {

    @GET
    fun getTickets(@Url url:String): Call<CallTickets>

}