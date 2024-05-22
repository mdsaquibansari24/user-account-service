package com.extrade.usermanagement.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder(builderMethodName = "of")
@ToString
public class ErrorMessage {
    private final String messageId;
    private final String errorCode;
    private final Date messageDateTime;
    private final String errorMessage;
    private final String originator;
}
