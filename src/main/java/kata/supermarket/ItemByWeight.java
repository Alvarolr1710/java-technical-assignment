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

    public BigDecimal discountToApply() {
        DiscountEnum discountEnum = product.getDiscount();
        BigDecimal discount = BigDecimal.ZERO;
        if (discountEnum.equals(DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE)) {
            if (weightInKilos.compareTo(BigDecimal.ONE) >= 0) {
                discount = product.pricePerKilo()
                        .multiply(weightInKilos.setScale(0, RoundingMode.DOWN))
                        .divide(new BigDecimal("2"), RoundingMode.DOWN)
                        .setScale(2, HALF_UP);
            }
        }
        return discount;
    }
}
