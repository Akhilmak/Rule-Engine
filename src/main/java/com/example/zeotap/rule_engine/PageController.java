package com.example.zeotap.rule_engine;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PageController {
    @GetMapping("/")
    public RedirectView showRuleEvaluator() {
        return new RedirectView("RuleEvaluator.html");
    }

}
