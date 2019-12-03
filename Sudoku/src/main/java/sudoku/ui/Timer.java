/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author Mika Hoffren
 */
public class Timer {

    private Timeline timeline;
    private long seconds;
    private long minutes;
    private Label label;

    public Timer() {
        this.timeline = new Timeline();
        this.seconds = 0;
        this.minutes = 0;
        this.label = new Label("Time: 00:00");
        label.setFont(new Font("Arial", 34));
        label.setTextFill(Color.WHITESMOKE);
        init();
    }

    private void init() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        e -> incrementTime()),
                new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void incrementTime() {
        if (seconds < 59) {
            seconds++;
        } else {
            seconds = 0;
            minutes++;
        }
        
        this.label.setText("Time " + minutes + ":" + seconds);
    }
    
    public void start() {
        timeline.play();
    }
    public void stop() {
        timeline.stop();
    }

    public void reset() {
        this.seconds = 0;
        this.minutes = 0;
    }

    public Label getTimer() {
        return this.label;
    }

}
