package com.mustafaergan.kafka.consumer;

import com.mustafaergan.kafka.model.KafkaModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {


    @KafkaListener(topics = "TOPIC_KAFKA_MODEL",containerFactory = "kafkaListenerContainerFactory")
    public void listen(KafkaModel model) {
        System.out.println("Kafka Model Key:: " + model.getKey());
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
