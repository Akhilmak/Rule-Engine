package com.example.zeotap.rule_engine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;
    @Autowired
    private RuleRepository ruleRepository;

    @GetMapping("")
    public List<RuleEntity> allRules(){
        return ruleRepository.findAll();
    }
    @PostMapping("/create")
    public ResponseEntity<RuleResponse> createRule(@RequestBody RuleEntity rule) {
        System.out.println("Parsing rule: " + rule.getRuleString()+"");
        Node ast = ruleService.createRule(rule.getRuleString(),rule.getRuleName());
        RuleResponse response = new RuleResponse(ast, rule.getRuleName());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/rule/{id}")
    public String getRule(@PathVariable Long id) {
        return ruleRepository.findById(id).get().getRuleString();
    }



//    @PostMapping("/combine")
//    public ResponseEntity<Node> combineRules(@RequestBody List<String> rules) {
//        Node combinedAST = ruleService.combineRules(rules);
//        return ResponseEntity.ok(combinedAST);
//    }

    @PostMapping("/evaluate")
    public ResponseEntity<Boolean> evaluateRule(@RequestBody Map<String, Object> requestData) {
        Object ruleIdObj = requestData.get("ruleId");
        Long ruleId;
        if (ruleIdObj instanceof Number) {
            ruleId = ((Number) ruleIdObj).longValue();
        } else if (ruleIdObj instanceof String) {
            ruleId = Long.valueOf((String) ruleIdObj); // Convert String to Long
        } else {
            throw new IllegalArgumentException("Invalid type for ruleId");
        }
//        String ruleName = (String) requestData.get("ruleName");
        RuleEntity rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new IllegalArgumentException("Rule not found")); // Fetch the rule
        String ruleString=rule.getRuleString();

        Node rootNode = ruleService.parseRule(ruleString);
        Map<String, Object> userData = (Map<String, Object>) requestData.get("data");
        boolean result = ruleService.evaluateRule(rootNode, userData); // Evaluate the AST against user data
        return ResponseEntity.ok(result);
    }
}
