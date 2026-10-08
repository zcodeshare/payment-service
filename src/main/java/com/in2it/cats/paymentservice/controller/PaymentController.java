package com.in2it.cats.paymentservice.controller;

import com.in2it.cats.paymentservice.dto.PaymentRequestDTO;
import com.in2it.cats.paymentservice.dto.PaymentResponseDTO;
import com.in2it.cats.paymentservice.dto.ResponseDTO;
import com.in2it.cats.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "${payment.create}")
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO request) {

        PaymentResponseDTO data = paymentService.createPayment(request);
        ResponseDTO response = new ResponseDTO(true, data, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "${payment.getById}")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPaymentById(@PathVariable String id) {

        PaymentResponseDTO data = paymentService.getPaymentById(id);
        ResponseDTO response = new ResponseDTO(true, data, null);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "${payment.getAll}")
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllPayments() {

        List<PaymentResponseDTO> data = paymentService.getAllPayments();
        ResponseDTO response = new ResponseDTO(true, data, null);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "${payment.update}")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updatePayment(@PathVariable String id, @Valid @RequestBody PaymentRequestDTO request) {

        PaymentResponseDTO data = paymentService.updatePayment(id, request);
        ResponseDTO response = new ResponseDTO(true, data, null);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "${payment.delete}")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deletePayment(@PathVariable String id) {

        paymentService.deletePayment(id);
        ResponseDTO response = new ResponseDTO(true, null, null);
        return ResponseEntity.ok(response);
    }
}