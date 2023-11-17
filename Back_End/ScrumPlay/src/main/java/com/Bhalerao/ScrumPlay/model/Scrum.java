package com.Bhalerao.ScrumPlay.model;

import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Scrum")
public class Scrum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrumid;
    private int scrumCallDuration;
    private int playersPresent;
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player scrumMaster;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;
}