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

    private String[] array;
    private int length = 0;
    private int actualLength;

    public WordStoreArrayImp(int initialArraySize) {
        this.actualLength = initialArraySize;
        this.array = new String[initialArraySize];
    }

    public WordStoreArrayImp() {
        this(20);
    }

    private void growArray() {
        if(length < actualLength) return; // No need to grow, space still left

        actualLength *= 2;
        String[] newArray = new String[actualLength];
        for(int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public void add(String word) {
        if(length >= actualLength) growArray();

        array[length++] = word;
    }

    @Override
    public int count(String word) {
        int count = 0;
        for(int i = 0; i < length; i++) if(array[i].equals(word)) count++;
        return count;
    }

    @Override
    public void remove(String word) {
        int index = -1;
        for(int i = 0; i < length; i++) {
            if(array[i].equals(word)) {
                index = i;
                break;
            }
        }
        if(index == -1) return;

        length--;
        for(int i = index; i < length; i++) {
            array[i] = array[i+1];
        }
    }
}
