package com.example.kafkaconsumer.demo.consumer;

import com.example.kafkaconsumer.demo.data.FileData;
import com.example.kafkaconsumer.demo.data.FileEvent;
import com.example.kafkaconsumer.demo.data.FileEventType;
import com.example.kafkaconsumer.demo.service.FileEventsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FileEventsConsumer {

    @Autowired
    private FileEventsService fileEventsService;

    @Autowired
    FileData fileData;

    @Autowired
    ObjectMapper objectMapper;

    List<String> line = new ArrayList<>();

    @KafkaListener(topics = {"file-events"})
    public void onMessage(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {

//        line.add(fileEventsService.processFileEvent(consumerRecord));
////
////        log.info(String.valueOf(line));
        FileEvent fileEvent = objectMapper.readValue(consumerRecord.value(), FileEvent.class);

//        switch (fileEvent.getFileEventType()){
//            case NOFILE:
//                log.info("파일 없습니다");
//
//            case SEND:
//                log.info("파일 있습니다 data: {}", fileEvent.getData());
//                line.add(fileEvent.getData());
//
//            case END:
//                log.info("파일 다보냄");
//                log.info(String.valueOf(line));
//        }

        if(fileEvent.getFileEventType() == FileEventType.NOFILE){
                log.info("파일 없습니다");

        }else if(fileEvent.getFileEventType() == FileEventType.SEND){
                line.add(fileEvent.getData());

        }else if (fileEvent.getFileEventType() == FileEventType.END){
                log.info(String.valueOf(line));

        }


    }


}
