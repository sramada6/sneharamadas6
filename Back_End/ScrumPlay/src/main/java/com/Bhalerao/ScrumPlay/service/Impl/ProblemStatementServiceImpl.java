package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.ProblemStatementDto;

import com.Bhalerao.ScrumPlay.Dto.SprintDto;
import com.Bhalerao.ScrumPlay.model.ProblemStatement;

import com.Bhalerao.ScrumPlay.model.Sprint;
import com.Bhalerao.ScrumPlay.repository.ProblemStatementRepository;

import com.Bhalerao.ScrumPlay.service.ProblemStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProblemStatementServiceImpl implements ProblemStatementService {
    private ProblemStatementRepository problemStatementRepository;

    @Autowired
    public ProblemStatementServiceImpl(ProblemStatementRepository problemStatementRepository){
        this.problemStatementRepository = problemStatementRepository;
    }

    @Override
    public List<ProblemStatementDto> findAllStatements() {
        List<ProblemStatement> ps = problemStatementRepository.findAll();
        return ps.stream().map((statement) -> mapToStatementDto(statement)).collect(Collectors.toList());
    }

    @Override
    public void saveProblemStatement(ProblemStatementDto problemStatementDto) {
        ProblemStatement ps = new ProblemStatement();
        ps.setStatement(problemStatementDto.getStatement());
        ps.setComments(problemStatementDto.getComments());
        ps.setNumOfUserStories(problemStatementDto.getNumOfUserStories());
        ps.setStatementid(problemStatementDto.getStatementid());
        problemStatementRepository.save(ps);
    }

    @Override
    public ProblemStatementDto findStatementById(int statementId) {
        Optional<ProblemStatement> statement = problemStatementRepository.findById((long) statementId);

        if (statement.isPresent()) {
            // Convert ProblemStatement entity to ProblemStatementDto
            return mapToStatementDto(statement.get());
        } else {
            return null; // or throw an exception, depending on your use case
        }
    }

    private ProblemStatementDto mapToStatementDto(ProblemStatement statement) {
        ProblemStatementDto psdto = ProblemStatementDto.builder()
                .statementid(statement.getStatementid())
                .NumOfUserStories(statement.getNumOfUserStories())
                .Statement(statement.getStatement())
                .comments(statement.getComments())
                .build();
        return psdto;
    }
}
