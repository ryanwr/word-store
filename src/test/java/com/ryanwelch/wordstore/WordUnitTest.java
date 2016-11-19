package com.ryanwelch.wordstore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.runners.Parameterized.*;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
@RunWith(Parameterized.class)
public class WordUnitTest {

    private WordStoreFactory wordStoreFactory;
    private WordGen wordGen;
    private WordStore wordStore;

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        WordStoreFactory.WordStoreType[] values = WordStoreFactory.WordStoreType.values();
        Object[][] res = new Object[values.length][1];
        for(int i = 0; i < res.length; i++) res[i][0] = values[i];
        return Arrays.asList(res);
    }

    public WordUnitTest(final WordStoreFactory.WordStoreType type) {
        this.wordStoreFactory = new WordStoreFactory(type);
    }

    @Before
    public void setupTest() {
        wordGen = new WordGen(0);
        wordStore = wordStoreFactory.get();
    }

    @Test
    public void shouldExistWhenInserted() {
        wordStore.add("test");
        assertEquals(wordStore.count("test"), 1);
    }

    @Test
    public void shouldIncreaseCountWhenInserted() {
        wordStore.add("test");
        wordStore.add("test");
        assertEquals(wordStore.count("test"), 2);
    }

    @Test
    public void removeWords() {
        wordStore.add("test");
        wordStore.remove("test");
        assertEquals(wordStore.count("test"), 0);
    }

}
