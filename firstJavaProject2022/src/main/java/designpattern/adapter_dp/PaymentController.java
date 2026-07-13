package com.designpattern.adapter_dp;

import designpattern.adapter_dp.PaymentRequest;
import designpattern.adapter_dp.PaymentResponse;
import designpattern.adapter_dp.ProcessPaymentAdapter;

//@RestController
public class PaymentController {

   // @PostMapping("/do-payment")
    public PaymentResponse doPayment( PaymentRequest paymentRequest){
        ProcessPaymentAdapter paymentAdapter = new ProcessPaymentAdapter();
        PaymentResponse paymentResponse =  paymentAdapter.doPayment(paymentRequest);
        return paymentResponse;
    }


}
