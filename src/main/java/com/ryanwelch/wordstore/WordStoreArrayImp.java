package com.ryanwelch.wordstore;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 *
 * Simple implementation of WordStore using a sorted "array and count" data
 * structure with fast lookups using binary search. The problem with binary
 * search backed by an array is that to insert an item into the array it must
 * be inserted into a sorted position and in worst case requires moving all other
 * elements in the array.
 */
public class WordStoreArrayImp implements WordStore {

    private Word[] array;
    private int length = 0;
    private int actualLength;

    public WordStoreArrayImp(int initialArraySize) {
        this.actualLength = initialArraySize;
        this.array = new Word[initialArraySize];
    }

    public WordStoreArrayImp() {
        this(20);
    }

    private void growArray() {
        if(length < actualLength) return; // No need to grow, space still left

        actualLength *= 2;
        Word[] newArray = new Word[actualLength];
        for(int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private int findWord(String word) {
        for(int i = 0; i < length; i++) {
            if(array[i].getWord().equals(word)) return i;
        }
        return -1;
    }

    @Override
    public void add(String word) {
        int position = findWord(word);
        if(position != -1) {
            array[position].incrementCount();
            return;
        }

        if(length >= actualLength) growArray();

        // TODO: Insert sorted, for binary search
        array[length] = new Word(word);
        length++;
    }

    @Override
    public int count(String word) {
        int position = findWord(word);
        return position == -1 ? 0 : array[position].getCount();
    }

    /**
     * Remove a word completely from an array and preserve sorted order
     * @param index
     */
    private void removeWord(int index) {
        length--;
        for(int i = index; i < length; i++) {
            array[i] = array[i+1];
        }
    }

    @Override
    public void remove(String word) {
        int index = findWord(word);
        if(index == -1) return;

        // If word exists and has more than one count, simply decrease the count
        if(array[index].getCount() > 1) {
            array[index].decrementCount();
        } else {
            // If it is the last word, remove completely from array
            removeWord(index);
        }
    }

    private class Word {

        private String word;
        private int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public Word(String word) {
            this(word, 1);
        }

        public String getWord() {
            return word;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void incrementCount() {
            this.count++;
        }

        public void decrementCount() {
            this.count--;
        }

    }
}
