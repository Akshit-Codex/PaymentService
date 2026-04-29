package com.example.paymentservice.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService
{
//    private final PaymentGateway paymentGateway;
//    public PaymentServiceImpl(PaymentGateway paymentGateway)
//    {
//        this.paymentGateway = paymentGateway;
//    }
// We can't use above injection as there may be a chance that either of gateways(Stripe or Razorpay)
// is down, so we want it more dynamic. That's why we created PaymentGatewaySelector to select
// gateway dynamically. If we use above injection then if let's say Stripe gateway is down then
// it will throw an exception and since we have annotated StripeGateway with @primary then again
// we have to make RazorpayGateway as primary gateway which looks like a manual job.

    private final PaymentGatewaySelector paymentGatewaySelector;
    public PaymentServiceImpl(PaymentGatewaySelector paymentGatewaySelector)
    {
        this.paymentGatewaySelector = paymentGatewaySelector;
    }
   @Override
    public String initiatePayment()
    {
        return paymentGatewaySelector
                .get()
                .generatePaymentLink();
    }

}
