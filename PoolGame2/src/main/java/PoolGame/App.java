/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package PoolGame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import PoolGame.CheatStrategy.CheatContext;
import PoolGame.CheatStrategy.CheatStrategy;
import PoolGame.CheatStrategy.*;
import PoolGame.Items.Pocket;
import PoolGame.Items.PoolTable;
import PoolGame.Momento.Caretaker;
import PoolGame.Momento.Memento;
import PoolGame.Momento.Originator;
import PoolGame.State.*;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import org.json.simple.parser.ParseException;

import PoolGame.ConfigReader.ConfigKeyMissingException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Duration;

import PoolGame.Items.Ball;

/** The JavaFX application */
public class App extends Application {

    private final double FRAMETIME = 1.0 / 60.0;
    private String path = "/config_easy.json";
    private Canvas canvas;
    private boolean flag;
    private Label time;
    private Label score;
    private Originator originalState;
    private Memento snapshot;
    private Caretaker storage;

    private ConfigReader loadConfig(List<String> args) {
        String configPath;
        boolean isResourcesDir = false;
		if (args.size() > 0) {
			configPath = args.get(0);
		} else {
			//configPath = "src/main/resources/config.json";
			configPath = path;
            isResourcesDir = true;
		}
		// parse the file:
        ConfigReader config = null;
        try {
            if (path.toLowerCase().startsWith("/config_")) {
                flag = false;
            }
            else if (path.toLowerCase().startsWith("/config.")){
                flag = true;
            }

            config = new ConfigReader(configPath, isResourcesDir, flag);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ConfigKeyMissingException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        }
        return config;
    }

    @Override
    public void start(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("PoolGame");
        
        ConfigReader config = loadConfig(getParameters().getRaw());
        Game game = new Game(config);

        canvas = new Canvas(game.getWindowDimX(), game.getWindowDimY());
        stage.show();

        time = new Label();
        time.setText("Timer: 00:00");
        time.setTextFill(Color.BLACK);
        time.setTranslateX(game.getWindowDimX() + 25);
        time.setTranslateY(game.getWindowDimY()/2);

        score = new Label();
        score.setText("Score: " + game.getPoolTable().getScore());
        score.setTextFill(Color.BLACK);
        score.setTranslateX(game.getWindowDimX() + 25);
        score.setTranslateY((game.getWindowDimY()/2) + 15);

        this.setKeyEvents(root, scene, game, stage);

        stage.setWidth(game.getWindowDimX() + 150);
        stage.setHeight(game.getWindowDimY() +
                        Pocket.RADIUS +
                        PoolTable.POCKET_OFFSET +
                        12); // Magic number to get bottom to align

        root.getChildren().add(canvas);
        root.getChildren().add(time);
        root.getChildren().add(score);
        // GraphicsContext gc = canvas.getGraphicsContext2D();
        game.addDrawables(root);
        
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(FRAMETIME),
        (actionEvent) -> {
                game.tick();

            });

        timeline.getKeyFrames().add(frame);
        timeline.play();

        this.timer(time, frame, game);
        this.showScore(game, frame);
    }

    /**
     * Trigger events when certain keys are pressed
     * @param root
     * @param scene
     * @param game
     * @param stage
     */
    public void setKeyEvents(Group root, Scene scene, Game game, Stage stage){
        scene.setOnKeyPressed(event -> {

            CheatContext cheatContext = new CheatContext();

            switch (event.getCode()){
                case Q:
                    DifficultyState easy = new EasyState();
                    this.reset(root, game, easy, stage);
                    System.out.println("Difficulty: Easy");
                    break;
                case W:
                    DifficultyState normal = new NormalState();
                    this.reset(root, game, normal, stage);
                    System.out.println("Difficulty: Normal");
                    break;
                case E:
                    DifficultyState hard = new HardState();
                    this.reset(root, game, hard, stage);
                    System.out.println("Difficulty: Hard");
                    break;
                case A:
                    DifficultyState original = new OriginalState();
                    this.reset(root, game, original, stage);
                    System.out.println("Original Level");
                    break;
                case R:
                    this.saveSnapshot(game);
                    originalState.recoverState(storage.getMemento());
                    break;
                case Z:
                    CheatStrategy red = new redCheat();
                    cheatContext.setCheatStrategy(red);
                    cheatContext.executeCheatStrategy(game.getPoolTable());
                    break;
                case X:
                    CheatStrategy yellow = new yellowCheat();
                    cheatContext.setCheatStrategy(yellow);
                    cheatContext.executeCheatStrategy(game.getPoolTable());
                    break;
                case C:
                    CheatStrategy green = new greenCheat();
                    cheatContext.setCheatStrategy(green);
                    cheatContext.executeCheatStrategy(game.getPoolTable());
                    break;
                case V:
                    CheatStrategy brown = new brownCheat();
                    cheatContext.setCheatStrategy(brown);
                    cheatContext.executeCheatStrategy(game.getPoolTable());
                    break;
                case B:
                    CheatStrategy blue = new blueCheat();
                    cheatContext.setCheatStrategy(blue);
                    cheatContext.executeCheatStrategy(game.getPoolTable());
                    break;
                case N:
                    CheatStrategy purple = new purpleCheat();
                    cheatContext.setCheatStrategy(purple);
                    cheatContext.executeCheatStrategy(game.getPoolTable());
                    break;
                case M:
                    CheatStrategy black = new blackCheat();
                    cheatContext.setCheatStrategy(black);
                    cheatContext.executeCheatStrategy(game.getPoolTable());
                    break;
                case K:
                    CheatStrategy orange = new orangeCheat();
                    cheatContext.setCheatStrategy(orange);
                    cheatContext.executeCheatStrategy(game.getPoolTable());
                    break;
            }
        });
    }

    /**
     * Save the current state of the game
     * @param game
     */
    public void saveSnapshot(Game game){
        originalState = new Originator();
        storage = new Caretaker();
        snapshot = originalState.saveState(game.getPoolTable());
        storage.setMemento(snapshot);
    }
    public Originator getOriginalState(){
        return this.originalState;
    }
    public Memento getSnapshot(){
        return snapshot;
    }

    /**
     * Returns the game state to previously saved state
     * @param game
     * @param root
     * @param stage
     * @return
     */
    public Canvas undo(Game game, Group root, Stage stage){
        originalState.recoverState(snapshot);
        System.out.println(snapshot.getSecond());
        game.getPoolTable().setSecond(snapshot.getSecond());
        game.getPoolTable().setMinute(snapshot.getMinute());
        time.setText("Timer: " + snapshot.getMinute() + ":" + snapshot.getSecond());
        score.setText("Score: " + snapshot.getScore());
        return canvas;
    }

    /**
     * Redraws the canvas to change difficulty state
     * @param root
     * @param game
     * @param state
     * @param stage
     * @return
     */
    public Canvas reset(Group root, Game game, DifficultyState state, Stage stage){
        DifficultyChanger changer = new DifficultyChanger();
        changer.setState(state);
        path = changer.getState();
        root.getChildren().remove(canvas);
        game.removeDrawables(root);
        ConfigReader newConf = loadConfig(getParameters().getRaw());
        game.reload(newConf);
        canvas = new Canvas(game.getWindowDimX(), game.getWindowDimY());
        game.addDrawables(root);
        stage.setWidth(game.getWindowDimX() + 150);
        stage.setHeight(game.getWindowDimY() +
                Pocket.RADIUS +
                PoolTable.POCKET_OFFSET +
                12);
        game.getPoolTable().resetTimer();
        time.setText("Timer: 00:00");
        game.getPoolTable().setScore(0);
        score.setText("Score: 0");
        game.reset();
        return canvas;
    }

    /**
     * Shows a timer for the game
     * @param label
     * @param frame
     * @param game
     */
    void timer(Label label, KeyFrame frame, Game game){

        Timeline timelineTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int second = game.getPoolTable().getSecond();
            int minute = game.getPoolTable().getMinute();

            if (second <= 58){
                if (second <= 8){
                    if (minute <= 8){
                        label.setText("Timer: 0" + minute + ":0" + ++second);
                    }
                    else {
                        label.setText("Timer: " + minute + ":0" + ++second);
                    }
                }
                else{
                    if (minute <= 8){
                        label.setText("Timer: 0" + minute + ":" + ++second);
                    }
                    else {
                        label.setText("Timer: " + minute + ":" + ++second);
                    }
                }
                if (!game.getPoolTable().hasWon()){
                    game.getPoolTable().setSecond(second);
                }
            }
            else {
                if (second <= 8){
                    if (minute <= 8){
                        second = 0;
                        label.setText("Timer: 0" + ++minute + ":0" + second);
                    }
                    else {
                        second = 0;
                        label.setText("Timer: " + ++minute + ":0" + second);
                    }
                }
                else{
                    if (minute <= 8){
                        second = 0;
                        label.setText("Timer: 0" + ++minute + ":0" + second);
                    }
                    else {
                        second = 0;
                        label.setText("Timer: " + ++minute + ":" + second);
                    }
                }
                if (!game.getPoolTable().hasWon()){
                    game.getPoolTable().setMinute(minute);
                    game.getPoolTable().setSecond(0);
                }
            }
        }));
        timelineTimer.setCycleCount(timelineTimer.INDEFINITE);
        timelineTimer.getKeyFrames().add(frame);
        timelineTimer.play();
    }

    /**
     * Shows the score for the game
     * @param game
     * @param frame
     */
    public void showScore(Game game, KeyFrame frame){

        Timeline timelineScore = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            score.setText("Score: " + game.getPoolTable().getScore());
        }));

        timelineScore.setCycleCount(Timeline.INDEFINITE);
        timelineScore.getKeyFrames().add(frame);
        timelineScore.play();
    }

    /**
     * The entry point of the program
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
