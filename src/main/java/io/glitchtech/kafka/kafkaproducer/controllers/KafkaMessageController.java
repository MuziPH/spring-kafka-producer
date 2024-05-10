package io.glitchtech.kafka.kafkaproducer.controllers;

import io.glitchtech.kafka.kafkaproducer.model.IncomingMessage;
import io.glitchtech.kafka.kafkaproducer.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaMessageController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/post")
    public String sendMessageToKafka(@RequestBody IncomingMessage incomingMessage) {
        kafkaProducerService.sendMessage(
                incomingMessage.getTopic(),
                incomingMessage.getKey(),
                incomingMessage.getValue()
        );
        return String.format(
                "Success - Message for key %s sent to Kafka Topic: %s",
                incomingMessage.getKey(), incomingMessage.getTopic()
        );
    }
}
