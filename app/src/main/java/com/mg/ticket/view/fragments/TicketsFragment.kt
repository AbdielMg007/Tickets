package com.mg.ticket.view.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.mg.ticket.R
import com.mg.ticket.helper.TicketClick
import com.mg.ticket.databinding.FragmentTicketsBinding
import com.mg.ticket.model.enties.Ticket
import com.mg.ticket.view.adapters.AdapterTicket
import com.mg.ticket.viewmodel.TicketsViewModel


class TicketsFragment : Fragment(R.layout.fragment_tickets), TicketClick {

    private lateinit var binding: FragmentTicketsBinding
    private val ticketsViewModel: TicketsViewModel by viewModels()
    private lateinit var adapter: AdapterTicket


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTicketsBinding.bind(view)
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected)
            setup()
        else
            Toast.makeText(context, "No hay internet", Toast.LENGTH_SHORT).show()
    }

    private fun setup() {
        adapter = AdapterTicket(arrayListOf(), this)

        binding.recyclerNew.layoutManager = LinearLayoutManager(context)
        binding.recyclerNew.adapter = adapter
        binding.recyclerInProgress.layoutManager = LinearLayoutManager(context)
        binding.recyclerInProgress.adapter = adapter
        binding.recyclerResolved.layoutManager = LinearLayoutManager(context)
        binding.recyclerResolved.adapter = adapter

        ticketsViewModel.list.observe(viewLifecycleOwner) { newTicket ->
            adapter.updateItems(newTicket)
        }
    }

    override fun ticketsClickListener(Ticket: Ticket) {
        Toast.makeText(context, "Hola", Toast.LENGTH_SHORT).show()
    }

}