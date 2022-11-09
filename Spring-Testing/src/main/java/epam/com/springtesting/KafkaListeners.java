package epam.com.springtesting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListeners.class);


    @KafkaListener(topics = "Order", groupId = "ticket")
    void listener(String data) {
        LOGGER.info(String.format("Order: %s", data));
    }
}
