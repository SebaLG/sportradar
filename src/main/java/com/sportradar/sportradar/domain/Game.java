package com.sportradar.sportradar.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Game {

    private final String homeTeamName;
    private final String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;

}
