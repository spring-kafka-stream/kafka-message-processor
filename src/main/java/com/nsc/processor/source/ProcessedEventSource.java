package com.nsc.processor.source;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Output;

public interface ProcessedEventSource {
    String OUTPUT = "processedEventOut";

    @Output(OUTPUT)
    KStream<String, String> output();
}
