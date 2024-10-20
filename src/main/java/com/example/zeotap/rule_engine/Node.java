package com.example.zeotap.rule_engine;


public class Node {
    String type;
    String value;
    String operator;
    String rightValue;
    Node left;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    Node right;

    public Node(String type, String operator, Node left, Node right) {
        this.type = type;
        this.operator = operator;
        this.left = left;
        this.right = right;
        this.value = null;
        this.rightValue = null;
    }

    public Node(String type, String value, String operator, String rightValue) {
        this.type = type;
        this.value = value;
        this.operator = operator;
        this.rightValue = rightValue; // Store the right-hand side value
        this.left = null;  // No left child for condition nodes
        this.right = null; // No right child for condition nodes
    }
}