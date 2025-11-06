package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Loans;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LoansRepository {

    private final List<Loans> storage = new ArrayList<>();

    public LoansRepository() {
        initData();
    }

    private void initData() {
        Loans l1 = new Loans();
        l1.setDate(LocalDate.now().minusMonths(3));
        l1.setType("Personal");
        l1.setTotalLoan(BigDecimal.valueOf(10000));
        l1.setAmountPaid(BigDecimal.valueOf(3000));
        l1.setOutstandingAmt(BigDecimal.valueOf(7000));
        storage.add(l1);
    }

    public Loans save(Loans loan) {
        if (loan == null || loan.getDate() == null) {
            throw new IllegalArgumentException("Loan o fecha no puede ser null");
        }
        storage.removeIf(l -> l.getDate().equals(loan.getDate()));
        storage.add(loan);
        return loan;
    }

    public Optional<Loans> findById(String id) {
        if (id == null) return Optional.empty();
        return storage.stream().filter(l -> id.equals(l.getDate().toString())).findFirst();
    }

    public List<Loans> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteById(String id) {
        return findById(id).map(storage::remove).orElse(false);
    }
}
