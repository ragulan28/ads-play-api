package com.ragul.adsplayapi.Service;

import com.ragul.adsplayapi.Model.Payment;
import com.ragul.adsplayapi.Repository.PaymentRepository;
import com.ragul.adsplayapi.exception.ResourceNotFoundException;
import com.ragul.adsplayapi.payload.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    GameService gameService;

    public Payment save(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setDateTime(new Date());
        payment.setGame(gameService.findById(paymentRequest.getGameId()));
        paymentRepository.save(payment);
        return payment;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(Long id) {
        Optional<Payment> company = paymentRepository.findById(id);
        return company.orElseThrow(() -> new ResourceNotFoundException("Payment", id.toString()));
    }
}
