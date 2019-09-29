package com.ragul.adsplayapi.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PaymentRequest {
    private Double amount;
    private Long gameId;
}
