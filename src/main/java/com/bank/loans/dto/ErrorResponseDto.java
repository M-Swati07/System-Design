package com.bank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
//Annotations like @Schema are used for OpenAPI Documentation purpose
@Schema(name = "Error Response", description = "Schema to hold error response details")
public class ErrorResponseDto {

    @Schema(description = "API path invoked by client")
    private String apiPath;

    @Schema(description = "Error Code")
    private HttpStatus errorCode;

    @Schema(description = "Error Message")
    private String errorMsg;

    @Schema(description = "Error Timestamp")
    private LocalDateTime errorTime;
}
