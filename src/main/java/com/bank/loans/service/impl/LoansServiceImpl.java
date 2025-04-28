package com.bank.loans.service.impl;

import com.bank.loans.constants.LoansConstants;
import com.bank.loans.dto.LoansDto;
import com.bank.loans.entity.Loans;
import com.bank.loans.exception.LoanAlreadyExistsException;
import com.bank.loans.exception.ResourceNotFoundException;
import com.bank.loans.mapper.LoansMapper;
import com.bank.loans.repository.LoansRepository;
import com.bank.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent())
            throw new LoanAlreadyExistsException("Loan already registered with this mobile number : "+mobileNumber);
        loansRepository.save(createNewLoan(mobileNumber));
    }


    private Loans createNewLoan(String mobileNumber) {
        Loans loan = new Loans();
        loan.setMobileNumber(mobileNumber);
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loan.setLoanNumber(Long.toString(randomLoanNumber));
        loan.setLoanType(LoansConstants.HOME_LOAN);
        loan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loan;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public LoansDto fetchLoanDetails(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
               () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        //mapper
        LoansDto loansDto = LoansMapper.mapToLoansDto(loans, new LoansDto());
        return loansDto;
    }

    /**
     * @param loansDto
     * @return
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loanNumber", loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loans, loansDto);
        loans = loansRepository.save(loans);
        return true;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

}
