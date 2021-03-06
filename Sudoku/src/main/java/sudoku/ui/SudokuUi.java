package sudoku.ui;

import java.util.List;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import sudoku.domain.Board;
import sudoku.domain.Cell;
import sudoku.domain.Score;
import sudoku.logics.ScoreService;

/**
 *
 * @author hoffrenm
 */
public class SudokuUi extends Application {

    private final Board board;
    private final BorderPane root;
    private final Timer timer;
    private final ScoreService scoreService;

    public SudokuUi() {
        root = new BorderPane();
        this.board = new Board();
        this.timer = new Timer();
        this.scoreService = new ScoreService("scores.txt");
        board.newSudoku(0);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox diff = new HBox();
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
        boardGrid.setId("playboard");

        scoresBtn.setOnAction((event) -> {
            root.setCenter(scorePanel(scoreService.getScores()));
        });

        playBtn.setOnAction((event) -> {
            buttons.getChildren().remove(playBtn);
            buttons.getChildren().add(0, diff);
        });

        diff.setSpacing(3);
        diff.setAlignment(Pos.CENTER);
        Button easy = new Button("Easy");
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");

        easy.setMinWidth(diff.getPrefWidth());
        medium.setMinWidth(diff.getPrefWidth());
        hard.setMinWidth(diff.getPrefWidth());

        diff.getChildren().addAll(easy, medium, hard);

        easy.setOnAction(e -> {
            buttons.getChildren().remove(diff);
            buttons.getChildren().add(0, playBtn);
            board.newSudoku(100);
            timer.start("easy");
            root.setCenter(initialize(board));
        });
        medium.setOnAction(e -> {
            buttons.getChildren().remove(diff);
            buttons.getChildren().add(0, playBtn);
            board.newSudoku(50);
            timer.start("medium");
            root.setCenter(initialize(board));
        });
        hard.setOnAction(e -> {
            buttons.getChildren().remove(diff);
            buttons.getChildren().add(0, playBtn);
            board.newSudoku(35);
            timer.start("hard");
            root.setCenter(initialize(board));
        });

        HBox top = new HBox();
        top.setPadding(new Insets(20, 40, 0, 40));
        top.setSpacing(10);
        top.getChildren().add(timer.getTimer());

        exitBtn.setOnAction((event) -> {
            stage.close();
        });

        root.setTop(top);
        root.setBottom(buttons);
        root.setCenter(boardGrid);
        BorderPane.setAlignment(boardGrid, Pos.CENTER);
        BorderPane.setAlignment(root.getTop(), Pos.TOP_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.show();
    }

    public GridPane initialize(Board board) {
        GridPane majorGrid = new GridPane();
        majorGrid.setAlignment(Pos.CENTER);
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
        panel.setPrefSize(45, 45);
        panel.setPadding(new Insets(1, 1, 1, 1));

        int value = cell.getValue();

        if (value == 0) {
            TextField input = new TextField("");
            input.setAlignment(Pos.CENTER);
            input.setFont(new Font("Arial", 20));
            input.setPrefSize(45, 45);

            UnaryOperator<TextFormatter.Change> filter = change -> {
                String newValue = change.getControlNewText();
                int newLegth = newValue.length();
                int oldLength = change.getControlText().length();

                if (newLegth < oldLength) {
                    cell.resetCellValue();
                    return change;
                }

                if (newLegth == oldLength) {
                    return null;
                }

                if (Pattern.compile("[1-9]{1}").matcher(newValue).matches()) {
                    if (cell.getValue() == 0) {
                        if (board.setValue(cell, Integer.parseInt(newValue))) {
                        } else {
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
                    timer.stop();
                    StackPane box = new StackPane();
                    box.setAlignment(Pos.CENTER);
                    VBox end = new VBox();
                    Label label1 = new Label("Congratulations, Sudoku successfully solved!\n"
                            + timer.getTimer().getText() + " \n"
                            + "Difficulty: " + timer.getDifficulty());
                    label1.setTextFill(Color.WHITESMOKE);
                    label1.setFont(new Font("Arial", 18));
                    Button submit = new Button("submit");

                    Label info = new Label("Enter your name to save result");
                    info.setTextFill(Color.WHITESMOKE);
                    info.setFont(new Font("Arial", 12));
                    TextField name = new TextField();

                    submit.setOnAction(event -> {
                        scoreService.addResult(name.getText(), timer.getTime(), timer.getDifficulty());
                        board.newSudoku(0);
                        root.setCenter(initialize(board));
                    });

                    end.getChildren().addAll(label1, info, name, submit);
                    end.setPadding(new Insets(10, 10, 10, 10));
                    end.setSpacing(10);
                    end.setAlignment(Pos.CENTER);
                    box.getChildren().add(end);

                    root.setCenter(box);
                }
            });

            panel.getChildren().add(input);
        } else {
            Label num = new Label(String.valueOf(cell.getValue()));
            num.setFont(new Font("Arial", 20));
            panel.getChildren().add(num);
            panel.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, new Insets(1, 1, 1, 1))));
        }

        return panel;
    }

    public BorderPane scorePanel(List<Score> scores) {
        BorderPane scoreBoard = new BorderPane();
        scoreBoard.setPrefSize(150, 150);

        Label name = new Label("Name");
        name.setFont(new Font("Arial", 32));
        Label time = new Label("Time");
        time.setFont(new Font("Arial", 32));
        Label diff = new Label("Difficulty");
        diff.setFont(new Font("Arial", 32));

        HBox stats = new HBox();
        stats.setPadding(new Insets(10, 10, 10, 10));
        stats.setSpacing(50);

        VBox names = new VBox();
        VBox times = new VBox();
        VBox levels = new VBox();

        names.getChildren().add(name);
        times.getChildren().add(time);
        levels.getChildren().add(diff);

        for (Score score : scores) {
            names.getChildren().add(new Label(score.getName()));
            times.getChildren().add(new Label(score.getTime()));
            levels.getChildren().add(new Label(score.getLevel()));
        }

        stats.getChildren().addAll(names, times, levels);
        stats.setAlignment(Pos.CENTER);

        scoreBoard.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(15), new Insets(1, 1, 1, 1))));
        scoreBoard.setCenter(stats);

        return scoreBoard;
    }

    @Override
    public void stop() throws Exception {
        scoreService.saveScores();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
