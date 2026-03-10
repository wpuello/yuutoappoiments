package com.yuutoap.Appoiments.handlers;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class ApiGeneralResponses<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
}
