package com.ryanwelch.wordstore;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
public class WordStoreFactory {

    public enum WordStoreType {
        ARRAY,
        BINARY_TREE,
        RED_BLACK_TREE,
        HASH_TABLE
    }

    private WordStoreType type;

    public WordStoreFactory(WordStoreType type) {
        this.type = type;
    }

    public WordStore get(int n) {
        switch (type) {
            case BINARY_TREE:
                return new WordStoreBinaryTreeImp();
            case HASH_TABLE:
                return new WordStoreHashTableImp(n);
            default:
                return new WordStoreArrayImp(n);
        }
    }

    public WordStore get() {
        switch (type) {
            case BINARY_TREE:
                return new WordStoreBinaryTreeImp();
            case HASH_TABLE:
                return new WordStoreHashTableImp();
            default:
                return new WordStoreArrayImp();
        }
    }
}
