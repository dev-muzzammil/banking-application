package com.banking.customer.service.Exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String message,
        int status,
        LocalDateTime timeStamp,
        Map<String, String> fieldErrors ) {

        public ErrorResponse(String message, int status, LocalDateTime timeStamp) {
            this(message, status, timeStamp, null); // fallback if no field errors
        }

}
