/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.logics;

import java.util.Collections;
import java.util.List;
import sudoku.dao.FileScore;
import sudoku.domain.Score;

/**
 * Offers methods to handle score related functions in user interface
 *
 * @author Mika Hoffren
 */
public class ScoreService {

    private List<Score> scores;
    private FileScore fileScore;

    public ScoreService(String filename) {
        this.fileScore = new FileScore(filename);
        this.scores = fileScore.getScores();
    }

    /**
     * Scores of games that have been read from the file.
     *
     * @return scores as sorted list
     */
    public List<Score> getScores() {
        Collections.sort(this.scores);

        if (this.scores.size() > 20) {
            return scores.subList(0, 20);
        }
        return scores;
    }

    /**
     * adds result to scoreboard
     *
     * @param name name of player
     * @param time time at completion of a game
     * @param level difficulty level
     */
    public void addResult(String name, String time, String level) {
        scores.add(new Score(name, time, level));
    }

    /**
     * Save all the stored scores. Method invokes file writer.
     *
     * @throws Exception throw IOException
     */
    public void saveScores() throws Exception {
        fileScore.save(scores);
    }

}
