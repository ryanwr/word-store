package com.ryanwelch.wordstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
@RunWith(Parameterized.class)
public class CollisionTest {

    private final int number;
    private final String[] testWords;

    private static final int FNV1_32_INIT = 0x811c9dc5;
    private static final int FNV1_PRIME_32 = 16777619;

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

    /**
     * FNV hash
     */
    private int hashFNV(byte[] data) {
        int hash = FNV1_32_INIT; // Initial value specified by FNV1
        for (int i = 0; i < data.length; i++) { // Loop through every byte in the integer
            hash ^= (data[i] & 0xff);
            hash *= FNV1_PRIME_32; // Prime multiplication constant specified by FNV1
        }
        return hash;
    }

    /**
     * Dijb2 hash
     */
    private int hashDijb2(byte[] data) {
        int hash = 5381;

        for(byte b : data) {
            hash = ((hash << 5) + hash) + b; /* hash * 33 + b */
        }

        return hash;
    }

    @Test
    public void testDefault() {
        int arraySize = getNextPowerOf2(number);
        List<Integer> list = new ArrayList<>();
        int collisions = 0;

        for(int i = 0; i < number; i++) {

            // Hash function to test
            int hash = 21;
            for(int j = 0; j < testWords[i].length(); j++) {
                hash = (hash * 7) + testWords[i].charAt(j);
            }
            hash = hash & (arraySize - 1);

            if(list.contains(hash)) collisions++;
            else list.add(hash);
        }
        System.out.println("Default @ " + number + ": " + collisions);
    }

    @Test
    public void testFNV() {
        int arraySize = getNextPowerOf2(number);
        List<Integer> list = new ArrayList<>();
        int collisions = 0;

        for(int i = 0; i < number; i++) {

            // Hash function to test
            byte[] data = new byte[testWords[i].length()];
            testWords[i].getBytes(0, testWords[i].length(), data, 0);
            int hash = hashFNV(data);
            hash = hash & (arraySize - 1);

            if(list.contains(hash)) collisions++;
            else list.add(hash);
        }
        System.out.println("FNV @ " + number + ": " + collisions);
    }

    @Test
    public void testDIJB2() {
        int arraySize = getNextPowerOf2(number);
        List<Integer> list = new ArrayList<>();
        int collisions = 0;

        for(int i = 0; i < number; i++) {

            // Hash function to test
            byte[] data = new byte[testWords[i].length()];
            testWords[i].getBytes(0, testWords[i].length(), data, 0);
            int hash = hashDijb2(data);
            hash = hash & (arraySize - 1);

            if(list.contains(hash)) collisions++;
            else list.add(hash);
        }
        System.out.println("DIJB2 @ " + number + ": " + collisions);
    }

}
