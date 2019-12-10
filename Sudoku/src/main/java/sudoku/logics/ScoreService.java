/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.logics;

import java.util.List;
import sudoku.dao.FileScore;
import sudoku.domain.Score;

/**
 *
 * @author Mika Hoffren
 */
public class ScoreService {
    
    List<Score> scores;
    FileScore fileScore;

    public ScoreService() {
        this.fileScore = new FileScore("scores.txt");
        this.scores = fileScore.getScores();
    }
    
    public List<Score> getScores() {
        return scores;
    }
    public void addResult(String name, String time, String level) {
        scores.add(new Score(name, time, level));
    }
    
    public void saveScores() throws Exception {
        fileScore.save(scores);
    }
    
}
