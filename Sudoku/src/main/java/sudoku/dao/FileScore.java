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
 * Handles all functions related to reading and writing scores to file.
 *
 * @author Mika Hoffren
 */
public class FileScore {

    private String file;
    private List<Score> scores;

    /**
     * Reads file once upon construction from filename that is provided as
     * parameter. If file doesn't exists no file will be read nor created. File
     * is supposed to exists in root directory.
     *
     * @param file filename
     */
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

    /**
     * Saves all the scores in the list. Writes over existing resource to root
     * directory.
     *
     * @param scores list of scores to save.
     */
    public void save(List<Score> scores) {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Score score : scores) {
                writer.write(score.toFileFormat());
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception in writing file: " + e.getMessage());
        }
    }

    /**
     * Scores read from file. If file was not present, empty list of scores will
     * be returned.
     *
     * @return scores read from file
     */
    public List<Score> getScores() {
        return scores;
    }

}
