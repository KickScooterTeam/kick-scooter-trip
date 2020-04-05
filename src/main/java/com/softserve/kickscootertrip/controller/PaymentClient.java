package com.softserve.kickscootertrip.controller;

import com.softserve.kickscootertrip.dto.TripDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping(path = "/payments/{userId}/user-solvency")
    Boolean isUserCanPay(@PathVariable UUID userId);

    @PostMapping(path = "/invoices")
    Object createInvoice(@RequestBody TripDto tripDto);

}


