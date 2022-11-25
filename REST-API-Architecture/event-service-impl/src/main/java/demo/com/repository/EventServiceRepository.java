package demo.com.repository;

import demo.com.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventServiceRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM EVENT WHERE title = ?1", nativeQuery = true)
    List<Event> getAllEventsByTitle(String title);
}
