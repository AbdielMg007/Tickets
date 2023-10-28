package com.mg.ticket.model.enties

data class Ticket(
    val ID: Int,
    val Title: String,
    val Date: String,
    val ResponsibleName: String,
    val Team: String,
    val IncidentType: String,
    val Severity: String,
    val SoftwareVersion: String,
    val ProblemDescription: String,
    val Status: String
)

