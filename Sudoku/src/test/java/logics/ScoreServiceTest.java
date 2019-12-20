/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logics;

import java.io.File;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import sudoku.dao.FileScore;
import sudoku.logics.ScoreService;

/**
 *
 * @author Mika Hoffren
 */
public class ScoreServiceTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    public File testFile;
    public FileScore fileScore;
    public ScoreService scoreService;

    @Before
    public void setUp() throws Exception {
        testFile = temporaryFolder.newFile("testScores.txt");

        scoreService = new ScoreService(testFile.getAbsolutePath());
    }

    @Test
    public void thereAreNoScoresAtBeginning() {
        assertEquals(0, scoreService.getScores().size());
    }

    @Test
    public void scoresCanBeAddedToList() {
        scoreService.addResult("cricket", "2:33", "hard");
        scoreService.addResult("bug", "0:57", "easy");
        scoreService.addResult("bird", "1:29", "medium");

        assertEquals(3, scoreService.getScores().size());
    }

    @Test
    public void returnedScoresAreSorted() {
        scoreService.addResult("cricket", "2:33", "hard");
        scoreService.addResult("bug", "0:57", "easy");
        scoreService.addResult("bird", "1:29", "medium");

        assertEquals("bug", scoreService.getScores().get(0).getName());
        assertEquals("0:57", scoreService.getScores().get(0).getTime());
    }

    @Test
    public void maximum20ScoresAreReturned() {
        for (int i = 0; i < 40; i++) {
            scoreService.addResult("cricket", String.valueOf(i), "hard");
        }

        assertEquals(20, scoreService.getScores().size());
    }

    @Test(expected = Test.None.class)
    public void scoresWillBeSaved() throws Exception {
        scoreService.saveScores();
    }
}
