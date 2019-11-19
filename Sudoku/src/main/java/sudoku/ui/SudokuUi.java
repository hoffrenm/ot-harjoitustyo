package sudoku.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 *
 * @author hoffrenm
 */
public class SudokuUi extends Application {
    private static final int HEIGHT = 200;
    private static final int WIDTH = 200;

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Region());
        
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() {
        System.out.println("stage closed");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
