package com.Bhalerao.ScrumPlay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Entity;
//import org.hibernate.annotations.Table;

import javax.lang.model.element.Name;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerid;
    private String playerName;
    private String playerRole;
}
