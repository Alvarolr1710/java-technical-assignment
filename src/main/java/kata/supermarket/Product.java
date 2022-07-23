package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;

    private final DiscountEnum discount;

    private final int unitCount;

    public Product(final BigDecimal pricePerUnit, final DiscountEnum discount, int unitCount) {
        this.pricePerUnit = pricePerUnit;
        this.discount = discount;
        this.unitCount = unitCount;
    }

    public DiscountEnum getDiscount() {
        return discount;
    }

    public int getUnitCount() {
        return unitCount;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item getItem() {
        return new ItemByUnit(this);
    }


}
