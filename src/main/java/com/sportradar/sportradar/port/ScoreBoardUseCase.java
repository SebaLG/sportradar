package com.sportradar.sportradar.port;

import com.sportradar.sportradar.domain.Game;

public interface  ScoreBoardUseCase {

    public Game startGame(String homeTeamName, String awayTeamName);

    public Boolean finishGame(String homeTeamName, String awayTeamName);
}
