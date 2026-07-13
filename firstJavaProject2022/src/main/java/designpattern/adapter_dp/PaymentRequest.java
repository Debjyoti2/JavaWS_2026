package designpattern.adapter_dp;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class PaymentRequest {

    private String customerId;
    private BigDecimal paymentAmount;
    private String paymentCurrency;
    private String beneficiaryAcntNo;


}
