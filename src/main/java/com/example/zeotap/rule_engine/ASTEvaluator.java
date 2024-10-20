package com.example.zeotap.rule_engine;

import java.util.Map;

public class ASTEvaluator {
    public boolean evaluate(Node root, Map<String, Object> data) {
        if (root == null) {
            return false; // Base case for null nodes
        }

        switch (root.type) {
            case "condition":
                return evaluateCondition(root, data);
            case "operator":
                return evaluateOperator(root, data);
            default:
                throw new IllegalArgumentException("Unknown node type: " + root.type);
        }
    }

    private boolean evaluateCondition(Node conditionNode, Map<String, Object> data) {
        String leftValue = conditionNode.value; // e.g., "age"
        String operator = conditionNode.operator; // e.g., ">"
        String rightValue = conditionNode.rightValue.replaceAll("'", ""); // Remove quotes for comparison

        Object leftData = data.get(leftValue);

        // Handle numeric comparisons
        if (leftData instanceof Number && isNumeric(rightValue)) {
            double leftNum = ((Number) leftData).doubleValue();
            double rightNum = Double.parseDouble(rightValue);
            return evaluateNumericCondition(leftNum, operator, rightNum);
        }

        // Handle string comparisons
        if (leftData instanceof String) {
            return evaluateStringCondition((String) leftData, operator, rightValue);
        }

        return false; // Default case if no conditions are met
    }

    private boolean evaluateNumericCondition(double leftNum, String operator, double rightNum) {
        switch (operator) {
            case ">":
                return leftNum > rightNum;
            case "<":
                return leftNum < rightNum;
            case "=":
                return leftNum == rightNum;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private boolean evaluateStringCondition(String leftStr, String operator, String rightStr) {
        switch (operator) {
            case "=":
                return leftStr.equals(rightStr);
            default:
                throw new IllegalArgumentException("Unknown operator for string comparison: " + operator);
        }
    }

    private boolean evaluateOperator(Node operatorNode, Map<String, Object> data) {
        boolean leftResult = evaluate(operatorNode.left, data);
        boolean rightResult = evaluate(operatorNode.right, data);

        switch (operatorNode.operator) {
            case "AND":
                return leftResult && rightResult;
            case "OR":
                return leftResult || rightResult;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operatorNode.operator);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
