package com.extrade.usermanagement.service;


import com.extrade.usermanagement.dto.UserAccountDto;
import com.extrade.usermanagement.entities.Role;
import com.extrade.usermanagement.entities.UserAccount;
import com.extrade.usermanagement.repositories.RoleRepository;
import com.extrade.usermanagement.repositories.UserAccountRepository;
import com.extrade.usermanagement.utilities.RandomGenerator;
import com.extrade.usermanagement.utilities.RoleCodeEnum;
import com.extrade.usermanagement.utilities.UserAccountConstants;
import com.extrade.usermanagement.utilities.UserAccountStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserManagementServiceImpl implements UserManagmentService {
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public long countUsersByEmailAddress(String emailAddress) {
        return userAccountRepository.countByEmailAddress(emailAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public long countUsersByMobileNo(String mobileNo) {

        return userAccountRepository.countByMobileNo(mobileNo);
    }

    @Override
    @Transactional(readOnly=false)
    public long registerCustomer(UserAccountDto userAccountDto) {
        Long userAccountId=null;
        Role userRole=null;
        UserAccount userAccount=null;
        RandomGenerator randomGenerator=null;
        LocalDateTime time=null;
        String emailVerificationOtpCode=null;
        String mobileNoVerificationOtpCode=null;
        time=LocalDateTime.now();

        userRole=roleRepository.findByRoleCode(RoleCodeEnum.CUSTOMER.toString());
        log.info("fetching user roleId: {} with user role code: {}",userRole.getRoleId(),RoleCodeEnum.CUSTOMER.toString());

        randomGenerator=new RandomGenerator();
        emailVerificationOtpCode=randomGenerator.emailAddressVerificationOtpGenerator(6);
        mobileNoVerificationOtpCode=randomGenerator.mobileNoVerificationOtpGenerator(6);


        userAccount=new UserAccount();
        userAccount.setUserAccountId(userAccountDto.getUserAccountId());
        userAccount.setFirstName(userAccountDto.getFirstName());
        userAccount.setLastName(userAccountDto.getLastName());
        userAccount.setEmailAddress(userAccountDto.getEmailAddress());
        userAccount.setMobileNo(userAccountDto.getMobileNo());
        userAccount.setDob(userAccountDto.getDob());
        userAccount.setGender(userAccountDto.getGender());
        userAccount.setPassword(userAccountDto.getPassword());
        userAccount.setEmailVerificationOtpCode(emailVerificationOtpCode);
        userAccount.setMobileNoVerificationOtpCode(mobileNoVerificationOtpCode);
        userAccount.setEmailVerificationOtpCodeGeneratedDate(time);
        userAccount.setMobileNoVerificationOtpCodeGeneratedDate(time);
        userAccount.setRegisteredDate(LocalDate.now());
        userAccount.setUserRole(userRole);
        userAccount.setMobileNoVerificationStatus((short) 0);
        userAccount.setEmailVerificationStatus((short) 0);
        userAccount.setLastModifiedDate(time);
        userAccount.setLastModifiedBy(UserAccountConstants.SYSTEM_USER);
        userAccount.setStatus(UserAccountStatusEnum.REGISTERED.toString());

        userAccountId=userAccountRepository.save(userAccount).getUserAccountId();
        log.info("userAccount with email: {} saved with userAccountId: {}",userAccount.getEmailAddress(),userAccountId);
          return userAccountId;
    }


}

