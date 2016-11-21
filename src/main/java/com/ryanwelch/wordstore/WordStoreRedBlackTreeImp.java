package com.ryanwelch.wordstore;

/**
 * Copyright 2016 (C) Ryan Welch
 *
 * @author Ryan Welch
 */
public class WordStoreRedBlackTreeImp implements WordStore {

    private WordNode rootNode = null;

    public WordStoreRedBlackTreeImp() {}

    private WordNode add(String word, WordNode node) {
        int value = word.compareTo(node.getWord());
        if(value < 0) {
            if(node.hasLeftNode()) {
                return add(word, node.getLeftNode());
            } else {
                node.setLeftNode(new WordNode(word, NodeColor.RED));
                return node.getLeftNode();
            }
        } else if(value > 0) {
            if(node.hasRightNode()) {
                return add(word, node.getRightNode());
            } else {
                node.setRightNode(new WordNode(word, NodeColor.RED));
                return node.getRightNode();
            }
        } else {
            node.incrementCount();
            return null; // No need for recoloring
        }
    }

    private void recolor(WordNode insertedNode) {

    }

    @Override
    public void add(String word) {
        if(rootNode == null) {
            rootNode = new WordNode(word, NodeColor.BLACK); // Root node is always black
            return;
        }

        // Perform standard BST insertion
        WordNode insertedNode = add(word, rootNode);

        if(insertedNode != null) {
            recolor(insertedNode);
        }
    }

    public int getCount(String word, WordNode node) {
        int value = word.compareTo(node.getWord());
        if(value < 0) {
            if(node.getLeftNode() != null) return getCount(word, node.getLeftNode());
            else return 0;
        } else if(value > 0) {
            if(node.getRightNode() != null) return getCount(word, node.getRightNode());
            else return 0;
        } else {
            return node.getCount();
        }
    }

    @Override
    public int count(String word) {
        if(rootNode == null) {
            return 0;
        }

        return getCount(word, rootNode);
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

    private enum NodeColor {
        BLACK,
        RED
    }

    private class WordNode {

        private WordNode leftNode = null;
        private WordNode rightNode = null;
        private NodeColor nodeColor;

        private String word;
        private int count;

        public WordNode(String word, int count, NodeColor color) {
            this.word = word;
            this.count = count;
            this.nodeColor = color;
        }

        public WordNode(String word) {
            this(word, 1, NodeColor.BLACK);
        }

        public WordNode(String word, NodeColor color) {
            this(word, 1, color);
        }

        public void setWord(String word) {
            this.word = word;
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

        public boolean hasLeftNode() {
            return leftNode != null;
        }

        public WordNode getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(WordNode leftNode) {
            this.leftNode = leftNode;
        }

        public boolean hasRightNode() {
            return rightNode != null;
        }

        public WordNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(WordNode rightNode) {
            this.rightNode = rightNode;
        }

        public void setColor(NodeColor color) { nodeColor = color; }

        public NodeColor getColor() { return nodeColor; }

        public void removeNode(WordNode node) {
            if(leftNode == node) leftNode = null;
            else if(rightNode == node) rightNode = null;
        }

        public void replaceNode(WordNode node, WordNode newNode) {
            if(leftNode == node) leftNode = newNode;
            else if(rightNode == node) rightNode = newNode;
        }

        public boolean isLeaf() {
            return leftNode == null && rightNode == null;
        }

        public WordNode getMinNode() {
            if(leftNode == null) return this;
            else return leftNode.getMinNode();
        }

        public WordNode getMaxNode() {
            if(rightNode == null) return this;
            else return rightNode.getMinNode();
        }
    }
}
