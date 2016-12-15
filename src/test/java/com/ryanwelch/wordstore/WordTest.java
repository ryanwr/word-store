package com.ryanwelch.wordstore;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
public class WordTest {

    private static int NUM_REPEATS = 3;
    private WordStoreFactory[] wordStoreFactories;

    public WordTest(WordStoreFactory.WordStoreType[] values) {
        wordStoreFactories = new WordStoreFactory[values.length];
        for(int i = 0; i < values.length; i++) {
            wordStoreFactories[i] = new WordStoreFactory(values[i]);
        }
    }

    private void createColumnNames(StringBuilder sb) {
        sb.append("size");
        for(WordStoreFactory factory : wordStoreFactories) {
            sb.append(", ").append(factory.getType().toString());
        }
        sb.append("\n");
    }

    private void writeFile(StringBuilder sb, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(sb.toString());
        writer.close();
    }

    public void start() throws IOException {
        StringBuilder addWordsCsvOutput = new StringBuilder();
        StringBuilder countWordsCsvOutput = new StringBuilder();
        StringBuilder removeWordsCsvOutput = new StringBuilder();
        createColumnNames(addWordsCsvOutput);
        createColumnNames(countWordsCsvOutput);
        createColumnNames(removeWordsCsvOutput);

        for (int size = 10000; size <= 10000000; size *= 10) {
            Test[] tests = new Test[wordStoreFactories.length];
            for (int i = 0; i < wordStoreFactories.length; i++) {
                if(size <= wordStoreFactories[i].getMaxSize() || wordStoreFactories[i].getMaxSize() == -1)
                    tests[i] = new Test(wordStoreFactories[i], size);
            }

            System.out.println("\n==================================\nTime taken for size of " + size + "\n==================================");
            for(int i = 0; i < NUM_REPEATS; i++) {
                System.out.println("\nAdd words to empty store");
                addWordsCsvOutput.append(size);
                for (Test test : tests) {
                    if(test != null) test.addWords(addWordsCsvOutput);
                    else addWordsCsvOutput.append(", ");
                }
                addWordsCsvOutput.append("\n");

                System.out.println("\nCount words in store");
                countWordsCsvOutput.append(size);
                for (Test test : tests) {
                    if(test != null) test.countWords(countWordsCsvOutput);
                    else countWordsCsvOutput.append(", ");
                }
                countWordsCsvOutput.append("\n");

                System.out.println("\nRemove words from store");
                removeWordsCsvOutput.append(size);
                for (Test test : tests) {
                    if(test != null) test.removeWords(removeWordsCsvOutput);
                    else removeWordsCsvOutput.append(", ");
                }
                removeWordsCsvOutput.append("\n");
            }
        }

        writeFile(addWordsCsvOutput, "docs/addwords.csv");
        writeFile(countWordsCsvOutput, "docs/countwords.csv");
        writeFile(removeWordsCsvOutput, "docs/removewords.csv");
    }

    public class Test {

        private static final int WORDS_PER_TEST = 1000;
        private static final int SEED = 0;

        private String wordStoreType;
        private WordStore wordStore;
        private WordGen wordGen;

        public Test(WordStoreFactory wordStoreFactory, int n) {
            this.wordStoreType = wordStoreFactory.getType().toString();
            this.wordStore = wordStoreFactory.get(n);
            this.wordGen = new WordGen(SEED);

            // Initial words
            for (int i = 0; i < n; i++) wordStore.add(wordGen.make());
        }

        /**
         * Inserting words into store
         */
        public void addWords(StringBuilder output) {
            String[] testWords = new String[WORDS_PER_TEST];
            for (int i = 0; i < WORDS_PER_TEST; i++) testWords[i] = wordGen.make();

            long start = System.nanoTime();
            for (int i = 0; i < WORDS_PER_TEST; i++) wordStore.add(testWords[i]);
            long end = System.nanoTime();

            long average = end-start;

            System.out.println(String.format("%1$-20s", wordStoreType) + average + "ns");
            output.append(", ").append(average);
        }

        /**
         * Counting word in store
         */
        public void countWords(StringBuilder output) {
            String[] testWords = new String[WORDS_PER_TEST];
            for (int i = 0; i < WORDS_PER_TEST; i++) testWords[i] = wordGen.make();

            long start = System.nanoTime();
            for (int i = 0; i < WORDS_PER_TEST; i++) wordStore.count(testWords[i]);
            long end = System.nanoTime();

            long average = end-start;

            System.out.println(String.format("%1$-20s", wordStoreType) + average + "ns");
            output.append(", ").append(average);
        }

        /**
         * Remove words in store
         * @return
         */
        public void removeWords(StringBuilder output) {
            String[] testWords = new String[WORDS_PER_TEST];
            for (int i = 0; i < WORDS_PER_TEST; i++) testWords[i] = wordGen.make();

            long start = System.nanoTime();
            for (int i = 0; i < WORDS_PER_TEST; i++) wordStore.remove(testWords[i]);
            long end = System.nanoTime();

            long average = end-start;

            System.out.println(String.format("%1$-20s", wordStoreType) + average + "ns");
            output.append(", ").append(average);
        }
    }

    public static void main(String[] args) throws IOException {
        (new WordTest(WordStoreFactory.WordStoreType.values())).start();
    }

}
