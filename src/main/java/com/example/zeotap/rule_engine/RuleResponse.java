package com.example.zeotap.rule_engine;

public class RuleResponse {
    private Node ast;
    private String ruleName;

    public RuleResponse(Node ast, String ruleName) {
        this.ast = ast;
        this.ruleName = ruleName;
    }

    public Node getAst() {
        return ast;
    }

    public String getRuleName() {
        return ruleName;
    }
}
