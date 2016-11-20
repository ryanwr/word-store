package com.ryanwelch.wordstore;

import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
public class WordTest {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        StringBuilder csvOutput = new StringBuilder();
        System.out.println("Enter seed: ");
        long seed = input.nextLong();

        WordStoreFactory.WordStoreType[] values = WordStoreFactory.WordStoreType.values();
        WordStoreFactory[] factories = new WordStoreFactory[WordStoreFactory.WordStoreType.values().length];
        csvOutput.append("size");
        for(int i = 0; i < values.length; i++) {
            factories[i] = new WordStoreFactory(values[i]);
            csvOutput.append(", ");
            csvOutput.append(values[i].toString());
        }
        csvOutput.append("\n");

        int[] sizes = new int[]{1000, 100000};
        long time;

        for(int size : sizes) {
            System.out.println("\n==================================\nTime taken for size of " + size + "\n==================================");

            System.out.println("\nAdd words to empty store");
            csvOutput.append(size);
            for(WordStoreFactory factory : factories) {
                time = addWordsToEmpty(factory.get(size), new WordGen(seed), size) / 1000000;
                System.out.println(String.format("%1$-20s", factory.getType().toString()) + time + "ms");
                csvOutput.append(", ").append(time);
            }
            csvOutput.append("\n");

            System.out.println("\nAdd words non empty store");
            for(WordStoreFactory factory : factories) {
                time = addWords(factory.get(size), new WordGen(seed), size, 10000) / 1000000;
                System.out.println(String.format("%1$-20s", factory.getType().toString()) + time + "ms");
            }

            System.out.println("\nCount words in store");
            for(WordStoreFactory factory : factories) {
                time = countWords(factory.get(size), new WordGen(seed), size, 10000) / 1000000;
                System.out.println(String.format("%1$-20s", factory.getType().toString()) + time + "ms");
            }

            System.out.println("\nRemove words from store");
            for(WordStoreFactory factory : factories) {
                time = removeWords(factory.get(size), new WordGen(seed), size, 10000) / 1000000;
                System.out.println(String.format("%1$-20s", factory.getType().toString()) + time + "ms");
            }
        }

        FileWriter writer = new FileWriter("test.csv");
        writer.write(csvOutput.toString());
        writer.close();

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

//        if(wordStore instanceof WordStoreHashTableImp) {
//            WordStoreHashTableImp wordStoreHT = (WordStoreHashTableImp) wordStore;
//            System.out.println("Collisions: " + wordStoreHT.getNumberOfCollisions() + " - Items: " + wordStoreHT.getNumberOfItems());
//            System.out.println("Collision rate: " + wordStoreHT.getCollisionRate() + "%");
//        }

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
