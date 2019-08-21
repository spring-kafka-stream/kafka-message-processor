package com.nsc.processor;

import com.nsc.processor.sink.IcuEventSink;
import com.nsc.processor.source.ProcessedEventSource;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@EnableBinding({IcuEventSink.class, ProcessedEventSource.class})
public class IcuEventProcessor {

    static int i = 1;

    @Autowired
    private SimpMessagingTemplate template;

    /*@StreamListener(target = IcuEventSink.INPUT)
    public void process(String message) {
        System.out.println(message);
        if (i++ % 2 == 0) {
            new Thread(() -> processWithHalfSecondDelay(message)).start();
        } else {
            new Thread(() -> processWithOneSecondDelay(message)).start();
        }
    }

    public void processWithHalfSecondDelay(String message) {
        try {
            Thread.sleep(5000);
            template.convertAndSend("/topic/event", message + " from one");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processWithOneSecondDelay(String message) {
        try {
            Thread.sleep(10000);
            template.convertAndSend("/topic/event", message + "from 2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    @StreamListener(target = IcuEventSink.INPUT)
    @SendTo(ProcessedEventSource.OUTPUT)
    public KStream<String, String> process(KStream<String, String> kStream) {
        return kStream.map((key, value) -> KeyValue.pair(key, value + " transformed")).peek((key, value) -> {
            int millis = 3000;
            if (i++ % 2 == 0) {
                millis = 2000;
            }
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
