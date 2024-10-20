package com.example.zeotap.rule_engine;

public class RuleParser {
    private String input;
    private int index;

    public Node parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        this.input = input.replaceAll("\\s+", ""); // Remove spaces
        this.index = 0;
        return parseExpression();
    }

    private Node parseExpression() {
        Node left = parseTerm();

        while (index < input.length() && (input.charAt(index) == 'O' || input.charAt(index) == 'A')) {
            char operator = input.charAt(index);
            index += operator == 'O' ? 2 : 3; // Skip "OR" or "AND"
            Node right = parseTerm();
            left = new Node("operator", operator == 'O' ? "OR" : "AND", left, right);
        }
        return left;
    }

    private Node parseTerm() {
        if (input.charAt(index) == '(') {
            index++; // Skip '('
            Node node = parseExpression();
            index++; // Skip ')'
            return node;
        } else {
            return parseCondition();
        }
    }

    private Node parseCondition() {
        String leftValue = parseIdentifier(); // e.g., "age"
        String operator = parseOperator();     // e.g., ">"
        String rightValue = parseValue();      // e.g., "30" or "'Sales'"

        // Create a condition node with leftValue as the operand,
        // operator as a separate field, and rightValue as well
        return new Node("condition", leftValue, operator, rightValue);
    }

    private String parseIdentifier() {
        StringBuilder sb = new StringBuilder();
        while (index < input.length() && Character.isLetter(input.charAt(index))) {
            sb.append(input.charAt(index));
            index++;
        }
        return sb.toString();
    }

    private String parseOperator() {
        StringBuilder sb = new StringBuilder();
        while (index < input.length() && (input.charAt(index) == '=' || input.charAt(index) == '>' || input.charAt(index) == '<')) {
            sb.append(input.charAt(index));
            index++;
        }
        return sb.toString();
    }

    private String parseValue() {
        StringBuilder sb = new StringBuilder();
        if (input.charAt(index) == '\'') {
            index++; // Skip opening quote
            while (index < input.length() && input.charAt(index) != '\'') {
                sb.append(input.charAt(index));
                index++;
            }
            index++; // Skip closing quote
            return "'" + sb.toString() + "'"; // Return the value enclosed in quotes
        } else {
            while (index < input.length() && Character.isDigit(input.charAt(index))) {
                sb.append(input.charAt(index));
                index++;
            }
            return sb.toString(); // Return the raw numeric value as needed
        }
    }
}