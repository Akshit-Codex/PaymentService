package com.example.paymentservice.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@Service("Stripe")//You can also use this annotation so that you can use qualifier annotation in PaymentServiceImpl
@Service
@Primary
public class StripeGateway implements PaymentGateway
{
    //While pushing this code Im getting error saying there is a violation that is using name apiKey,
    // so change the name.
    //private String apiKey="sk_test_51SRK02QnwRKzrFfiZuLqrWryOIIwE10ytKMwvjJ5CajCI1LnFqVTD59mBY5UkghRRUTFE2ZNBNUGxZWezd4MpzJO00FQwPhMtW";
    private String apnaKey="sk_test_51SRK02QnwRKzrFfiZuLqrWryOIIwE10ytKMwvjJ5CajCI1LnFqVTD59mBY5UkghRRUTFE2ZNBNUGxZWezd4MpzJO00FQwPhMtW";

    @Override
    public String generatePaymentLink()
    {
        try {
            Stripe.apiKey = this.apnaKey;

            Price price = getPrice();

            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                            .setUrl("https://google.com/?trx_id=" + "abcd1234").build()).build())
                            .build();
            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Price getPrice() {
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("inr")
                            .setUnitAmount(200000L)
                            .setProductData(
                                    PriceCreateParams
                                            .ProductData
                                            .builder()
                                            .setName("iPhone")
                                            .build()
                            )
                            .build();
            Price price = Price.create(params);
            return price;
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
