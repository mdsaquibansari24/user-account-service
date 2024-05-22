package com.extrade.usermanagement.api;

import com.extrade.usermanagement.dto.AccountVerificationStatusDto;
import com.extrade.usermanagement.dto.ErrorMessage;
import com.extrade.usermanagement.dto.UserAccountDto;
import com.extrade.usermanagement.service.UserManagmentService;
import com.extrade.usermanagement.utilities.VerificationTypeEnum;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class UserAccountApiController {
     private final UserManagmentService userManagmentService;
     @PostMapping(value="/customer",consumes ={MediaType.APPLICATION_JSON_VALUE})
     //if u want documentation to be really generated,we need to write api annotation
     @ApiResponses(value = {@ApiResponse(responseCode = "500",
             content = {@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))}),
             @ApiResponse(responseCode = "200",
                     content = {@Content(mediaType = "*/*", schema = @Schema(implementation = Long.class))})})
     public ResponseEntity<String> registerCustomer(@RequestBody UserAccountDto userAccountDto){
         long userAccountId=0;
         userAccountId=userManagmentService.registerCustomer(userAccountDto);
         return ResponseEntity.ok(String.valueOf(userAccountId));
     }

    @GetMapping(value = "/count/email")
    @ApiResponses(value = {@ApiResponse(responseCode = "500",
            content = {@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "*/*", schema = @Schema(implementation = Long.class))})})
    public ResponseEntity<Long> getNoUsersByEmailAddress(@RequestParam("emailAddress") String emailAddress) {
        long c = 0;
        c=userManagmentService.countUsersByEmailAddress(emailAddress);
        log.info("no of user found are: {} with emailAddress: {}",c,emailAddress);
        return ResponseEntity.ok(c);
    }
    @GetMapping(value="/count/mobileNo")
    @ApiResponses(value = {@ApiResponse(responseCode = "500",
            content = {@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "*/*", schema = @Schema(implementation = Long.class))})})
    public ResponseEntity<Long> getNoOfUserByMobileNo(@RequestParam("mobileNo") String mobileNo){
        long c=0;
        c=userManagmentService.countUsersByMobileNo(mobileNo);
        log.info("no of user found are: {} with mobile number: {}",c,mobileNo);
        return ResponseEntity.ok(c);
    }

    //updating database if needed

    @PutMapping(value = "/{userAccountId}/{otpCode}/{verificationType}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponses(value = {@ApiResponse(responseCode = "404", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))},description = "UserAccount NotFound for Verification"),
    @ApiResponse(responseCode = "208" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))},description = "UserAccount Already Activated"),
    @ApiResponse(responseCode = "400" , content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))},description = "VerificationCode Mis-Match"),
    @ApiResponse(responseCode = "422" , content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))},description = "otp Already verified"),
    @ApiResponse(responseCode = "200" , content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))})})
    public AccountVerificationStatusDto verifyOtpCode(@PathVariable("userAccountId") int userAccountId,
                                                          @PathVariable("otpCode") String otpCode,
                                                          @PathVariable("verificationType") VerificationTypeEnum verificationType){
         AccountVerificationStatusDto accountVerificationStatusDto=null;
         accountVerificationStatusDto=userManagmentService.verifyOtpAndUpdateAccountStatus(userAccountId,otpCode,verificationType);
         return accountVerificationStatusDto;
    }


    @GetMapping(value = "/{userAccountId}/verificationStatus",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponses(value = {@ApiResponse(responseCode = "404", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))},description = "userAccount not found for verification status"),
    @ApiResponse(responseCode = "200" , content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))},description = "otp verified")})
    public AccountVerificationStatusDto accountVerificationStatusDto(@PathVariable("userAccountId") int userAccountId){
        AccountVerificationStatusDto accountVerificationStatusDto=null;

        accountVerificationStatusDto= userManagmentService.accountVerificationStatusDto(userAccountId);
        return accountVerificationStatusDto;
    }

}
