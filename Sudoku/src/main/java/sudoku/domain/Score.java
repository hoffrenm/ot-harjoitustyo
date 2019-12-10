/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

/**
 *
 * @author Mika Hoffren
 */
public class Score {
    
    private String name;
    private String time;
    private String level;

    public Score(String name, String time, String level) {
        this.name = name;
        this.time = time;
        this.level = level;
    }

    public String toFileFormat() {
        return name + ";" + time + ";" + level + "\n";
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time.replace("Time ", "");
    }

    public String getLevel() {
        return level;
    }
    
}
