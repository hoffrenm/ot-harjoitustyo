/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sudoku.domain.Score;

/**
 *
 * @author Mika Hoffren
 */
public class ScoreTest {

    Score score;

    @Before
    public void setUp() {
        score = new Score("horse", "22:11", "hard");
    }

    @Test
    public void scoreCanBeCreated() {
        assertNotNull(score);
    }

    @Test
    public void scoreIsReturnedAsCSV() {
        assertEquals("horse;22:11;hard\n", score.toFileFormat());
    }

    @Test
    public void nameIsCorrect() {
        assertEquals("horse", score.getName());
    }

    @Test
    public void difficultyIsCorrect() {
        assertEquals("hard", score.getLevel());
    }

    @Test
    public void timeIsCorrectly() {
        assertEquals("22:11", score.getTime());
    }

    @Test
    public void scoresAreComparedCorretlyByTime1() {
        Score score1 = new Score("mouse", "2:59", "easy");
        Score score2 = new Score("rat", "0:23", "easy");

        assertTrue(score1.compareTo(score2) > 0);
    }

    @Test
    public void scoresAreComparedCorretlyByTime2() {
        Score score1 = new Score("mouse", "0:03", "hard");
        Score score2 = new Score("rat", "0:23", "easy");

        assertTrue(score1.compareTo(score2) < 0);
    }

}
