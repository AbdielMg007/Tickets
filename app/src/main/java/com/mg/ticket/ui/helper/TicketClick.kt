package com.mg.ticket.ui.helper

import com.mg.ticket.data.model.Ticket


interface TicketClick {

    fun ticketsClickListener(Ticket: Ticket)

}