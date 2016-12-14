package com.ryanwelch.wordstore;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
public class WordTest {

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
        StringBuilder addWordsNonEmptyCsvOutput = new StringBuilder();
        StringBuilder countWordsCsvOutput = new StringBuilder();
        StringBuilder removeWordsCsvOutput = new StringBuilder();
        createColumnNames(addWordsCsvOutput);
        createColumnNames(addWordsNonEmptyCsvOutput);
        createColumnNames(countWordsCsvOutput);
        createColumnNames(removeWordsCsvOutput);

        for (int size = 1; size <= 100000; size *= 10) {
            Test[] tests = new Test[wordStoreFactories.length];
            for (int i = 0; i < wordStoreFactories.length; i++) tests[i] = new Test(wordStoreFactories[i], 3, size);

            System.out.println("\n==================================\nTime taken for size of " + size + "\n==================================");
            System.out.println("\nAdd words to empty store");
            addWordsCsvOutput.append(size);
            for(Test test : tests) test.addWords(addWordsCsvOutput);
            addWordsCsvOutput.append("\n");

            System.out.println("\nAdd words non empty store");
            addWordsNonEmptyCsvOutput.append(size);
            for(Test test : tests) test.addWords(addWordsNonEmptyCsvOutput);
            addWordsNonEmptyCsvOutput.append("\n");

            System.out.println("\nCount words in store");
            countWordsCsvOutput.append(size);
            for(Test test : tests) test.countWords(countWordsCsvOutput);
            countWordsCsvOutput.append("\n");

            System.out.println("\nRemove words from store");
            removeWordsCsvOutput.append(size);
            for(Test test : tests) test.removeWords(removeWordsCsvOutput);
            removeWordsCsvOutput.append("\n");
        }

        writeFile(addWordsCsvOutput, "docs/addwords.csv");
        writeFile(addWordsNonEmptyCsvOutput, "docs/addwordsnonempty.csv");
        writeFile(countWordsCsvOutput, "docs/countwords.csv");
        writeFile(removeWordsCsvOutput, "docs/removewords.csv");
    }

    public class Test {

        private String wordStoreType;
        private WordStore wordStore;
        private int numRepeats;
        private int n;

        public Test(WordStoreFactory wordStoreFactory, int numRepeats, int n) {
            this.wordStoreType = wordStoreFactory.getType().toString();
            this.wordStore = wordStoreFactory.get(n);
            this.numRepeats = numRepeats;
            this.n = n;
        }

        /**
         * Inserting words into store
         */
        private long addWords(WordGen wordGen) {
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

        public void addWords(StringBuilder output) {
            long average = 0;
            for(int i = 0; i < numRepeats; i++) average += addWords(new WordGen(i));
            average /= numRepeats;

            System.out.println(String.format("%1$-20s", wordStoreType) + average + "ns");
            output.append(", ").append(average);
        }

        /**
         * Counting word in store
         */
        private long countWords(WordGen wordGen) {
            String[] testWords = new String[n];
            for (int i = 0; i < n; i++) testWords[i] = wordGen.make();

            long start = System.nanoTime();
            for (int i = 0; i < n; i++) wordStore.count(testWords[i]);
            long end = System.nanoTime();

            return end-start;
        }

        public void countWords(StringBuilder output) {
            long average = 0;
            for(int i = 0; i < numRepeats; i++) average += countWords(new WordGen(i));
            average /= numRepeats;

            System.out.println(String.format("%1$-20s", wordStoreType) + average + "ns");
            output.append(", ").append(average);
        }

        /**
         * Remove words in store
         * @return
         */
        private long removeWords(WordGen wordGen) {
            String[] testWords = new String[n];
            for (int i = 0; i < n; i++) testWords[i] = wordGen.make();

            long start = System.nanoTime();
            for (int i = 0; i < n; i++) wordStore.remove(testWords[i]);
            long end = System.nanoTime();

            return end-start;
        }

        public void removeWords(StringBuilder output) {
            long average = 0;
            for(int i = 0; i < numRepeats; i++) average += removeWords(new WordGen(i));
            average /= numRepeats;

            System.out.println(String.format("%1$-20s", wordStoreType) + average + "ns");
            output.append(", ").append(average);
        }
    }

    public static void main(String[] args) throws IOException {
        (new WordTest(WordStoreFactory.WordStoreType.values())).start();
    }

}
