package com.bank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
//Annotations like @Schema are used for OpenAPI Documentation purpose
@Schema(name = "Response", description = "Schema to hold response")
public class ResponseDto {

    @Schema(description = "Status")
    private String statusCode;

    @Schema(description = "Status Message")
    private String statusMsg;
}
