/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import sudoku.dao.FileScore;
import static org.junit.Assert.*;
import sudoku.domain.Score;
import sudoku.logics.ScoreService;

/**
 *
 * @author Mika Hoffren
 */
public class FileScoreTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    public File testFile;
    public FileScore fileScore;
    public ScoreService scoreService;

    @Before
    public void setUp() throws Exception {
        testFile = temporaryFolder.newFile("testScores.txt");

        try {
            FileWriter fw = new FileWriter(testFile.getAbsolutePath());
            fw.write("chimpanzee;12:34;easy\n");
            fw.write("toad;0:45;hard\n");
            fw.write("unicorn;2:04;medium");

            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        fileScore = new FileScore(testFile.getAbsolutePath());
    }

    @Test(expected = Test.None.class)
    public void canBeInitializedWithExistingFile() {
        assertNotNull(fileScore);
    }

    @Test(expected = Test.None.class)
    public void scoresFromFileAreStoredToList() {
        System.out.println(fileScore.getScores().size());
        assertEquals(3, fileScore.getScores().size());
    }

    @Test(expected = Test.None.class)
    public void scoresAreSavedToFile() {
        fileScore.getScores().add(new Score("monkey", "40:52,", "easy"));
        fileScore.getScores().add(new Score("donkey", "2:32,", "easy"));
        fileScore.getScores().add(new Score("dog", "5:41,", "easy"));
        fileScore.getScores().add(new Score("cat", "0:11,", "easy"));
        fileScore.save(fileScore.getScores());

        fileScore = new FileScore(testFile.getAbsolutePath());

        assertEquals(7, fileScore.getScores().size());
    }

    @After
    public void tearDown() {
        testFile.delete();
    }

}
