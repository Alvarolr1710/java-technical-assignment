# Notes

Please add here any notes, assumptions and design decisions that might help us understand your thought process.

Assumptions.
    * The application will count of the items of the same and group them together
    * The products will not have more than one discount
    * The discounts that can be applied are the ones in the discountEnum class, more can be added.

Pending:
    * Implement the rest of the discount types and improve testing sweet to verify all the edge cases
    * Refactor TotalCalculator and DiscountCalculator as they are similar classes that can be taken out of the basket class
    * Refactor the product by unit to take a product type and be added independently, this will bring some considerations into the way that the discount is being calculated.
    * Prepare a strategy to handle multiple weighted products of the same type that can have a same discount applied, combine weight to verify correct discount is applied.

