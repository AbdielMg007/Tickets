package com.mg.ticket.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mg.ticket.data.model.Ticket
import com.mg.ticket.data.network.ApiDbClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTicketsViewModel : ViewModel() {
    var ticketList: ArrayList<Ticket> = arrayListOf()
    var list = MutableLiveData<ArrayList<Ticket>>()

    init {
        getTickets()
    }

    private fun getTickets() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = ApiDbClient.service.getTickets("new")
            val ticket = call.execute().body()
            ticketList = ((ticket?.tickets ?: emptyList()) as ArrayList<Ticket>)
            list.postValue(ticketList)
        }
    }
}
