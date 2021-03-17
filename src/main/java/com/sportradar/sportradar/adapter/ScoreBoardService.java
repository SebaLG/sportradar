package com.sportradar.sportradar.adapter;

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


}
