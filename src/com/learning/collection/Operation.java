package com.learning.collection;

import java.math.BigDecimal;
import java.util.Optional;

public class Operation {
    private  String accNo;
    private  BigDecimal amount;

    public String getAccNo() {
        return accNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Operation(String accNo, BigDecimal amount) {
        this.accNo = accNo;
        this.amount = amount;
    }

}

