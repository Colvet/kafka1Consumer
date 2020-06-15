package com.example.kafkaconsumer.demo.service;

import com.example.kafkaconsumer.demo.data.FileEvent;
import com.example.kafkaconsumer.demo.data.FileEventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileEventsService {

    @Autowired
    ObjectMapper objectMapper;

    public String processFileEvent(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        FileEvent fileEvent = objectMapper.readValue(consumerRecord.value(), FileEvent.class);

        if(fileEvent.getFileEventType() == FileEventType.NOFILE){
            return fileEvent.getData();


        }else if(fileEvent.getFileEventType() == FileEventType.SEND){
            return fileEvent.getData();

        }else if (fileEvent.getFileEventType() == FileEventType.END){
            return fileEvent.getData();

        }

//        switch (fileEvent.getFileEventType()){
//
//            case SEND:
//                log.info("파일 받음");
//            case NOFILE:
//                log.info("파일 없음");
//
//            case END:
//                log.info("파일 다 받음");
//        }
        return null;
    }
}
