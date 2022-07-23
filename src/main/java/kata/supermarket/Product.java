package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;

    private final DiscountEnum discount;

    public Product(final BigDecimal pricePerUnit, final DiscountEnum discount) {
        this.pricePerUnit = pricePerUnit;
        this.discount = discount;
    }

    public DiscountEnum getDiscount() {
        return discount;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }


}
