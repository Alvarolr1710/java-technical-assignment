package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                multipleItemOfTheSamePriced(),
                multipleItemOfTheSamePricedWithDiscount(),
                aSingleItemPricedByWeight(),
                aSingleItemPricedByWeightWithDiscount(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25",
                Collections.singleton(americanSweetsByWeight(new BigDecimal(".25"), DiscountEnum.NONE)));
    }

    private static Arguments aSingleItemPricedByWeightWithDiscount() {
        return Arguments.of("a single weighed item", "3.75",
                Collections.singleton(
                        americanSweetsByWeight(new BigDecimal("1.25"), DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE)));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(
                        americanSweetsByWeight(new BigDecimal(".25"), DiscountEnum.NONE),
                        twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), pintsOfMilk(1, DiscountEnum.NONE)));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49",
                Collections.singleton(pintsOfMilk(1, DiscountEnum.NONE)));
    }

    private static Arguments multipleItemOfTheSamePriced() {
        return Arguments.of("multiple item priced per unit", "1.47",
                Collections.singleton(pintsOfMilk(3, DiscountEnum.NONE)));
    }

    private static Arguments multipleItemOfTheSamePricedWithDiscount() {
        return Arguments.of("multiple item priced per unit", "0.98",
                Collections.singleton(pintsOfMilk(3, DiscountEnum.BUY_ONE_GET_ONE)));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item pintsOfMilk(int unitCount, DiscountEnum discountEnum) {
        return new Product(new BigDecimal("0.49"), discountEnum, unitCount).getItem();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55"), DiscountEnum.NONE, 1).getItem();
    }

    private static WeighedProduct aKiloOfAmericanSweets(DiscountEnum discountEnum) {
        return new WeighedProduct(new BigDecimal("4.99"), discountEnum);
    }

    private static Item americanSweetsByWeight(BigDecimal weight, DiscountEnum discountEnum) {
        return aKiloOfAmericanSweets(discountEnum).weighing(weight);
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"), DiscountEnum.NONE);
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}