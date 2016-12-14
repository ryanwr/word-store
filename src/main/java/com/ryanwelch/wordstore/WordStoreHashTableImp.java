package com.ryanwelch.wordstore;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
public class WordStoreHashTableImp implements WordStore {

    // Maximum load factor allowed before resize
    private static final float MAX_LOAD_FACTOR = 0.7f;
    // Smallest size of hash table
    private static final int MINIMUM_TABLE_SIZE = 8;

    private WordNode[] array;
    private int items;
    private int minSize;

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
                }

                nextNode = node.getNextNode();
                node.setNextNode(null);
                node = nextNode;
            }
        }

        array = newArray;
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
        // Dijb2
        int hash = hashDijb2(word.getBytes());

        // Requires table to be a PoT
        return hash & (size-1);
    }

    @Override
    public void add(String word) {
        resize(); // Resize if needed

        int position = getIndex(word, array.length);

        if(array[position] == null) {
            // Create start of linked list as this node
            array[position] = new WordNode(word);
            items++;
        } else {
            // Insert node at end of linked list, or increment count if word is in linked list
            WordNode node = array[position];

            while(node != null) {
                if(word.equals(node.getWord())) {
                    node.incrementCount();
                    break;
                } else if(!node.hasNextNode()) {
                    node.setNextNode(new WordNode(word));
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

        WordNode(String word, int count) {
            this.word = word;
            this.count = count;
        }

        WordNode(String word) {
            this(word, 1);
        }

        String getWord() {
            return word;
        }

        boolean hasNextNode() {
            return nextNode != null;
        }

        WordNode getNextNode() {
            return nextNode;
        }

        void setNextNode(WordNode node) {
            nextNode = node;
        }

        int getCount() {
            return count;
        }

        void incrementCount() {
            this.count++;
        }

        void decrementCount() {
            this.count--;
        }
    }

}
