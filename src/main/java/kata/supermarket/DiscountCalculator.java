package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

public class DiscountCalculator {

    private final List<Item> items;

    public DiscountCalculator(List<Item> items) {
        this.items = items;
    }

    public BigDecimal calculateDiscount() {
        return items.stream().map(Item::discountToApply)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
