package com.mustafaergan.kafka;

import com.mustafaergan.kafka.consumer.KafkaConsumer;
import com.mustafaergan.kafka.model.KafkaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(path = "/kafka")
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Autowired
	private KafkaTemplate<String, KafkaModel> kafkaTemplate;

//	@Autowired
//    private KafkaConsumer kafkaConsumer;

	@Value(value = "${kafka.topic}")
	private String kafkaTopic;

	@RequestMapping(value = "/{key}/{value}", method = RequestMethod.GET)
	String sendMessage(@PathVariable("key") String key,
                       @PathVariable("value") String value){
		System.out.println("Text is "+key);
        kafkaTemplate.send(kafkaTopic, new KafkaModel(key,value));
//		ListenableFuture<SendResult<String, KafkaModel>> future =
//		future.addCallback(new ListenableFutureCallback<SendResult<String, KafkaModel>>() {
//			@Override
//			public void onSuccess(SendResult<String, KafkaModel> result) {
//				System.out.println("Sent message=[" + key +
//						"] with offset=[" + result.getRecordMetadata().offset() + "]");
//			}
//			@Override
//			public void onFailure(Throwable ex) {
//				System.out.println("Unable to send message=["
//						+ key + "] due to : " + ex.getMessage());
//			}
//		});
		return "send message";
	}

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    String getMessage() {
//
//    }


}
