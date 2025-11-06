package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Balance;

public class BalanceRepository {

    private final List<Balance> storage = new ArrayList<>();

    public BalanceRepository() {
        initData();
    }

    private void initData() {
        // Datos iniciales
        Balance b1 = new Balance();
        b1.setDate(java.time.LocalDate.now().minusDays(2));
        b1.setDescription("Depósito inicial");
        b1.setCashIn(java.math.BigDecimal.valueOf(5000));
        b1.setCashOut(java.math.BigDecimal.ZERO);
        b1.setClosingBalance(java.math.BigDecimal.valueOf(5000));
        storage.add(b1);
    }

    public Balance save(Balance balance) {
        if (balance == null || balance.getDate() == null) {
            throw new IllegalArgumentException("Balance o fecha no puede ser null");
        }
        storage.removeIf(b -> b.getDate().equals(balance.getDate()));
        storage.add(balance);
        return balance;
    }

    public Optional<Balance> findById(String id) {
        if (id == null) return Optional.empty();
        return storage.stream()
                .filter(b -> id.equals(b.getDate().toString()))
                .findFirst();
    }

    public List<Balance> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteById(String id) {
        return findById(id).map(storage::remove).orElse(false);
    }
}

