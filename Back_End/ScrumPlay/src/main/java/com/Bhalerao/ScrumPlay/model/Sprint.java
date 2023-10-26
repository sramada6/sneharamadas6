package com.Bhalerao.ScrumPlay.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sprint_details")
public class Sprint {
    private int sprintid;
    private int teamSize;
    private int sprintLength;
    private float scrumCallLength;
}
