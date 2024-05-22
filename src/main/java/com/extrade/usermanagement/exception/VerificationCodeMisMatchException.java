package com.extrade.usermanagement.exception;

import com.extrade.usermanagement.utilities.VerificationTypeEnum;
import lombok.Getter;

public class VerificationCodeMisMatchException extends RuntimeException{

    @Getter
    private VerificationTypeEnum verificationType;

    public VerificationCodeMisMatchException(String message, VerificationTypeEnum verificationType) {
        super(message);
        this.verificationType = verificationType;
    }
}
