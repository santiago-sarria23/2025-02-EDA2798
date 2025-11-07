package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Balance {

    private LocalDate date;
    private String description;
    private BigDecimal cashIn;
    private BigDecimal cashOut;
    private BigDecimal closingBalance;
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getCashIn() {
        return cashIn;
    }
    public void setCashIn(BigDecimal cashIn) {
        this.cashIn = cashIn;
    }
    public BigDecimal getCashOut() {
        return cashOut;
    }
    public void setCashOut(BigDecimal cashOut) {
        this.cashOut = cashOut;
    }
    public BigDecimal getClosingBalance() {
        return closingBalance;
    }
    public void setClosingBalance(BigDecimal closingBalance) {
        this.closingBalance = closingBalance;
    }
    @Override
    public String toString() {
        return "Balance [date=" + date + ", description=" + description + ", cashIn=" + cashIn + ", cashOut=" + cashOut
                + ", closingBalance=" + closingBalance + "]";
    }


    


}




