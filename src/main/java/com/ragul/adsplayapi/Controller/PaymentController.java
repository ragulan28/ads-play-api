package com.ragul.adsplayapi.Controller;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Model.Game;
import com.ragul.adsplayapi.Model.Payment;
import com.ragul.adsplayapi.Service.GameService;
import com.ragul.adsplayapi.Service.PaymentService;
import com.ragul.adsplayapi.payload.ApiResponse;
import com.ragul.adsplayapi.payload.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Payment>> payment(@RequestBody PaymentRequest request) {
        Payment payment=this.paymentService.save(request);

        return new ResponseEntity<>(new ApiResponse<>(payment, HttpStatus.CREATED, "Company create successfully"), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Payment>> findById(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        return new ResponseEntity<>(new ApiResponse<>(payment), HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Payment>>> findAll() {
        return new ResponseEntity<>(new ApiResponse<>(paymentService.findAll()), HttpStatus.OK);
    }

}
