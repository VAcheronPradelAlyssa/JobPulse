package com.alyssa.jobpulse.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String error;
    private String path;
    private List<FieldError> fieldErrors;

    public ErrorResponse(LocalDateTime timestamp, int status, String message, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.error = error;
    }
}
