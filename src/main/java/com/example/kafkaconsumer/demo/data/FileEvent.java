package com.example.kafkaconsumer.demo.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileEvent {

    private String fileName;
    private FileEventType fileEventType;
    private String data;

}
