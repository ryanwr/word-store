package com.ryanwelch.wordstore;

import java.util.Scanner;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
public class WordTest {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter seed: ");
        long seed = input.nextLong();
        WordStoreFactory wordStoreFactory = new WordStoreFactory(WordStoreFactory.WordStoreType.HASH_TABLE);


        int[] sizes = new int[]{1000, 100000, 1000000, 10000000};

        for(int size : sizes) {
            System.out.println("\nTime taken for size of " + size);
            long time;

            time = addWordsToEmpty(wordStoreFactory.get(), new WordGen(seed), size) / 1000000;
            System.out.println("Add words to empty store - " + time + "ms");
            time = addWords(wordStoreFactory.get(), new WordGen(seed), size, 10000) / 1000000;
            System.out.println("Add words non empty store - " + time + "ms");
            time = countWords(wordStoreFactory.get(), new WordGen(seed), size, 10000) / 1000000;
            System.out.println("Count words in store - " + time + "ms");
            time = removeWords(wordStoreFactory.get(), new WordGen(seed), size, 10000) / 1000000;
            System.out.println("Remove words from store - " + time + "ms");
        }

    }

    /**
     * Inserting into empty store
     */
    public static long addWordsToEmpty(WordStore wordStore, WordGen wordGen, int n) {
        String[] testWords = new String[n];
        for (int i = 0; i < n; i++) testWords[i] = wordGen.make();

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) wordStore.add(testWords[i]);
        long end = System.nanoTime();

        if(wordStore instanceof WordStoreHashTableImp) {
            WordStoreHashTableImp wordStoreHT = (WordStoreHashTableImp) wordStore;
            System.out.println("Collision rate: " + wordStoreHT.getCollisionRate() + "%");
            System.out.println("Collision number: " + wordStoreHT.getNumberOfCollisions());
            System.out.println("Items number: " + wordStoreHT.getNumberOfItems());
        }

        return end-start;
    }

    /**
     * Counting word in store
     */
    public static long countWords(WordStore wordStore, WordGen wordGen, int n, int initialN) {
        for (int i = 0; i < initialN; i++) wordStore.add(wordGen.make());

        String[] testWords = new String[n];
        for (int i = 0; i < n; i++) testWords[i] = wordGen.make();

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) wordStore.count(testWords[i]);
        long end = System.nanoTime();

        return end-start;
    }

    /**
     * Insert words in store
     */
    public static long addWords(WordStore wordStore, WordGen wordGen, int n, int initialN) {
        for (int i = 0; i < initialN; i++) wordStore.add(wordGen.make());

        String[] testWords = new String[n];
        for (int i = 0; i < n; i++) testWords[i] = wordGen.make();

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) wordStore.add(testWords[i]);
        long end = System.nanoTime();

        return end-start;
    }

    /**
     * Remove words in store
     * @return
     */
    public static long removeWords(WordStore wordStore, WordGen wordGen, int n, int initialN) {
        for (int i = 0; i < initialN; i++) wordStore.add(wordGen.make());

        String[] testWords = new String[n];
        for (int i = 0; i < n; i++) testWords[i] = wordGen.make();

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) wordStore.remove(testWords[i]);
        long end = System.nanoTime();

        return end-start;
    }

}
