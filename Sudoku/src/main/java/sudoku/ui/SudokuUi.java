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
import javafx.scene.layout.HBox;
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

    private static final int HEIGHT = 550;
    private static final int WIDTH = 400;
    private final Board board;
    private final StackPane root;
    private final Timer clock;

    public SudokuUi() {
        root = new StackPane();
        this.board = new Board();
        this.clock = new Timer();
        board.newSudoku(81);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane layout = new BorderPane();
        VBox buttons = new VBox();
        buttons.setPrefWidth(100);
        buttons.setPadding(new Insets(20, 20, 40, 20));
        buttons.setSpacing(5);
        buttons.setAlignment(Pos.CENTER);

        Button playBtn = new Button("New game");
        Button scoresBtn = new Button("Scores");
        Button exitBtn = new Button("Exit");
        playBtn.setMinWidth(buttons.getPrefWidth());
        scoresBtn.setMinWidth(buttons.getPrefWidth());
        exitBtn.setMinWidth(buttons.getPrefWidth());

        buttons.getChildren().addAll(playBtn, scoresBtn, exitBtn);

        GridPane boardGrid = initialize(board);

        playBtn.setOnAction((event) -> {
            board.newSudoku(45);
            clock.reset();
            clock.start();
            layout.setCenter(initialize(board));
        });
        
        HBox top = new HBox();
        top.setPadding(new Insets(20, 10, 10, 10));
        top.setSpacing(10);
        top.getChildren().add(clock.getTimer());

        exitBtn.setOnAction((event) -> {
            stage.close();
        });
        
        layout.setTop(top);
        layout.setBottom(buttons);
        layout.setCenter(boardGrid);
        BorderPane.setAlignment(layout.getTop(), Pos.TOP_CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().add(layout);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.show();
    }

    public GridPane initialize(Board board) {
        GridPane majorGrid = new GridPane();
        majorGrid.setMinSize(0, 0);
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

                    root.getChildren().add(panel);

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
