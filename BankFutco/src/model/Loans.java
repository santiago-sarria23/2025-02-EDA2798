package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loans {

    private LocalDate date;
    private String type; //Home - Vehicle - University - Personal
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmt;
}
