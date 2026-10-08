package com.in2it.cats.paymentservice.service;

import com.in2it.cats.paymentservice.dto.PaymentRequestDTO;
import com.in2it.cats.paymentservice.dto.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO request);
    PaymentResponseDTO getPaymentById(String id);
    List<PaymentResponseDTO> getAllPayments();
    PaymentResponseDTO updatePayment(String id, PaymentRequestDTO request);
    void deletePayment(String id);
}