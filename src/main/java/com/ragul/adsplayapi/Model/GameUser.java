package com.ragul.adsplayapi.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GameUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Game game;

    @ManyToOne
    private User user;
    private int score;
}
