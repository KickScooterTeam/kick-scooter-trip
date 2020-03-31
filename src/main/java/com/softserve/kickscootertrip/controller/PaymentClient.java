package com.softserve.kickscootertrip.controller;

import com.softserve.kickscootertrip.dto.TripDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "payment-client", url = "${payment-url}")
public interface PaymentClient {

    @PostMapping(path = "/payment/user-validation")
    Boolean isUserCanPay(@RequestParam UUID userId);

    @PostMapping(path = "/invoices")
    Object createInvoice(@RequestBody TripDto tripDto);

}


