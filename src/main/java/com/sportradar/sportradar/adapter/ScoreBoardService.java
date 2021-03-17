package com.sportradar.sportradar.adapter;

import com.sportradar.sportradar.domain.Game;
import com.sportradar.sportradar.domain.ScoreBoard;
import com.sportradar.sportradar.port.ScoreBoardUseCase;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ScoreBoardService implements ScoreBoardUseCase {

  private final ScoreBoard scoreBoard;

  public ScoreBoardService() {
    scoreBoard = new ScoreBoard();
  }


  @Override
  public Game startGame(String homeTeamName, String awayTeamName) {
    Game game =
            Game.builder()
                    .homeTeamName(homeTeamName)
                    .awayTeamName(awayTeamName)
                    .homeTeamScore(0)
                    .awayTeamScore(0)
                    .build();

    scoreBoard.getGames().add(game);
    return game;
  }

}
