package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_UP;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, HALF_UP);
    }

    /* Weighted items are only affected by discounts that takes in consideration weight and not units
    for our case is only BUY_ONE_KILO_FOR_HALF_PRICE */
    public BigDecimal discountToApply() {

        if (product.getDiscount().equals(DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE) &&
                weightInKilos.compareTo(BigDecimal.ONE) >= 0) {

            return product.pricePerKilo()
                    .multiply(weightInKilos.setScale(0, RoundingMode.DOWN))
                    .divide(new BigDecimal("2"), RoundingMode.DOWN)
                    .setScale(2, HALF_UP);
        }

        return BigDecimal.ZERO;
    }
}
