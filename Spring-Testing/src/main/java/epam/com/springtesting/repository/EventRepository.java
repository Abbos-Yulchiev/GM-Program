package epam.com.springtesting.repository;

import epam.com.springtesting.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM events WHERE title = ?1", nativeQuery = true)
    Page<Event> findByTitle(String title, Pageable pageable);

    @Query(value = "SELECT * FROM events WHERE event_date = ?1", nativeQuery = true)
    Page<Event> findByDate(Timestamp date, Pageable pageable);
}