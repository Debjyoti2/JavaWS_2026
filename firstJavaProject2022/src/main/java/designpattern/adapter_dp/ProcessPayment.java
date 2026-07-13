package designpattern.adapter_dp;

public interface ProcessPayment {

    public PaymentResponse doPayment(PaymentRequest paymentRequest);
}
