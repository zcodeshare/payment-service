package com.in2it.cats.paymentservice.service.impl;

import com.in2it.cats.paymentservice.client.NotificationClient;
import com.in2it.cats.paymentservice.constant.PaymentConstants;
import com.in2it.cats.paymentservice.dto.NotificationRequestDTO;
import com.in2it.cats.paymentservice.dto.PaymentRequestDTO;
import com.in2it.cats.paymentservice.dto.PaymentResponseDTO;
import com.in2it.cats.paymentservice.entity.Payment;
import com.in2it.cats.paymentservice.exception.PaymentNotFoundException;
import com.in2it.cats.paymentservice.repository.PaymentRepository;
import com.in2it.cats.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final NotificationClient notificationClient;

    @Override
    public PaymentResponseDTO createPayment(
            PaymentRequestDTO request) {

        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .status(PaymentConstants.PAYMENT_PENDING)
                .build();

        Payment savedPayment =
                paymentRepository.save(payment);

        NotificationRequestDTO notificationRequest =
                new NotificationRequestDTO(
                        savedPayment.getUserId(),
                        savedPayment.getOrderId(),
                        "PAYMENT_CREATED",
                        "Your payment has been created successfully",
                        "EMAIL"
                );

        notificationClient.createNotification(notificationRequest);

        return PaymentResponseDTO.builder()
                .id(savedPayment.getId())
                .orderId(savedPayment.getOrderId())
                .userId(savedPayment.getUserId())
                .amount(savedPayment.getAmount())
                .paymentMethod(savedPayment.getPaymentMethod())
                .status(savedPayment.getStatus())
                .build();
    }

    @Override
    public PaymentResponseDTO getPaymentById(String id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new PaymentNotFoundException(id));

        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .build();
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(payment -> PaymentResponseDTO.builder()
                        .id(payment.getId())
                        .orderId(payment.getOrderId())
                        .userId(payment.getUserId())
                        .amount(payment.getAmount())
                        .paymentMethod(payment.getPaymentMethod())
                        .status(payment.getStatus())
                        .build())
                .toList();
    }

    @Override
    public PaymentResponseDTO updatePayment(
            String id,
            PaymentRequestDTO request) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new PaymentNotFoundException(id));

        payment.setOrderId(request.getOrderId());
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());

        Payment updatedPayment =
                paymentRepository.save(payment);

        return PaymentResponseDTO.builder()
                .id(updatedPayment.getId())
                .orderId(updatedPayment.getOrderId())
                .userId(updatedPayment.getUserId())
                .amount(updatedPayment.getAmount())
                .paymentMethod(updatedPayment.getPaymentMethod())
                .status(updatedPayment.getStatus())
                .build();
    }

    @Override
    public void deletePayment(String id) {

        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException(id);
        }

        paymentRepository.deleteById(id);
    }
}