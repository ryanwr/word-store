package com.ryanwelch.wordstore;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 *
 * The benefits of using a binary tree implementation using references to objects
 * is that when an item is inserted it does not need to reshuffle an array or allocate
 * a whole new array when it runs out of space. It does however use more memory.
 */
public class WordStoreBinaryTreeImp implements WordStore {

    private WordNode rootNode = null;

    public WordStoreBinaryTreeImp() {}

    @Override
    public void add(String word) {
        if(rootNode == null) {
            rootNode = new WordNode(word);
            return;
        }

        WordNode node = rootNode;
        while(node != null) {
            int value = word.compareTo(node.getWord());
            if(value < 0) {
                if(node.hasLeftNode()) {
                    node = node.getLeftNode();
                } else {
                    node.setLeftNode(new WordNode(word));
                    break;
                }
            } else if(value > 0) {
                if(node.hasRightNode()) {
                    node = node.getRightNode();
                } else {
                    node.setRightNode(new WordNode(word));
                    break;
                }
            } else {
                node.incrementCount();
                break;
            }
        }
    }

    @Override
    public int count(String word) {
        if(rootNode == null) {
            return 0;
        }

        WordNode node = rootNode;
        while(node != null) {
            int value = word.compareTo(node.getWord());
            if(value < 0) {
                if(node.getLeftNode() != null) node = node.getLeftNode();
                else return 0;
            } else if(value > 0) {
                if(node.getRightNode() != null) node = node.getRightNode();
                else return 0;
            } else {
                return node.getCount();
            }
        }

        return 0;
    }

    private void remove(String word, WordNode node, WordNode parent) {
        int value = word.compareTo(node.getWord());
        if(value < 0) {
            if(node.getLeftNode() != null) remove(word, node.getLeftNode(), node);
        } else if(value > 0) {
            if(node.getRightNode() != null) remove(word, node.getRightNode(), node);
        } else if(node.getCount() > 1) { // If a higher count of the word, remove a count instead of node
            node.decrementCount();
        } else { // Else remove the node
            if (!node.hasLeftNode() || !node.hasRightNode()) { // If the node has no child or only one child, set the parent to it's child or null
                if(parent != null) parent.replaceNode(node, node.hasLeftNode() ? node.getLeftNode() : node.getRightNode());
                else rootNode = node.hasLeftNode() ? node.getLeftNode() : node.getRightNode();
            } else { // If both children are occupied
                // Get the minimum node and copy to this node
                WordNode min = node.getRightNode().getMinNode();
                node.setWord(min.getWord());
                node.setCount(min.getCount());
                // Now remove the duplicate minimum node
                remove(min.getWord(), node.getRightNode(), node);
            }
        }
    }

    @Override
    public void remove(String word) {
        if(rootNode == null) return;

        remove(word, rootNode, null);
    }


    private class WordNode {

        private WordNode leftNode = null;
        private WordNode rightNode = null;

        private String word;
        private int count;

        WordNode(String word, int count) {
            this.word = word;
            this.count = count;
        }

        WordNode(String word) {
            this(word, 1);
        }

        void setWord(String word) {
            this.word = word;
        }

        String getWord() {
            return word;
        }

        int getCount() {
            return count;
        }

        void setCount(int count) {
            this.count = count;
        }

        void incrementCount() {
            this.count++;
        }

        void decrementCount() {
            this.count--;
        }

        boolean hasLeftNode() {
            return leftNode != null;
        }

        WordNode getLeftNode() {
            return leftNode;
        }

        void setLeftNode(WordNode leftNode) {
            this.leftNode = leftNode;
        }

        boolean hasRightNode() {
            return rightNode != null;
        }

        WordNode getRightNode() {
            return rightNode;
        }

        void setRightNode(WordNode rightNode) {
            this.rightNode = rightNode;
        }

        void replaceNode(WordNode node, WordNode newNode) {
            if(leftNode == node) leftNode = newNode;
            else if(rightNode == node) rightNode = newNode;
        }

        WordNode getMinNode() {
            if(leftNode == null) return this;
            else return leftNode.getMinNode();
        }

        WordNode getMaxNode() {
            if(rightNode == null) return this;
            else return rightNode.getMinNode();
        }
    }

}
