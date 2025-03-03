package exercise;

import java.math.BigDecimal;

public class SimpleCalculator implements Calculator {

    @Override
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        return a.add(b);
    }
}
