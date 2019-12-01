package sudoku.ui;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import sudoku.domain.Board;
import sudoku.domain.Cell;

/**
 *
 * @author hoffrenm
 */
public class SudokuUi extends Application {

    private static final int HEIGHT = 450;
    private static final int WIDTH = 400;
    private final Board board;

    public SudokuUi() {
        this.board = new Board();
        board.newSudoku(35);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane layout = new BorderPane();
        VBox buttons = new VBox();
        Button playBtn = new Button("New game");
        Button scoresBtn = new Button("Scores");
        Button exitBtn = new Button("Exit");

        buttons.getChildren().addAll(playBtn, scoresBtn, exitBtn);

        GridPane boardGrid = initialize(board);

        playBtn.setOnAction((event) -> {
            board.newSudoku(80);
            layout.setCenter(initialize(board));
        });

        layout.setBottom(buttons);
        layout.setCenter(boardGrid);
        layout.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.show();
    }

    public GridPane initialize(Board board) {
        GridPane majorGrid = new GridPane();
        majorGrid.setPadding(new Insets(20, 20, 20, 20));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridPane minorGrid = new GridPane();
                minorGrid.setPadding(new Insets(2, 2, 2, 2));

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        minorGrid.add(createPanel(board.getCellInGrid(i * 3 + k, j * 3 + l)), l, k);
                    }
                }

                majorGrid.add(minorGrid, j, i);
            }
        }

        return majorGrid;
    }

    public StackPane createPanel(Cell cell) {
        StackPane panel = new StackPane();
        panel.setAlignment(Pos.CENTER);
        panel.setPrefSize(40, 40);
        panel.setPadding(new Insets(1, 1, 1, 1));
        panel.setUserData(cell);

        int value = cell.getValue();

        if (value == 0) {
            TextField input = new TextField("");
            input.setAlignment(Pos.CENTER);
            input.setPrefSize(40, 40);

            UnaryOperator<TextFormatter.Change> filter = change -> {
                String newValue = change.getControlNewText();
                int newLegth = newValue.length();
                int oldLength = change.getControlText().length();

                // handle backspace
                if (newLegth < oldLength) {
                    cell.resetCellValue();
                    return change;
                }

                if (Pattern.compile("[1-9]{1}").matcher(newValue).matches()) {
                    // for some reason eventlistener gets fired for every field containing text when 
                    // another field is focused so this is a workaround
                    if (cell.getValue() == 0) {
                        if (board.setValue(cell, Integer.parseInt(newValue))) {
                            System.out.println("Value has been set: " + cell.toString());

                        } else {
                            // fancy fading effect to notify user that current value cannot be set to field
                            // todo: refactor to dedicated method and make it red
                            FadeTransition ft = new FadeTransition(Duration.millis(500), panel);
                            ft.setFromValue(1.0);
                            ft.setToValue(0.5);
                            ft.setCycleCount(2);
                            ft.setAutoReverse(true);
                            ft.play();

                            return null;
                        }
                    }

                    return change;
                } else {
                    return null;
                }
            };

            input.setTextFormatter(new TextFormatter<>(filter));
            input.textProperty().addListener((obv, oldValue, newValue) -> {
                input.setText(newValue);
                if (board.isFinished()) {
                    System.out.println("Baord full, end game");
                    System.out.println(panel.getParent());
                }
            });
            
            panel.getChildren().add(input);
        } else {
            panel.getChildren().add(new Label(String.valueOf(cell.getValue())));
            panel.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, new Insets(1, 1, 1, 1))));
        }

        return panel;
    }

    @Override
    public void stop() {
        System.out.println("stage closed");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
