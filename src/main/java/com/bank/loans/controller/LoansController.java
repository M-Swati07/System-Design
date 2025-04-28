package com.bank.loans.controller;

import com.bank.loans.constants.LoansConstants;
import com.bank.loans.dto.ErrorResponseDto;
import com.bank.loans.dto.LoansDto;
import com.bank.loans.dto.ResponseDto;
import com.bank.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
@Tag(name = "CRUD REST APIs Bank Microservice", description = "CRUD operations - create/fetch/update/delete")
public class LoansController {

    private ILoansService iLoansService;

    @Operation(
            summary = "Create New Loan"
            //description if needed
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTPS STATUS CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTPS INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                      String mobileNumber){
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loan Details"
            //description if needed
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTPS STATUS OK"),
            @ApiResponse(responseCode = "500", description = "HTTPS INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/fetchLoan")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                         String mobileNumber){
        LoansDto loansDto = iLoansService.fetchLoanDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(
            summary = "Update Loan Details"
            //description if needed
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTPS STATUS OK"),
            @ApiResponse(responseCode = "417", description = "HTTPS EXPECTATION FAILED"),
            @ApiResponse(responseCode = "500", description = "HTTPS INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @PutMapping("/updateLoan")
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto){
        boolean flag = iLoansService.updateLoan(loansDto);
        if(flag)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        else return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
    }

    @Operation(
            summary = "Delete Loan Account"
            //description if needed
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTPS STATUS OK"),
            @ApiResponse(responseCode = "417", description = "HTTPS EXPECTATION FAILED"),
            @ApiResponse(responseCode = "500", description = "HTTPS INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @DeleteMapping("/deleteLoan")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam String mobileNumber){
        boolean flag = iLoansService.deleteLoan(mobileNumber);
        if(flag)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        else return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
    }
}
