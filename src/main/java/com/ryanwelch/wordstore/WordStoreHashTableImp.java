package com.ryanwelch.wordstore;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
public class WordStoreHashTableImp implements WordStore {

    private static final float MAX_LOAD_FACTOR = 0.7f; // Maximum load factor allowed before resize
    private static final int MINIMUM_TABLE_SIZE = 8; // Smallest size of hash table

    private static final int FNV1_32_INIT = 0x811c9dc5; // FNV Hash function constants
    private static final int FNV1_PRIME_32 = 16777619;

    private WordNode[] array;
    private int items;
    private int minSize;

    private int collisions = 0; // debug

    public WordStoreHashTableImp(int minSize) {
        this.minSize = getNextPowerOf2(minSize < MINIMUM_TABLE_SIZE ? MINIMUM_TABLE_SIZE : minSize);
        this.array = new WordNode[this.minSize];
        this.items = 0;
    }

    public WordStoreHashTableImp() {
        this(MINIMUM_TABLE_SIZE);
    }

    private int getNextPowerOf2(int n) {
        int res = 1;
        while (res < n) res = res << 1;
        return res;
    }

    /**
     * Resizes the array if necessary
     */
    private void resize() {
        float currentLoadFactor = items / array.length;
        float minLoadFactor = items / (array.length / 2);

        boolean shouldShrink = array.length > minSize && minLoadFactor < MAX_LOAD_FACTOR;
        boolean shouldGrow = currentLoadFactor > MAX_LOAD_FACTOR;

        if(!shouldGrow && !shouldShrink) return; // No need to resize

        WordNode[] newArray;
        if(shouldGrow) newArray = new WordNode[2 * array.length];
        else newArray = new WordNode[array.length / 2];

        collisions = 0; // debug

        // Move all nodes to their new positions, and relink collisions
        for(int i = 0; i < array.length; i++) {
            WordNode node = array[i];
            WordNode nextNode;

            while(node != null) {
                int newIndex = getIndex(node.getWord(), newArray.length);

                if(newArray[newIndex] == null) { // If empty place in array set the node
                    newArray[newIndex] = node;
                } else { // If there is a collision, add to the end of the linked list
                    WordNode listNode = newArray[newIndex];
                    while(listNode != null) {
                        if(!listNode.hasNextNode()) {
                            listNode.setNextNode(node);
                            break;
                        }
                        listNode = listNode.getNextNode();
                    }

                    collisions++; // debug
                }

                nextNode = node.getNextNode();
                node.setNextNode(null);
                node = nextNode;
            }
        }

        array = newArray;
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

    private int getIndex(String word, int size) {
        // Pure FNV
//        int hash = hashFNV(word.getBytes());

        // Dijb2
        int hash = hashDijb2(word.getBytes());

        return hash & (size-1); // Requires table to be a PoT
    }

    // debug
    public int getCollisionRate() {
        return (int) (((float) collisions/ (float) items) * 100.0f);
    }

    // debug
    public int getNumberOfCollisions() {
        return collisions;
    }

    // debug
    public int getNumberOfItems() {
        return items;
    }

    @Override
    public void add(String word) {
        resize(); // Resize if needed

        int position = getIndex(word, array.length);

        if(array[position] == null) { // Create start of linked list as this node
            array[position] = new WordNode(word);
            items++;
        } else { // Insert node at end of linked list, or increment count if word is in linked list
            WordNode node = array[position];

            while(node != null) {
                if(word.equals(node.getWord())) {
                    node.incrementCount();
                    break;
                } else if(!node.hasNextNode()) {
                    node.setNextNode(new WordNode(word));
                    collisions++; // debug
                    items++;
                    break;
                }

                node = node.getNextNode();
            }
        }
    }

    @Override
    public int count(String word) {
        int position = getIndex(word, array.length);

        if(array[position] != null) {
            WordNode node = array[position];

            while(node != null) {
                if(word.equals(node.getWord())) return node.getCount();

                node = node.getNextNode();
            }
        }

        return 0;
    }

    @Override
    public void remove(String word) {
        resize(); // Resize if needed

        int position = getIndex(word, array.length);

        if(array[position] != null) {
            WordNode parent = null;
            WordNode node = array[position];

            while(node != null) {
                if(word.equals(node.getWord())) {
                    if(node.getCount() > 1) {
                        node.decrementCount();
                        break;
                    } else {
                        if(parent != null) {
                            parent.setNextNode(node.getNextNode());
                            collisions--; // debug
                        } else {
                            array[position] = node.getNextNode();
                        }
                        items--;
                        break;
                    }
                }

                parent = node;
                node = node.getNextNode();
            }
        }
    }

    private class WordNode {

        private String word;
        private int count;
        private WordNode nextNode;

        public WordNode(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public WordNode(String word) {
            this(word, 1);
        }

        public String getWord() {
            return word;
        }

        public boolean hasNextNode() {
            return nextNode != null;
        }

        public WordNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(WordNode node) {
            nextNode = node;
        }

        public int getCount() {
            return count;
        }

        public void incrementCount() {
            this.count++;
        }

        public void decrementCount() {
            this.count--;
        }

    }

}
