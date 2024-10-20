package com.example.zeotap.rule_engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, Long> {
    RuleEntity findByRuleName(String name);
    RuleEntity findById(long id);

}


