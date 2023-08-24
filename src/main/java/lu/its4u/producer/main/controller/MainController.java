package lu.its4u.producer.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lu.its4u.dto.Event;
import lu.its4u.producer.kafka.KafkaProducer;

@RestController
public class MainController {

	@Autowired
	private KafkaProducer producer;

	@GetMapping(value = { "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> main(final HttpServletRequest request, @RequestParam("message") String msg, @RequestParam("count") int count) {
		for (int i = 0; i < count; i++) {
			producer.sendMessage(new Event("event	_" + i + "_" + System.currentTimeMillis(), msg));
		}
		return new ResponseEntity<String>("Sent " + count + " messages correcly", HttpStatus.OK);
	}

}
