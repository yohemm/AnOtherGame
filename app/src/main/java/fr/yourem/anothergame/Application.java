package fr.yourem.anothergame;

import fr.yourem.anothergame.entities.ennemies.Ennemy;
import fr.yourem.anothergame.entities.players.Player;
import fr.yourem.anothergame.entities.players.PlayerBuilder;
import fr.yourem.anothergame.entities.players.PlayerType;
import fr.yourem.anothergame.floors.Floor;
import fr.yourem.anothergame.floors.rooms.Room;
import fr.yourem.anothergame.floors.themes.Theme;
import fr.yourem.anothergame.items.gadjets.Gadjet;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.util.ArrayList;
import java.util.List;

public class Application extends javafx.application.Application {
    private static List<PlayerBuilder> PlayerUnlock = new ArrayList<>();
    private static Player player = PlayerType.WOODBODY.getPlayers().createPlayer();
    private static int maxGadjets = 5;
    private static Gadjet[] gadjets = new Gadjet[maxGadjets];
    private static int difficulty = 1;
    private List<KeyCode> pressed = new ArrayList<>();
    private Floor floor = new Floor(0, Theme.JUNGLE,1,10);
    private Room currentRoom = floor.getSpawn();
    private Panel panel= new Panel();
    private Panel roomPanel;
    private Panel playerPanel = new Panel();
    Panel ennemiesPanel = new Panel();

    private List<ImageView> ennemyIVs = new ArrayList<>();

    public void setRoom(Room r){
        currentRoom = r;
       if (roomPanel != null) {
            panel.getChildren().remove(playerPanel);
            panel.getChildren().remove(roomPanel);
            panel.getChildren().remove(ennemiesPanel);
            ennemiesPanel.getChildren().clear();
        }
        roomPanel = r.getPanel(floor);
        panel.getChildren().add(roomPanel);
        panel.getChildren().add(playerPanel);
        panel.getChildren().add(ennemiesPanel);

        ennemyIVs.clear();
        for (Ennemy ennemy : currentRoom.getEnnemies()){
            ImageView iv =new ImageView(ennemy.getType().getSprite());
            ennemyIVs.add(iv);
            ennemiesPanel.getChildren().add(iv);
        }
    }
    @Override
    public void start(Stage stage) {
        try {
            floor.printMap();
            FlowPane root = new FlowPane();
            root.setPadding(new Insets(20));
            Scene scene = new Scene(root, 500, 500);
            ImageView iV = new ImageView(player.getType().getSprite());
            playerPanel.getChildren().add(iV);
            setRoom(currentRoom);
            root.getChildren().add(panel);
            stage.setTitle("AnOtherGame");

            AnimationTimer boucle = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    player.update();
                    currentRoom.ennemiesUpdate(ennemyIVs, player, floor);
                    player.setDirection(new int[]{
                            (!pressed.contains(KeyCode.UP) && !pressed.contains(KeyCode.DOWN))||(pressed.contains(KeyCode.UP) && pressed.contains(KeyCode.DOWN)) ? 0 : (pressed.contains(KeyCode.DOWN) ?1:-1),
                            (!pressed.contains(KeyCode.LEFT) && !pressed.contains(KeyCode.RIGHT))||(pressed.contains(KeyCode.LEFT) && pressed.contains(KeyCode.RIGHT)) ? 0 : (pressed.contains(KeyCode.RIGHT) ?1:-1)
                    });
                    Room potentialRoom = currentRoom.verifyTakeDoors(player, floor);
                    if (potentialRoom != null){
                        setRoom(potentialRoom);
                    }
                    currentRoom.setCanMove(player);
                    player.move();
                    iV.setY(player.getPosition()[0]);
                    iV.setX(player.getPosition()[1]);

                }
            };
            boucle.start();
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()){
                    case LEFT :
                    case Q:
                        if (!pressed.contains(KeyCode.LEFT))
                            pressed.add(KeyCode.LEFT);
                        break;
                    case RIGHT:
                    case D:
                        if (!pressed.contains(KeyCode.RIGHT))
                            pressed.add(KeyCode.RIGHT);
                        break;
                    case UP:
                    case Z:
                        if (!pressed.contains(KeyCode.UP))
                            pressed.add(KeyCode.UP);
                        break;
                    case DOWN:
                    case S:
                        if (!pressed.contains(KeyCode.DOWN))
                            pressed.add(KeyCode.DOWN);
                        break;
                    default:
                        break;
                }
            });
            scene.setOnKeyReleased(e -> {
                switch (e.getCode()){
                    case LEFT :
                    case Q:
                        pressed.remove(KeyCode.LEFT);
                        break;
                    case RIGHT:
                    case D:
                        pressed.remove(KeyCode.RIGHT);
                        break;
                    case UP:
                    case Z:
                        pressed.remove(KeyCode.UP);
                        break;
                    case DOWN:
                    case S:
                        pressed.remove(KeyCode.DOWN);
                        break;
                    default:
                        break;
                }
            });

            scene.setFill(Color.BLACK);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}