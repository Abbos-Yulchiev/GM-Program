import controller.EventController;
import controller.TicketController;
import controller.UserController;
import entity.Event;
import entity.Ticket;
import entity.User;
import entity.dto.EventDTO;
import entity.dto.UserDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws ParseException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.getBean(User.class);
        context.getBean(Ticket.class);
        context.getBean(Event.class);

        UserController userController = context.getBean(UserController.class);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(4);
        userDTO.setUsername("ali");
        userDTO.setEmail("ali@email.com");
        ResponseEntity<String> stringResponseEntity = userController.updateUser(1, userDTO);
        System.out.println(stringResponseEntity);

        ResponseEntity<User> user = userController.getUserById(1);
        System.out.println(user);

        ResponseEntity<String> stringResponseEntity1 = userController.deleteUser(2);
        System.out.println(stringResponseEntity1);


        //--------------------------------------------------------------------------------------------------------------
        EventController eventController = context.getBean(EventController.class);

        ResponseEntity<Event> eventById = eventController.getEventById(1L);
        System.out.println(eventById);

        ResponseEntity<List<Event>> halloween = eventController.getEventsByTitle("Halloween", 5, 0);
        System.out.println(halloween);


        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        System.out.println(date.getTime());
        System.out.println(ts.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(ts));
        System.out.println(formatter.format(date));

        System.out.println("getEventsForDay");
        ResponseEntity<List<Event>> eventsForDay = eventController.getEventsForDay("2022-10-03", 5, 0);
        System.out.println(eventsForDay);

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(4L);
        eventDTO.setTitle("New Year eve");
        eventDTO.setEvent_date("2022-12-31");
        ResponseEntity<String> event = eventController.createEvent(eventDTO);
        System.out.println(event);

        eventDTO.setId(4L);
        eventDTO.setTitle("New Year eve-2");
        eventDTO.setEvent_date("2022-12-31");
        ResponseEntity<String> stringResponseEntity2 = eventController.updateEvent(4, eventDTO);
        System.out.println(stringResponseEntity2);

        ResponseEntity<Event> eventById2 = eventController.getEventById(4L);
        System.out.println(eventById2);

        //----------------------------------------------------------------------------------------------------------------

        TicketController ticketController = context.getBean(TicketController.class);

        ResponseEntity<List<Ticket>> bookedTickets =
                ticketController.getBookedTicketsByUserId(1, 5, 0);
        System.out.println(bookedTickets);

        ResponseEntity<Ticket> ticketResponseEntity = ticketController.bookTicket(1, 2L, 1, Ticket.Categories.BAR);
        System.out.println(ticketResponseEntity);

//        ResponseEntity<Boolean> booleanResponseEntity = ticketController.cancelTicket(1L);
//        System.out.println(booleanResponseEntity);

    }
}
