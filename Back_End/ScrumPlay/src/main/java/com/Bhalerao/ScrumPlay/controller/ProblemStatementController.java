package com.Bhalerao.ScrumPlay.controller;

import com.Bhalerao.ScrumPlay.Dto.ProblemStatementDto;

import com.Bhalerao.ScrumPlay.service.ProblemStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ProblemStatementController {
    private ProblemStatementService problemStatementService;

    @Autowired
    ProblemStatementController(ProblemStatementService problemStatementService){
        this.problemStatementService = problemStatementService;
    }

    @GetMapping("/statements")
    public ResponseEntity<List<ProblemStatementDto> > getStatements() {
        List<ProblemStatementDto> statements = problemStatementService.findAllStatements();
        return ResponseEntity.ok(statements);
    }

    @GetMapping("/statements/{statementId}")
    public ResponseEntity<ProblemStatementDto> getStatementById(@PathVariable int statementId) {
        ProblemStatementDto statement = problemStatementService.findStatementById(statementId);

        if (statement != null) {
            return ResponseEntity.ok(statement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add-statement")
    public ResponseEntity<String> AddStatement(@RequestBody ProblemStatementDto problemStatementDto) {
        try {
            problemStatementService.saveProblemStatement(problemStatementDto);
            return ResponseEntity.ok("Statement added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding statement");
        }
    }
}
