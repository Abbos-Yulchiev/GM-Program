package com.study.springdataaccess.entity.model;

import lombok.Data;

@Data
public class TicketEvent {

    long id;

    long eventId;

    int ticketAmount;

    int soldTickets;
}
