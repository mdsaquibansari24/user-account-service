package com.extrade.usermanagement.repositories;

import com.extrade.usermanagement.dto.AccountVerificationStatusDto;
import com.extrade.usermanagement.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    long countByEmailAddress(String emailAddress);

    long countByMobileNo(String mobileNo);

    @Modifying
    @Query("update UserAccount ua set ua.emailVerificationStatus=?2," +
            " ua.mobileNoVerificationStatus=?3, ua.lastModifiedDate=?4, ua.activatedDate=?5," +
            " ua.status=?6 where ua.userAccountId=?1")
    int updateUserAccount(int userAccountId, short emailVerificationCodeVerifiedStatus,
                          short mobileVerificationCodeVerifiedStatus,
                          LocalDateTime lastModifiedDate, LocalDate activatedDate,
                          String accountStatus);




}
