package exercise;

import net.jqwik.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    private final Calculator calculator = new SimpleCalculator();

    @Provide
    Arbitrary<BigDecimal> bigDecimals() {
        return Arbitraries.bigDecimals()
                .between(new BigDecimal(-100_0000), new BigDecimal(100_0000))
                .ofScale(2);
    }

    @Group
    class AdditionTests {

        @Property
        void additionIsCommutative(
                @ForAll("bigDecimals") BigDecimal a,
                @ForAll("bigDecimals") BigDecimal b
        ) {
            assertEquals(calculator.add(a, b), calculator.add(b, a));
        }

        @Property
        void additionIsAssociative(
                @ForAll("bigDecimals") BigDecimal a,
                @ForAll("bigDecimals") BigDecimal b,
                @ForAll("bigDecimals") BigDecimal c
        ) {
            final BigDecimal result1 = calculator.add(a, calculator.add(b, c));
            final BigDecimal result2 = calculator.add(calculator.add(a, b), c);
            assertEquals(result1, result2);
        }

        @Property
        void addZeroIdentity(
                @ForAll("bigDecimals") BigDecimal a
        ) {
            assertEquals(a, calculator.add(a, BigDecimal.ZERO));
        }
    }
}