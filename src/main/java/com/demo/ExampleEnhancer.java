/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo;

// [START logging_enhancer]

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.LoggingEnhancer;
import com.google.cloud.logging.Payload;
import com.google.cloud.logging.Severity;
import com.google.cloud.logging.logback.LoggingEventEnhancer;

import java.util.HashMap;
import java.util.Map;

// Add / update additional fields to the log entry
public class ExampleEnhancer implements LoggingEnhancer {

    @Override
    public void enhanceLogEntry(LogEntry.Builder logEntryBuilder) {
//        StringBuilder payload = new StringBuilder("e.getFormattedMessage()").append('\n');

        LogEntry logEntry = logEntryBuilder.build();
        Map<String, String> labels = logEntry.getLabels();
        Payload payload = logEntry.getPayload();
        if (payload instanceof Payload.JsonPayload) {
//            Payload.JsonPayload jsonPayload = (Payload.JsonPayload) payload.getData();
            Map<String, Object> jsonContent = ((Payload.JsonPayload) payload).getDataAsMap();
            String message = jsonContent.get("message").toString();
            String traceId = labels.get("X-B3-TraceId");

            Map<String, Object> newJsonContent = new HashMap<>();
            newJsonContent.put("message", String.format("%s, %s", traceId, message));

            logEntryBuilder.setPayload(Payload.JsonPayload.of(newJsonContent));
        }
    }

//    private String getPattern() {
//        return "%d{yyyy-MM-dd HH:mm:ss.SSSZ} | [%X{X-B3-TraceId},%X{X-B3-SpanId},%X{X-B3-ParentSpanId},%X{X-Span-Export}] | %-8.8p| [%t] | %c{3} | %m%n";
//    }
}
// [END logging_enhancer]