package epam.com.springtesting.repository;

import epam.com.springtesting.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM ticket WHERE user_id = ?1", nativeQuery = true)
    Page<Ticket> findByUser(int userId, Pageable pageable);

    @Query(value = "SELECT * FROM ticket WHERE event_id = ?1", nativeQuery = true)
    Page<Ticket> findByEvent(long eventId, Pageable pageable);

    @Query(value = "SELECT * FROM ticket WHERE event_id = ?1\n" +
            "AND address_id = ?2 AND categories = ?3 AND sold = false LIMIT ?4", nativeQuery = true)
    List<Ticket> getTicketList(long eventId, long addressId, String categories, int limit);

    @Query(value = "SELECT * FROM ticket WHERE user_id = ?1 and event_id = ?2\n" +
            "AND address_id = ?3 AND categories = ?4 AND sold = true", nativeQuery = true)
    List<Ticket> cancelTicket(int userId, long eventId, long addressId, String categories);
}
