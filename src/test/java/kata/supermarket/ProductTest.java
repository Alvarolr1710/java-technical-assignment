package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product(price, DiscountEnum.NONE, 1).getItem().price());
    }

    @Test
    void multipleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price.multiply(new BigDecimal("3")),
                new Product(price, DiscountEnum.NONE, 3).getItem().price());
    }

    @Test
    void multipleItemHasExpectedDiscountFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(new BigDecimal("2.49"),
                new Product(price, DiscountEnum.BUY_ONE_GET_ONE, 3).getItem().discountToApply());
    }
}