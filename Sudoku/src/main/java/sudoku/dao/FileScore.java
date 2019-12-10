/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sudoku.domain.Score;

/**
 *
 * @author Mika Hoffren
 */
public class FileScore {

    private String file;
    private List<Score> scores;

    public FileScore(String file) {
        this.file = file;
        this.scores = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(file));

            while (scanner.hasNextLine()) {
                String[] entry = scanner.nextLine().split(";");
                
                String name = entry[0];
                String time = entry[1];
                String level = entry[2];
                
                scores.add(new Score(name, time, level));
            }
        } catch (Exception e) {
            System.out.println("Exception in reading file: " + e.getMessage());
        }
    }
    
    public void save(List<Score> scores) {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Score score : scores) {
                writer.write(score.toFileFormat());
            }
        } catch (Exception e) {
            System.out.println("Exception in writing file: " + e.getMessage());
        }
    }
    
    public List<Score> getScores() {
        return scores;
    }
    
}
