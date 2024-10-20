package com.example.zeotap.rule_engine;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;
    RuleParser ruleParser=new RuleParser();

    public Node createRule(String ruleString,String ruleName) {

        Node ast = parseRule(ruleString);
        RuleEntity rule = new RuleEntity(ruleName.isEmpty()?"Default Rule Name":ruleName, ruleString); // You may want to pass the rule name
        ruleRepository.save(rule);
        return ast;
    }


    public Node parseRule(String ruleString) {// Remove whitespace

        return ruleParser.parse(ruleString);
    }

//    public Node combineRules(List<String> rules) {
//        Node combinedNode = null;
//        for (String rule : rules) {
//            Node currentNode = createRule(rule);
//            if (combinedNode == null) {
//                combinedNode = currentNode;
//            } else {
//                combinedNode = new Node("operator", "AND", combinedNode, currentNode);
//            }
//        }
//        return combinedNode;
//    }

    public boolean evaluateRule(Node rootNode, Map<String, Object> data) {
        ASTEvaluator astEvaluator=new ASTEvaluator();
        return astEvaluator.evaluate(rootNode,data);
    }
}