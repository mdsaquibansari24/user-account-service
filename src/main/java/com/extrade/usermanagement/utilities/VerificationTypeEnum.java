package com.extrade.usermanagement.utilities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum VerificationTypeEnum {
    VERIFICATION_TYPE_MOBILE("VERIFICATION_TYPE_MOBILE"),
    VERIFICATION_TYPE_EMAIL("VERIFICATION_TYPE_EMAIL");



    private String name;
}
