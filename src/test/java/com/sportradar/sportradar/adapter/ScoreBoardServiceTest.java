package com.sportradar.sportradar.adapter;

import com.sportradar.sportradar.domain.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

public class ScoreBoardServiceTest {

  private ScoreBoardService scoreBoardService;

  @BeforeEach
  void initializeScoreBoardService() {
    scoreBoardService = new ScoreBoardService();
  }

  @ParameterizedTest
  @CsvSource({"Uruguay,Italy", "Spain,Brazil", "Mexico,Canada"}) // Given
  void startGame_CorrectGame_AddedInScoreBoard(String homeTeamName, String awayTeamName) {
    int previousSize = scoreBoardService.getScoreBoard().getGames().size();
    // When
    scoreBoardService.startGame(homeTeamName, awayTeamName);
    // Then
    Assertions.assertEquals(previousSize + 1, scoreBoardService.getScoreBoard().getGames().size());
  }

  @ParameterizedTest
  @CsvSource({"Uruguay,Italy", "Spain,Brazil", "Mexico,Canada"}) // Given
  void startGame_CorrectGame_InitialScoreZero(String homeTeamName, String awayTeamName) {
    // When
    Game gameAdded = scoreBoardService.startGame(homeTeamName, awayTeamName);
    // Then
    Assertions.assertEquals(0, gameAdded.getHomeTeamScore());
    Assertions.assertEquals(0, gameAdded.getAwayTeamScore());
  }

  @Test
  void finishGame_CorrectGame_RemoveInScoreBoard() {
    // Given
    String homeTeamName = "Uruguay";
    String awayTeamName = "Italy";
    var games = scoreBoardService.getScoreBoard().getGames();
    games.add(
        Game.builder()
            .homeTeamName(homeTeamName)
            .awayTeamName(awayTeamName)
            .homeTeamScore(0)
            .awayTeamScore(0)
            .build());

    // When
    scoreBoardService.finishGame(homeTeamName, awayTeamName);

    // Then
    Assertions.assertEquals(
        false,
        games.stream()
            .anyMatch(
                game ->
                    game.getHomeTeamName().equals(homeTeamName)
                        && game.getAwayTeamName().equals(awayTeamName)));
  }

  @Test
  void updateGame_GameExisting_UpdateInScoreBoard() {
    // Given
    String homeTeamName = "Uruguay";
    String awayTeamName = "Italy";
    int homeTeamScore = 2;
    int awayTeamScore = 2;
    var games = scoreBoardService.getScoreBoard().getGames();
    games.add(
        Game.builder()
            .homeTeamName(homeTeamName)
            .awayTeamName(awayTeamName)
            .homeTeamScore(0)
            .awayTeamScore(0)
            .build());

    // When
    scoreBoardService.updateGame(homeTeamName, awayTeamName, homeTeamScore, awayTeamScore);

    // Then
    Assertions.assertEquals(
        true,
        games.stream()
            .anyMatch(
                game ->
                    game.getHomeTeamName().equals(homeTeamName)
                        && game.getAwayTeamName().equals(awayTeamName)
                        && game.getAwayTeamScore() == 2
                        && game.getHomeTeamScore() == 2));
  }

  @Test
  void updateGame_NonExistingGame_ThrowExeption() {
    // Given
    Throwable e = null;
    String homeTeamName = "Uruguay";
    String awayTeamName = "Italy";
    var games = scoreBoardService.getScoreBoard().getGames();
    games.add(
        Game.builder()
            .homeTeamName(homeTeamName)
            .awayTeamName(awayTeamName)
            .homeTeamScore(0)
            .awayTeamScore(0)
            .build());

    // When
    try {
      scoreBoardService.updateGame("Spain", awayTeamName, 2, 2);
    } catch (Throwable ex) {
      e = ex;
    }

    // Then
    Assertions.assertTrue(e instanceof NoSuchElementException);
  }


  @Test
  void summaryGames_ShowSummary() {
    // Given
    String summaryExpected = "Italy 0 - Brazil 0\n" + "Spain 0 - Uruguay 0\n";
    var games = scoreBoardService.getScoreBoard().getGames();
    games.add(
            Game.builder()
                    .homeTeamName("Spain")
                    .awayTeamName("Uruguay")
                    .homeTeamScore(0)
                    .awayTeamScore(0)
                    .build());
     games.add(
            Game.builder()
                    .homeTeamName("Italy")
                    .awayTeamName("Brazil")
                    .homeTeamScore(0)
                    .awayTeamScore(0)
                    .build());

    String summary = scoreBoardService.summaryGames();
    Assertions.assertEquals(summaryExpected, summary);
  }
}
