package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCalculatorTest {

    @DisplayName("Calculate the discount for a list of items...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedDiscount, Iterable<Item> items) {
        List<Item> itemsList = new ArrayList<>();
        items.forEach(itemsList::add);
        final DiscountCalculator discountCalculator = new DiscountCalculator(itemsList);
        assertEquals(new BigDecimal(expectedDiscount), discountCalculator.calculateDiscount());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                aSingleItemPricedByWeightWithDiscount(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "0.00",
                Collections.singleton(americanSweetsByWeight(new BigDecimal(".25"), DiscountEnum.NONE)));
    }

    private static Arguments aSingleItemPricedByWeightWithDiscount() {
        return Arguments.of("a single weighed item", "2.49",
                Collections.singleton(
                        americanSweetsByWeight(new BigDecimal("1.25"), DiscountEnum.BUY_ONE_KILO_FOR_HALF_PRICE)));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "0.00",
                Arrays.asList(
                        americanSweetsByWeight(new BigDecimal(".25"), DiscountEnum.NONE),
                        twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "0.00",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.00", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49"), DiscountEnum.NONE).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55"), DiscountEnum.NONE).oneOf();
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