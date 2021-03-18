package com.sportradar.sportradar.adapter;

import com.sportradar.sportradar.domain.Game;
import com.sportradar.sportradar.domain.ScoreBoard;
import com.sportradar.sportradar.port.ScoreBoardUseCase;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

  @Override
  public Boolean finishGame(String homeTeamName, String awayTeamName) {
    return scoreBoard
        .getGames()
        .removeIf(
            game ->
                game.getHomeTeamName().equals(homeTeamName)
                    && game.getAwayTeamName().equals(awayTeamName));
  }

  @Override
  public Game updateGame(
      String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
    Game gameToUpdate =
        scoreBoard.getGames().stream()
            .filter(
                game ->
                    game.getHomeTeamName().equals(homeTeamName)
                        && game.getAwayTeamName().equals(awayTeamName))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException());

    gameToUpdate.setHomeTeamScore(homeTeamScore);
    gameToUpdate.setAwayTeamScore(awayTeamScore);
    return gameToUpdate;
  }

  @Override
  public String summaryGames() {
    final String[] summary = {""};
    scoreBoard.getGames().stream()
        .collect(Collectors.toCollection(ArrayDeque::new))
        .descendingIterator()
        .forEachRemaining(
            game ->
                summary[0] +=
                    (game.getHomeTeamName()
                        + " "
                        + game.getHomeTeamScore()
                        + " - "
                        + game.getAwayTeamName()
                        + " "
                        + game.getAwayTeamScore()
                        + "\n"));

    return summary[0];
  }
}
