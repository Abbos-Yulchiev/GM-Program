package epam.com.springtesting.service.impl;

import epam.com.springtesting.entity.Ticket;
import epam.com.springtesting.exceptions.InsufficientAmountException;
import epam.com.springtesting.repository.TicketRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TicketServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TicketServiceImplTest {

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @MockBean
    private TicketRepository ticketRepository;

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    private final Ticket ticket = new Ticket();
    private final ArrayList<Ticket> ticketList = new ArrayList<>();
    private final Ticket mockTicket = mock(Ticket.class);
    private final ArrayList<Ticket> mockTicketList = new ArrayList<>();


    @BeforeEach
    private void init() {
        //ticket property set
        ticket.setAddressId(123);
        ticket.setCategories(Ticket.Categories.STANDARD);
        ticket.setEventId(123L);
        ticket.setId(123L);
        ticket.setPrice(10.0d);
        ticket.setSold(true);
        ticket.setUserId(123);

        //add a ticket to Ticket List
        ticketList.add(ticket);


        doNothing().when(mockTicket).setAddressId(anyInt());
        doNothing().when(mockTicket).setCategories(any());
        doNothing().when(mockTicket).setEventId(anyLong());
        doNothing().when(mockTicket).setId(anyLong());
        doNothing().when(mockTicket).setPrice(anyDouble());
        doNothing().when(mockTicket).setSold(anyBoolean());
        doNothing().when(mockTicket).setUserId(anyInt());
        mockTicket.setAddressId(123);
        mockTicket.setCategories(Ticket.Categories.STANDARD);
        mockTicket.setEventId(123L);
        mockTicket.setId(123L);
        mockTicket.setPrice(10.0d);
        mockTicket.setSold(true);
        mockTicket.setUserId(123);

        mockTicketList.add(mockTicket);
    }

    @Test
    void testBookTicket() {
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt())).thenReturn(new ArrayList<>());

        assertThrows(InsufficientAmountException.class,
                () -> ticketServiceImpl.bookTicket(123, 123L, 1L, Ticket.Categories.STANDARD, 10));

        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
    }


    @Test
    void testBookTicket2() {

        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt()))
                .thenReturn(new ArrayList<>());

        assertThrows(InsufficientAmountException.class,
                () -> ticketServiceImpl.bookTicket(123, 123L, 1L, Ticket.Categories.PREMIUM, 10));

        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
    }


    @Test
    void testBookTicket3() {
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt())).thenReturn(new ArrayList<>());

        assertThrows(InsufficientAmountException.class,
                () -> ticketServiceImpl.bookTicket(123, 123L, 1L, Ticket.Categories.BAR, 10));

        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
    }


    @Test
    void testBookTicket4() {
        when(ticketRepository.saveAll(any())).thenReturn(new ArrayList<>());
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt())).thenReturn(new ArrayList<>());

        assertEquals("You've ordered [0] ticket.",
                ticketServiceImpl.bookTicket(123, 123L, 1L, Ticket.Categories.STANDARD, 0));

        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
        verify(ticketRepository).saveAll(any());
    }

    @Test
    void testBookTicket5() {

        when(ticketRepository.saveAll(any())).thenReturn(ticketList);
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt()))
                .thenReturn(new ArrayList<>());
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("Topic", "42");
        when(kafkaTemplate.send(any(), any()))
                .thenReturn(new AsyncResult<>(new SendResult<>(producerRecord,
                        new RecordMetadata(new TopicPartition("Topic", 1), 1L, 1, 10L, 3, 3))));

        assertEquals("You've ordered [0] ticket.",
                ticketServiceImpl.bookTicket(123, 123L, 1L, Ticket.Categories.STANDARD, 0));
        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
        verify(ticketRepository).saveAll(any());
        verify(kafkaTemplate).send(any(), any());
    }

    @Test
    void testBookTicket6() {

        when(ticketRepository.saveAll(any())).thenReturn(mockTicketList);
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt()))
                .thenReturn(new ArrayList<>());
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("Topic", "42");
        when(kafkaTemplate.send(any(), any())).thenReturn(new AsyncResult<>(
                new SendResult<>(producerRecord, new RecordMetadata(new TopicPartition("Topic", 1), 1L, 1, 10L, 3, 3))));

        assertEquals("You've ordered [0] ticket.",
                ticketServiceImpl.bookTicket(123, 123L, 1L, Ticket.Categories.STANDARD, 0));
        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
        verify(ticketRepository).saveAll(any());
        verify(mockTicket).setAddressId(anyInt());
        verify(mockTicket).setCategories(any());
        verify(mockTicket).setEventId(anyLong());
        verify(mockTicket).setId(anyLong());
        verify(mockTicket).setPrice(anyDouble());
        verify(mockTicket).setSold(anyBoolean());
        verify(mockTicket).setUserId(anyInt());
        verify(kafkaTemplate).send(any(), any());
    }

    @Test
    void testBookTicket7() {

        when(ticketRepository.saveAll(any())).thenReturn(mockTicketList);
        when(ticketRepository.getTicketList(anyLong(), anyLong(), any(), anyInt())).thenReturn(ticketList);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("Topic", "42");
        when(kafkaTemplate.send(any(), any())).thenReturn(new AsyncResult<>(
                new SendResult<>(producerRecord, new RecordMetadata(new TopicPartition("Topic", 1), 1L, 1, 10L, 3, 3))));

        assertEquals("You've ordered [0] ticket.",
                ticketServiceImpl.bookTicket(123, 123L, 1L, Ticket.Categories.STANDARD, 0));
        verify(ticketRepository).getTicketList(anyLong(), anyLong(), any(), anyInt());
        verify(ticketRepository).saveAll(any());
        verify(mockTicket).setAddressId(anyInt());
        verify(mockTicket).setCategories(any());
        verify(mockTicket).setEventId(anyLong());
        verify(mockTicket).setId(anyLong());
        verify(mockTicket).setPrice(anyDouble());
        verify(mockTicket).setSold(anyBoolean());
        verify(mockTicket).setUserId(anyInt());
        verify(kafkaTemplate).send(any(), any());
    }
}

