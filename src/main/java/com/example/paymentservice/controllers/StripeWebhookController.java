package com.example.paymentservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeWebhookController
{
    @PostMapping("/stripeWebhook")
    public void stripeWebhook(@RequestBody Object object)
    {
        System.out.println(object);
    }
}
