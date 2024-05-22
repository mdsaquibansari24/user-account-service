package com.extrade.usermanagement.dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
@AllArgsConstructor
@Component
public class ErrorMessageFactory {
    private final String originator="user-account-service";
    public ErrorMessage failure(Exception exception,String errorCode){
        return ErrorMessage.of()
                .messageId(UUID.randomUUID().toString())
                .errorCode(errorCode)
                .messageDateTime(new Date())
                .errorMessage(exception.getMessage()) //exception message is comming from service exception message
                .originator(originator)
                .build();
    }
}
