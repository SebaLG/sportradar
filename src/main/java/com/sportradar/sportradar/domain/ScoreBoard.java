package com.sportradar.sportradar.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ScoreBoard {

    private final Set<Game> games;

    public ScoreBoard() {
        this.games = new HashSet<>();
    }
}
