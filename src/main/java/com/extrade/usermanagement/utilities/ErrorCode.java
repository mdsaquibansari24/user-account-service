package com.extrade.usermanagement.utilities;

public interface ErrorCode {
    String USER_NOT_FOUND = "user.notFound";
    String USER_ALREADY_ACTIVATED = "user.alreadyActivated";
    String VERIFICATION_CODE_MISMATCH = "otpCode.mismatch";
    String OTP_ALREADY_VERIFIED = "otpCode.alreadyVerified";
}
