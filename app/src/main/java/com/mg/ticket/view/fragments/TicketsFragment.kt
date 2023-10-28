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
import com.mg.ticket.viewmodel.InProgressTicketsViewModel
import com.mg.ticket.viewmodel.NewTicketsViewModel
import com.mg.ticket.viewmodel.ResolvedTicketsViewModel


class TicketsFragment : Fragment(R.layout.fragment_tickets), TicketClick {

    private lateinit var binding: FragmentTicketsBinding
    private lateinit var newAdapter: AdapterTicket
    private lateinit var inProgressAdapter: AdapterTicket
    private lateinit var resolvedAdapter: AdapterTicket
    private val newTicketsViewModel: NewTicketsViewModel by viewModels()
    private val inProgressTicketsViewModel: InProgressTicketsViewModel by viewModels()
    private val resolvedTicketsViewModel: ResolvedTicketsViewModel by viewModels()


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
        binding.recyclerNew.layoutManager = LinearLayoutManager(context)
        newAdapter = AdapterTicket(arrayListOf(), this)
        binding.recyclerNew.adapter = newAdapter

        newTicketsViewModel.list.observe(viewLifecycleOwner) { newTicket ->
            newAdapter.updateItems(newTicket)
        }

        binding.recyclerInProgress.layoutManager = LinearLayoutManager(context)
        inProgressAdapter = AdapterTicket(arrayListOf(), this)
        binding.recyclerInProgress.adapter = inProgressAdapter

        inProgressTicketsViewModel.list.observe(viewLifecycleOwner) { inProgressTicket ->
            inProgressAdapter.updateItems(inProgressTicket)
        }

        binding.recyclerResolved.layoutManager = LinearLayoutManager(context)
        resolvedAdapter = AdapterTicket(arrayListOf(), this)
        binding.recyclerResolved.adapter = resolvedAdapter

        resolvedTicketsViewModel.list.observe(viewLifecycleOwner) { resolvedTicket ->
            resolvedAdapter.updateItems(resolvedTicket)
        }
    }

override fun ticketsClickListener(Ticket: Ticket) {
        val builder = context?.let { androidx.appcompat.app.AlertDialog.Builder(it) }
        builder?.setTitle("Ticket Details")

        val ticketDetails = buildTicketDetailsString(Ticket)
        builder?.setMessage(ticketDetails)

        builder?.setPositiveButton("Aceptar", null)
        builder?.create()?.show()
    }

    private fun buildTicketDetailsString(ticket: Ticket): String {
        val builder = StringBuilder()
        builder.append("ID: ${ticket.ID}\n")
        builder.append("Title: ${ticket.Title}\n")
        builder.append("Date: ${ticket.Date}\n")
        builder.append("Responsible Name: ${ticket.ResponsibleName}\n")
        builder.append("Team: ${ticket.Team}\n")
        builder.append("Incident Type: ${ticket.IncidentType}\n")
        builder.append("Severity: ${ticket.Severity}\n")
        builder.append("Software Version: ${ticket.SoftwareVersion}\n")
        builder.append("Problem Description: ${ticket.ProblemDescription}\n")
        builder.append("Status: ${ticket.Status}\n")

        return builder.toString()
    }

}