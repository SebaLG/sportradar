package com.sportradar.sportradar.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreBoardServiceTest {

  private ScoreBoardService scoreBoardService;

  @BeforeEach
  void initializeScoreBoardService(){
    scoreBoardService = new ScoreBoardService();
  }

  @Test
  @ParameterizedTest
  @CsvSource({"Uruguay,Italy", "Spain,Brazil", "Mexico,Canada"}) //Given
  void startGame_CorrectGame_AddedInScoreBoard(
      String homeTeamName, String awayTeamName) {
      //When
      int previousSize = scoreBoardService.getScoreBoard().getGames().size();
      //scoreBoardService.startGame(homeTeamName, awayTeamName);
      //Then
      Assertions.assertEquals(previousSize + 1, scoreBoardService.getScoreBoard().getGames().size());
  }

  @Test
  @ParameterizedTest
  @CsvSource({"Uruguay,Italy", "Spain,Brazil", "Mexico,Canada"}) //Given
  void startGame_CorrectGame_InitialScoreZero(
          String homeTeamName, String awayTeamName) {
    //When
    //Game gameAdded = scoreBoardService.startGame(homeTeamName, awayTeamName);

    //Then
    //Assertions.assertEquals(0, gameAdded.getHomeTeamScore);
    //Assertions.assertEquals(0, gameAdded.getAwayTeamScore);
  }

}
