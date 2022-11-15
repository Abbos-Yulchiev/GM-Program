package com.study.springdataaccess.repository;

import com.study.springdataaccess.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM ticket as t " +
            "LEFT JOIN events as e ON t.event_id = e.id " +
            "WHERE t.user_id = ?1 and t.sold = true", nativeQuery = true)
    Page<Ticket> getBookedTicketsByUser(long userId, Pageable pageable);

    @Query(value = "SELECT * FROM ticket AS t " +
            "RIGHT JOIN events AS e on t.event_id = e.id " +
            "WHERE t.event_id = ?1 " +
            "and t.sold = true " +
            "ORDER BY e.event_date", nativeQuery = true)
    Page<Ticket> getBookedTicketsByEvent(long eventId, Pageable pageable);

    @Query(value = "SELECT * FROM ticket WHERE event_id = ?1\n" +
            "AND place = ?2 AND categories = ?3 AND sold = false LIMIT ?4", nativeQuery = true)
    List<Ticket> getTicketList(long eventId, long addressId, String categories, int limit);

    @Query(value = "SELECT t.price FROM ticket AS t " +
            "RIGHT JOIN events AS e on t.event_id = e.id " +
            "WHERE t.event_id = ?1 and t.place = ?2 " +
            "ORDER BY e.event_date " +
            "LIMIT 1", nativeQuery = true)
    double getTicketPrice(long eventId, int placeId);

    @Query(value = "SELECT * FROM TICKET ORDER BY id desc LIMIT 1", nativeQuery = true)
    Ticket getLastEvent();
}
