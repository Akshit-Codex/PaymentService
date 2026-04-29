package com.example.paymentservice.services;

import org.springframework.stereotype.Service;

@Service
public class PaymentGatewaySelectorImpl implements PaymentGatewaySelector
{
    private final RazorpayGateway razorpayGateway;
    private final StripeGateway stripeGateway;
    public PaymentGatewaySelectorImpl(RazorpayGateway razorpayGateway, StripeGateway stripeGateway)
    {
        this.razorpayGateway = razorpayGateway;
        this.stripeGateway = stripeGateway;
    }

    @Override
    public PaymentGateway get()
    {
        //some logic to determine appropriate payment gateway
        return stripeGateway;
    }
}
