package com.Bhalerao.ScrumPlay.service.Impl;
import com.Bhalerao.ScrumPlay.Dto.ProblemStatementDto;
import com.Bhalerao.ScrumPlay.model.ProblemStatement;
import com.Bhalerao.ScrumPlay.repository.ProblemStatementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProblemStatementServiceImplTest {

    @Mock
    private ProblemStatementRepository problemStatementRepository;

    @InjectMocks
    private ProblemStatementServiceImpl problemStatementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAllStatements() {
        // Mock the repository to return a list of problem statements
        when(problemStatementRepository.findAll()).thenReturn(Arrays.asList(
                new ProblemStatement(1, "1st Statement", 4, "1st comment"),
                new ProblemStatement(2, "2nd Statement", 4, "2nd comment")
        ));

        // Call the service method
        List<ProblemStatementDto> result = problemStatementService.findAllStatements();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Statement 1", result.get(0).getStatement());
        assertEquals("Comments 2", result.get(1).getComments());

        // Verify that the repository method was called
        verify(problemStatementRepository, times(1)).findAll();
    }

    @Test
    void testSaveProblemStatement() {
        // Mock the repository to save a problem statement
        ProblemStatementDto inputDto = new ProblemStatementDto(1, "New Statement", 4, "new comment");
        problemStatementService.saveProblemStatement(inputDto);

        // Verify that the repository method was called with the correct argument
        verify(problemStatementRepository, times(1)).save(argThat(ps ->
                ps.getStatement().equals("New Statement") &&
                        ps.getComments().equals("New Comments") &&
                        ps.getNumOfUserStories() == 4
        ));
    }

    @Test
    void testFindStatementById() {
        // Mock the repository to return a problem statement with the given ID
        when(problemStatementRepository.findById(1L)).thenReturn(Optional.of(
                new ProblemStatement(1, "Statement 1", 3, "Comments 1")
        ));

        // Call the service method
        ProblemStatementDto result = problemStatementService.findStatementById(1);

        // Verify the result
        assertEquals("Statement 1", result.getStatement());
        assertEquals("Comments 1", result.getComments());

        // Verify that the repository method was called with the correct argument
        verify(problemStatementRepository, times(1)).findById(1L);
    }
}
