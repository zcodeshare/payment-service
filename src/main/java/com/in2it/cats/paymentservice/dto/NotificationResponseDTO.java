package com.in2it.cats.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDTO {

    @JsonProperty("is_success")
    private boolean isSuccess;

    private Object data;

    @JsonProperty("error_info")
    private Object errorInfo;
}
