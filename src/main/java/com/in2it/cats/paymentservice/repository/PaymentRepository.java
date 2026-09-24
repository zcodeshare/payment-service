package com.in2it.cats.paymentservice.repository;

import com.in2it.cats.paymentservice.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}