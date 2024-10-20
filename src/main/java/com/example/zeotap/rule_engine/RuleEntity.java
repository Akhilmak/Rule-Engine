package com.example.zeotap.rule_engine;

import jakarta.persistence.*;


@Entity
@Table(name = "rules")
public class RuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rule_name", nullable = false)
    private String ruleName;

    @Column(name = "rule_string", columnDefinition = "TEXT", nullable = false)
    private String ruleString;

    // Constructors
    public RuleEntity() {}

    public RuleEntity(String ruleName, String ruleString) {
        this.ruleName = ruleName;
        this.ruleString = ruleString;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }
}
