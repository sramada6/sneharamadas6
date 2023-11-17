package com.Bhalerao.ScrumPlay.model;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "UserStory")
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storyid;

    private int storyPoints = 0;
    private String status = "new";
    private String storyDescription;

    @ManyToOne
    @JoinColumn(name = "assigned_player_id")
    private Player assignedTo;


    @ManyToOne
    @JoinColumn(name = "problem_id")
    private ProblemStatement problemStatement;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

    private int workRemaining;

    public void setDescription(String description) {
    }
}