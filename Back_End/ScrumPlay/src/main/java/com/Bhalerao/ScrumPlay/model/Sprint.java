package com.Bhalerao.ScrumPlay.model;

import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sprintid;
    private int teamSize;
    private int sprintLength;
    private int storyPointsCompleted;
    private float scrumCallLength;
    private LocalDate startDate;
    private LocalDate endDate;

}