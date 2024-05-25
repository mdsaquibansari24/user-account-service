package com.extrade.usermanagement.exception.handler;

import com.extrade.usermanagement.dto.ErrorMessage;
import com.extrade.usermanagement.dto.ErrorMessageFactory;
import com.extrade.usermanagement.exception.AccountVerificationException;
import com.extrade.usermanagement.exception.UserAccountNotFoundException;
import com.extrade.usermanagement.exception.UserAlreadyActiveException;
import com.extrade.usermanagement.exception.VerificationCodeMisMatchException;
import com.extrade.usermanagement.utilities.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class ErrorMessageExceptionHandler {
    private final ErrorMessageFactory errorMessageFactory;

    @ExceptionHandler(AccountVerificationException.class)
    public ResponseEntity<ErrorMessage> handleAccountVerificationException(HttpServletRequest request ,AccountVerificationException e){
        log.error(request.getRequestURI().toString(),e);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessageFactory.failure(e, ErrorCode.OTP_ALREADY_VERIFIED));
    }

    @ExceptionHandler(VerificationCodeMisMatchException.class)
    public ResponseEntity<ErrorMessage> handleVerificationCodeMisMatchException(HttpServletRequest request,VerificationCodeMisMatchException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageFactory.failure(e,ErrorCode.VERIFICATION_CODE_MISMATCH));
    }
    @ExceptionHandler(UserAlreadyActiveException.class)
    public ResponseEntity<ErrorMessage> handleUserAlreadyActiveException(HttpServletRequest request,UserAlreadyActiveException e){  // CARRIED THE EXCEPTION FROM SERVICE CLASS
        return ResponseEntity.status(HttpStatus.GONE).body(errorMessageFactory.failure(e,ErrorCode.USER_ALREADY_ACTIVATED)); // 2 VALUE YAHAN SE JAA RAHA HAI
    }

    @ExceptionHandler({UserAccountNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleUserAccountNotFoundException(HttpServletRequest request,UserAccountNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageFactory.failure(e,ErrorCode.USER_NOT_FOUND));
    }


}
