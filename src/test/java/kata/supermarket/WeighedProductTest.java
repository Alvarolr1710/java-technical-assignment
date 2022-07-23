package kata.supermarket;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo,
                                                    String weightInKilos,
                                                    String expectedPrice,
                                                    DiscountEnum discountEnum) {
        final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal(pricePerKilo), discountEnum);
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "100.00", DiscountEnum.NONE),
                Arguments.of("100.00", "0.33333", "33.33", DiscountEnum.NONE),
                Arguments.of("100.00", "0.33335", "33.34", DiscountEnum.NONE),
                Arguments.of("100.00", "0", "0.00", DiscountEnum.NONE)
        );
    }

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedDiscountForUnitPrice(String pricePerKilo,
                                                               String weightInKilos,
                                                               String expectedDiscount,
                                                               DiscountEnum discountEnum) {
        final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal(pricePerKilo), discountEnum);
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedDiscount), weighedItem.discountToApply());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedDiscountForUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "0", DiscountEnum.NONE),
                Arguments.of("100.00", "1.00", "50.00", DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE),
                Arguments.of("100.00", "1.00", "0", DiscountEnum.BUY_ONE_GET_ONE),
                Arguments.of("100.00", "1.00", "0", DiscountEnum.BUY_THREE_FOR_TWO),
                Arguments.of("100.00", "1.00", "0", DiscountEnum.BUY_TWO_FOR_POUND_1),
                Arguments.of("75.00", "1.50", "37.50", DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE),
                Arguments.of("175.00", "4.50", "350.00", DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE),
                Arguments.of("120.00", "0.99", "0", DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE),
                Arguments.of("120.00", "1.99", "60.00", DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE),
                Arguments.of("111.11", "1.00", "55.55", DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE),
                Arguments.of("100.00", "0", "0", DiscountEnum.NONE)
        );
    }

}