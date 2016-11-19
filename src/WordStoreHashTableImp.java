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

    /**
     * Adds or increments a word in a list
     * @param word
     * @param node
     */
    private void add(String word, WordNode node) {
        if(word.equals(node.getWord())) node.incrementCount();
        else if(node.hasNextNode()) add(word, node.getNextNode());
        else {
            node.setNextNode(new WordNode(word));
            collisions++;
            items++;
        }
    }

    @Override
    public void add(String word) {
        resize(); // Resize if needed

        int position = getIndex(word, array.length);
        if(array[position] == null) {
            array[position] = new WordNode(word);
            items++;
        } else {
            add(word, array[position]);
        }
    }

    private int getCount(String word, WordNode node) {
        if(word.equals(node.getWord())) return node.getCount();
        else if(node.hasNextNode()) return getCount(word, node.getNextNode());
        else return 0;
    }

    @Override
    public int count(String word) {
        int position = getIndex(word, array.length);
        return array[position] == null ? 0 : getCount(word, array[position]);
    }

    private void remove(String word, WordNode node, WordNode parent) {
        if(word.equals(node.getWord())) {
            if(node.getCount() > 1) {
                node.decrementCount();
            } else {
                parent.setNextNode(node.getNextNode());
                items--;
            }
        } else if(node.hasNextNode()) {
            remove(word, node.getNextNode(), node);
        }
    }

    @Override
    public void remove(String word) {
        //resize(); // Resize if needed

        int position = getIndex(word, array.length);
        if(array[position] == null) return;

        tempParent.setNextNode(array[position]);
        remove(word, array[position], tempParent);
        array[position] = tempParent.getNextNode();
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
