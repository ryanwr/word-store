package com.ryanwelch.wordstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
@RunWith(Parameterized.class)
public class CollisionTest {

    private final int number;
    private final String[] testWords;

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1000},
                {100000},
                {1000000}
        });
    }

    public CollisionTest(final int number) {
        this.number = number;
        this.testWords = new String[number];
        WordGen wordGen = new WordGen(0);
        for (int i = 0; i < number; i++) testWords[i] = wordGen.make();
    }

    // PRIME HELPER
    private boolean isPrime(int n) {
        if (n == 2 || n == 3 || n % 2 == 0 || n % 3 == 0) return false;

        int divisor = 6;
        while (divisor * divisor - 2 * divisor + 1 <= n) {
            if (n % (divisor - 1) == 0 || n % (divisor + 1) == 0) return false;
            divisor += 6;
        }

        return true;
    }

    private int getNextPrime(int n) {
        while(!isPrime(++n)) {}
        return n;
    }

    // POWER OF TWO HELPER
    private int getNextPowerOf2(int n) {
        n--;
        n |= n >> 1;   // Divide by 2^k for consecutive doublings of k up to 32,
        n |= n >> 2;   // and then or the results.
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n++;            // The result is a number of 1 bits equal to the number
        // of bits in the original number, plus 1. That's the
        // next highest power of 2.
        return n;
    }



    @Test
    public void defaultHashCodeAndPrime() {
        int arraySize = getNextPrime(number);
        List<Integer> list = new ArrayList<>();
        int collisions = 0;

        for(int i = 0; i < number; i++) {

            // Hash function to test
            int hash = testWords[i].hashCode();
            hash = (hash % arraySize + arraySize) % arraySize;

            if(list.contains(hash)) collisions++;
            else list.add(hash);
        }
    }

    @Test
    public void defaultHashCodeAndPowerOfTwo() {
        int arraySize = getNextPowerOf2(number);
        List<Integer> list = new ArrayList<>();
        int collisions = 0;

        for(int i = 0; i < number; i++) {

            // Hash function to test
            int hash = testWords[i].hashCode();
            hash = hash & (arraySize - 1);

            if(list.contains(hash)) collisions++;
            else list.add(hash);
        }
    }

}
