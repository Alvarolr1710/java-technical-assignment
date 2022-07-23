package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;

    private final DiscountEnum discount;

    public WeighedProduct(final BigDecimal pricePerKilo, DiscountEnum discount) {
        this.pricePerKilo = pricePerKilo;
        this.discount = discount;
    }

    public DiscountEnum getDiscount() {
        return discount;
    }
    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
