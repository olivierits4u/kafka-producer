package lu.its4u.producer.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lu.its4u.dto.Data;
import lu.its4u.producer.kafka.KafkaProducer;

@RestController
public class MainController {

	@Autowired
	private KafkaProducer producer;

	@GetMapping(value = { "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> main(final HttpServletRequest request, @RequestParam("message") String message,
			@RequestParam("count") int count) {
		for (int i = 0; i < count; i++) {
			// Data data=new
			// Data(System.currentTimeMillis(),"name_"+System.currentTimeMillis(), , msg)

			Data data = new Data();
			data.setDate(System.currentTimeMillis());
			data.setDescription(message);
			data.setId("" + data.getDate());
			data.setName("name_" + data.getDate());
			data.setEnvironment("env");
			producer.sendMessage(data);
		}
		return new ResponseEntity<String>("Sent " + count + " messages correcly", HttpStatus.OK);
	}

}
