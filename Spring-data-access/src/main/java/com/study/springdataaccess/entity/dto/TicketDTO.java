package com.study.springdataaccess.entity.dto;

import com.study.springdataaccess.entity.Ticket;
import lombok.Data;

@Data
public class TicketDTO {

    long eventId;
    Ticket.Categories categories;
    int place;
    int amount;
}
