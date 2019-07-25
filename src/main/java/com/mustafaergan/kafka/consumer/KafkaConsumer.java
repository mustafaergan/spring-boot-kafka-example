package com.mustafaergan.kafka.consumer;

import com.mustafaergan.kafka.model.KafkaModel;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    int control = 0;
    boolean fail = true;

//    @KafkaListener(topics = "TOPIC_KAFKA_MODEL",containerFactory = "kafkaListenerContainerFactory")
//    public void listen1(KafkaModel model) {
//        System.out.println("Kafka Model Key:: " + model.getKey());
//    }


    //Hata Durumlarinda
    @KafkaListener(topics = "${kafka.topic}",containerFactory = "kafkaListenerContainerFactory")
    public void listen2(KafkaModel model) {
        System.out.println("Model Key:"+model.getKey());
        if (fail) {
            if(control > 10)
                fail = false;
            control++;
            throw new RuntimeException("failed");
        }
    }

//    @KafkaListener(topics = "TOPIC_KAFKA_MODEL")
//    public void listenWithHeaders(
//            @Payload KafkaModel model,
//            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        System.out.println(
//                "Received Message: " + model.getKey()"
//                        + "from partition: " + partition);
//    }
//
//    @KafkaListener(
//            topicPartitions = @TopicPartition(topic = "topicName",
//                    partitionOffsets = {
//                            @PartitionOffset(partition = "0", initialOffset = "0"),
//                            @PartitionOffset(partition = "3", initialOffset = "0")
//                    }))
//    public void listenToParition(
//            @Payload KafkaModel model,
//            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        System.out.println(
//                "Received Messasge: " + model.getKey()"
//                        + "from partition: " + partition);
//    }


}
