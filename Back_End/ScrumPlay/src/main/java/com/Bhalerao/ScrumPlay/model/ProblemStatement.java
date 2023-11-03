package com.Bhalerao.ScrumPlay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ProblemStatement")
public class ProblemStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statementid;
    private String Statement;
    private int NumOfUserStories;
    private String comments;
}
