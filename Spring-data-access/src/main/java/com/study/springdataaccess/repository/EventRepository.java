package com.study.springdataaccess.repository;

import com.study.springdataaccess.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    @Query(value = "SELECT * FROM events WHERE title = ?1 ORDER BY id", nativeQuery = true)
    Page<Event> getEventsByTitle(String title, Pageable pageable);

    @Query(value = "SELECT * FROM events WHERE event_date = ?1 ORDER BY id", nativeQuery = true)
    Page<Event> getEventsForDay(String date, Pageable pageable);

}
