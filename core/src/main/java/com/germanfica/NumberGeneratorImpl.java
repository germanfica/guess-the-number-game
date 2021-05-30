package com.germanfica;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.ranges.Range;

import java.util.Random;

@Getter
@Component
public class NumberGeneratorImpl implements NumberGenerator {
    // == fields ==
    private final int maxNumber;
    private final int minNumber;

    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    // == constructors ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == public methods ==
    @Override
    public int next() {
        // example: min=5 max=20, 20-5=15 -> min=0 max=15, range(0,15)+min -> range(5,20)
        int bound = maxNumber - minNumber;
        return random.nextInt(bound) + minNumber;
    }
}
