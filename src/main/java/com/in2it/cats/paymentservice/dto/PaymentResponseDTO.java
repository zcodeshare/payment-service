package com.in2it.cats.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private String id;
    private String orderId;
    private String userId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
}