package com.bank.loans.service;

import com.bank.loans.dto.LoansDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public interface ILoansService {

    void createLoan(String mobileNumber);

    LoansDto fetchLoanDetails(String mobileNumber);

    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(String mobileNumber);
}
