package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Cards;
import java.math.BigDecimal;

public class CardsRepository {

    private final List<Cards> storage = new ArrayList<>();

    public CardsRepository() {
        initData();
    }

    private void initData() {
        Cards c1 = new Cards();
        c1.setCardNumber("CARD001");
        c1.setType("Credit");
        c1.setTotalLimit(BigDecimal.valueOf(2000));
        c1.setAmountUsed(BigDecimal.valueOf(500));
        c1.setAvailable(BigDecimal.valueOf(1500));
        storage.add(c1);
    }

    public Cards save(Cards card) {
        if (card == null || card.getCardNumber() == null) {
            throw new IllegalArgumentException("Card o cardNumber no puede ser null");
        }
        storage.removeIf(c -> c.getCardNumber().equals(card.getCardNumber()));
        storage.add(card);
        return card;
    }

    public Optional<Cards> findById(String cardNumber) {
        if (cardNumber == null) return Optional.empty();
        return storage.stream().filter(c -> cardNumber.equals(c.getCardNumber())).findFirst();
    }

    public List<Cards> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteById(String cardNumber) {
        return findById(cardNumber).map(storage::remove).orElse(false);
    }
}
