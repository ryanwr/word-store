package com.ryanwelch.wordstore;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
public class WordStoreHashTableImp implements WordStore {

    private static final float MAX_LOAD_FACTOR = 3/4;

    private WordNode[] array;
    private WordNode tempParent;
    private int items;

    private int collisions = 0;

    public WordStoreHashTableImp(int initialArraySize) {
        this.array = new WordNode[getNextPowerOf2(initialArraySize)];
        this.items = 0;
        this.tempParent = new WordNode();
    }

    public WordStoreHashTableImp() {
        this(8);
    }

    // Taken from http://stackoverflow.com/a/1322548
    private int getNextPowerOf2(int n) {
        n--;
        n |= n >> 1;   // Divide by 2^k for consecutive doublings of k up to 32,
        n |= n >> 2;   // and then or the results.
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n++;            // The result is a number of 1 bits equal to the number
                        // of bits in the original number, plus 1. That's the
                        // next highest power of 2.
        return n;
    }

    /**
     * Resizes the array if necessary
     */
    private void resize() {
        float loadFactor = items / array.length;
        if(loadFactor <= MAX_LOAD_FACTOR) return; // No need to resize

        WordNode[] newArray = new WordNode[2 * array.length];

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

    private int getIndex(String word, int size) {
        //return word.hashCode() % size; // Simple modulus
        return word.hashCode() & (size-1); // Requires table to be a PoT
    }

    public int getCollisionRate() {
        return (int) (((float) collisions/ (float) items) * 100.0f);
    }

    public int getNumberOfCollisions() {
        return collisions;
    }

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
                    collisions++;
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
        //resize(); // Resize if needed

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
                        if(parent != null) parent.setNextNode(node.getNextNode());
                        else array[position] = node.getNextNode();
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

        public WordNode() {
            this(null, 0);
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
