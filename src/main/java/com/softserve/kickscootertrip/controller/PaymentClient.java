package com.softserve.kickscootertrip.controller;

import com.softserve.kickscootertrip.dto.TripDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping(path = "/payments/{userId}/user-solvency")
    Boolean isUserCanPay(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken, @PathVariable UUID userId);

    @PostMapping(path = "/invoices")
    Object createInvoice(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken, @RequestBody TripDto tripDto);

}


