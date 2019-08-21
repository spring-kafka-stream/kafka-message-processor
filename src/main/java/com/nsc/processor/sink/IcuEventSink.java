package com.nsc.processor.sink;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface IcuEventSink {
    String INPUT = "icuEventIn";

    /*@Input(INPUT)
    SubscribableChannel input();*/

    @Input(INPUT)
    KStream<String, String> input();
}
