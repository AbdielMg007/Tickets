package com.mg.ticket.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mg.ticket.databinding.CardTicketBinding
import com.mg.ticket.helper.TicketClick
import com.mg.ticket.model.enties.Ticket

class AdapterTicket(private var ticket: ArrayList<Ticket>, private val TicketClick: TicketClick) : RecyclerView.Adapter<AdapterTicket.TicketViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding = CardTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TicketViewHolder, i: Int) {
        holder.bind(ticket[i])
        holder.itemView.setOnClickListener{
            TicketClick.ticketsClickListener(ticket[i])
        }
    }

    override fun getItemCount(): Int = ticket.size

    class TicketViewHolder(val binding: CardTicketBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(ticket: Ticket) {
            binding.idTicket.text = ticket.ID.toString()
            binding.titleTicket.text = ticket.Title
            binding.IncidentType.text = ticket.IncidentType
            binding.Severity.text = ticket.Severity
        }
    }

    fun updateItems(newItems: ArrayList<Ticket>) {
        this.ticket.clear()
        ticket.addAll(newItems)
        notifyDataSetChanged()
    }
}