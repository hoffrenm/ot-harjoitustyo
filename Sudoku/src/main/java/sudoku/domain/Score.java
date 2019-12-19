/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

/**
 * Score entry for single successfully solved sudoku.
 * 
 * @author Mika Hoffren
 */
public class Score implements Comparable<Score> {
    
    private String name;
    private String time;
    private String level;

    /**
     * Generates Score entry that can be stored to file.
     * 
     * @param name name of user
     * @param time time at completion of a game
     * @param level difficulty level of game
     */
    public Score(String name, String time, String level) {
        this.name = name;
        this.time = time;
        this.level = level;
    }

    /**
     * returns score as comma separated values for file writing in 
     * "name;time;level" format.
     * 
     * @return score as csv
     */
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
    
    private int minutes() {
        return Integer.parseInt(time.split(":")[0]);
    }
    
    private int seconds() {
        return Integer.parseInt(time.split(":")[1]);
    }

    @Override
    public int compareTo(Score o) {
        if (this.minutes() == o.minutes()) {
            return this.seconds() - o.seconds();
        }
        
        return this.minutes() - o.minutes();
    }
    
}
