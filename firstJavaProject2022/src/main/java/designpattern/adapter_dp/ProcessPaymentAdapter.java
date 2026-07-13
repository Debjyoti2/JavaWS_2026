package designpattern.adapter_dp;

public class ProcessPaymentAdapter implements ProcessPayment {
    @Override
    public PaymentResponse doPayment(PaymentRequest paymentRequest) {
       LegacyPaymentProcessor legacyPaymentProcessor = new LegacyPaymentProcessor();

        try {
            int responseid = legacyPaymentProcessor.processLegacyPayment(
                    paymentRequest.getCustomerId(),
                    paymentRequest.getPaymentAmount(),
                    paymentRequest.getPaymentCurrency(),
                    paymentRequest.getBeneficiaryAcntNo()
            );
            if(responseid==1){
                PaymentResponse paymentResponse = PaymentResponse.builder()
                        .responseId("AAA01")
                        .responseStatus("Payment Made Fine")
                        .build();
                return paymentResponse;
            }

        }catch (Exception e){

        }
        return null;
    }
}
