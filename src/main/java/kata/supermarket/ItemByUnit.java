package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ItemByUnit implements Item {

    private final Product product;

    ItemByUnit(final Product product) {
        this.product = product;
    }

    public BigDecimal price() {
        return product.pricePerUnit().multiply(new BigDecimal(product.getUnitCount()));
    }

    public BigDecimal discountToApply() {
        switch (product.getDiscount()) {
            case BUY_ONE_GET_ONE:
                return calculateBuyOneGetOneDiscount(product);
            case BUY_ONE_KILO_FOR_HALF_PRICE:
            case NONE:
            case BUY_TWO_FOR_POUND_1:
            case BUY_THREE_FOR_TWO:
            default:
                return BigDecimal.ZERO;
        }
    }

    private BigDecimal calculateBuyOneGetOneDiscount(Product product) {
        return product.pricePerUnit()
                .multiply(new BigDecimal(product.getUnitCount() / 2))
                .setScale(2, RoundingMode.DOWN);
    }
}
