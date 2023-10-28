package com.mg.ticket.model.network

import com.mg.ticket.model.enties.CallTickets
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiClient {

    @GET
    fun getTickets(@Url url:String): Call<CallTickets>

}