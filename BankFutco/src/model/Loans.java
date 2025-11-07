package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loans {

    private LocalDate date;
    private String type; //Home - Vehicle - University - Personal
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmt;
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public BigDecimal getTotalLoan() {
        return totalLoan;
    }
    public void setTotalLoan(BigDecimal totalLoan) {
        this.totalLoan = totalLoan;
    }
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
    public BigDecimal getOutstandingAmt() {
        return outstandingAmt;
    }
    public void setOutstandingAmt(BigDecimal outstandingAmt) {
        this.outstandingAmt = outstandingAmt;
    }
    @Override
    public String toString() {
        return "Loans [date=" + date + ", type=" + type + ", totalLoan=" + totalLoan + ", amountPaid=" + amountPaid
                + ", outstandingAmt=" + outstandingAmt + "]";
    }
}

