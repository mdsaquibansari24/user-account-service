package com.extrade.usermanagement.exception;

import com.extrade.usermanagement.utilities.VerificationTypeEnum;
import lombok.Getter;

public class AccountVerificationException extends RuntimeException {
     @Getter
     private VerificationTypeEnum verificationType;

    public AccountVerificationException(String message, VerificationTypeEnum verificationType) {
        super(message);
        this.verificationType = verificationType;
    }
}
