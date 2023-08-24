package lu.its4u.producer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lu.its4u.dto.Event;

@Component

public class KafkaProducer {
	@Value("${kafka.topic.name}")
	private String topic;

	private final KafkaTemplate<String, Event> kafkaTemplate;

	@Autowired
	public KafkaProducer(KafkaTemplate<String, Event> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(Event event) {
		this.kafkaTemplate.send(this.topic, event);
	}

}
