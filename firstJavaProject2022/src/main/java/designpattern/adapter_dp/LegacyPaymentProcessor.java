package designpattern.adapter_dp;

import java.math.BigDecimal;


public class LegacyPaymentProcessor {

    public int processLegacyPayment(String customerId, BigDecimal paymentAmount,
                                    String paymentCurrency,String beneficiaryAcntNo){
        try{
            System.out.println("Internal logic to process payment");

        }catch (Exception e){
            return 0 ;

        }
        return 1;
    }

}
