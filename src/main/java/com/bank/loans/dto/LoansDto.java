package com.bank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loans", description = "Schema to hold loan information")
public class LoansDto {

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    @Schema(description = "Customer Mobile No.", example = "9090909090")
    private String mobileNumber;

    @NotEmpty(message = "Loan number cannot be null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    @Schema(description = "Loan Number", example = "548732457654")
    private String loanNumber;

    @NotEmpty(message = "Loan Type cannot be null or empty")
    @Schema(description = "Loan Type", example = "Home Loan")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(description = "Total Loan", example = "1000000")
    private int totalLoan;

    @PositiveOrZero(message = "Loan amount paid should be equal or greater than zero")
    @Schema(description = "Loan amount paid", example = "1000")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(description = "Total outstanding amount against a loan", example = "99000")
    private int outstandingAmount;
}
