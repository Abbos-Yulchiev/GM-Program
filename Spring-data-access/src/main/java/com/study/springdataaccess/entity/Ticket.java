package com.study.springdataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ticket {

    public enum Categories {STANDARD, PREMIUM, BAR}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long eventId;

    @Enumerated(EnumType.STRING)
    private Categories categories;

    private double price;

    private int place;

    private boolean sold = false;
}
