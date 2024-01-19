package com.mg.ticket.data.network

import com.mg.ticket.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiDbClient {

    private val client = OkHttpClient.Builder().build()
    private val baseUrl: String = R.string.api_url.toString()

    private val getRetrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

    val service: ApiClient = getRetrofit.create(ApiClient::class.java)

}

