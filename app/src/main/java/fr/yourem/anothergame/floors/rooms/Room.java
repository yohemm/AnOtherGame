package fr.yourem.anothergame.floors.rooms;

import fr.yourem.anothergame.entities.Entity;
import fr.yourem.anothergame.entities.ennemies.Ennemy;
import fr.yourem.anothergame.entities.players.Player;
import fr.yourem.anothergame.floors.Floor;
import fr.yourem.anothergame.floors.rooms.structures.Structure;
import fr.yourem.anothergame.floors.themes.Theme;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.lang.reflect.Type;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    static final int maxDifficulty = 1000;
    static final int maxEnnemies = 8;
    private RoomType type;
    private Structure structure = null;
    private int[] sizes;
    private int timeToSurvive = 10;
    private ArrayList<Ennemy> ennemies = new ArrayList<>();

    public Room(RoomType type, Structure structure, int[] sizes, int timeToSurvive, ArrayList<Ennemy> ennemies) {
        this.type = type;
        this.structure = structure;
        this.sizes = sizes;
        this.timeToSurvive = timeToSurvive;
        this.ennemies = ennemies;
    }

    public void ennemiesUpdate(List<ImageView> ennemiesIv,Player player, Floor floor){
        for (int i = 0; i < ennemies.size(); i++){
            Ennemy ennemy = ennemies.get(i);
            Entity.collision(ennemy, player);
            ennemy.move(player,floor, this);
            ennemiesIv.get(i).setY(ennemy.getPosition()[0]);
            ennemiesIv.get(i).setX(ennemy.getPosition()[1]);

        }

    }


    public RoomType getType() {
        return type;
    }

    public Structure getStructure() {
        return structure;
    }

    public int[] getSizes() {
        return sizes;
    }

    public int getTimeToSurvive() {
        return timeToSurvive;
    }

    public ArrayList<Ennemy> getEnnemies() {
        return ennemies;
    }
    public Panel getPanel(Floor floor){
        Panel panel = new Panel();
        Image floorImage = floor.getTheme().getFloor();
        Image doorsImage = new Image("/door.png");

        Room[] doors = floor.getDoors(this);
        if (doors[0] != null){
            ImageView iVdoor = new ImageView(doorsImage);
            iVdoor.setY(-doorsImage.getHeight());
            iVdoor.setX((sizes[1]*floorImage.getHeight())/2 - doorsImage.getHeight()/2);
            panel.getChildren().add(iVdoor);
        }
        if (doors[1] != null){
            ImageView iVdoor = new ImageView(doorsImage);
            iVdoor.setY(sizes[0]*floorImage.getHeight());
            iVdoor.setX((sizes[1]*floorImage.getHeight())/2 - doorsImage.getHeight()/2);
            panel.getChildren().add(iVdoor);
        }
        if (doors[2] != null){
            ImageView iVdoor = new ImageView(doorsImage);
            iVdoor.setY((sizes[0]*floorImage.getHeight())/2 - doorsImage.getHeight()/2);
            iVdoor.setX(-doorsImage.getWidth());
            panel.getChildren().add(iVdoor);
        }
        if (doors[3] != null){
            ImageView iVdoor = new ImageView(doorsImage);
            iVdoor.setY((sizes[0]*floorImage.getHeight())/2 - doorsImage.getHeight()/2);
            iVdoor.setX(sizes[1]*floorImage.getWidth());
            panel.getChildren().add(iVdoor);
        }
        for (int j = 0; j < sizes[0]; j++)
            for (int i = 0; i < sizes[1]; i++){
                ImageView iV = new ImageView(floorImage);
                iV.setY(j*floorImage.getHeight());
                iV.setX(i*floorImage.getWidth());
                panel.getChildren().add(iV);
            }
        return panel;
    }

    public Room verifyTakeDoors(Player player, Floor floor){
        Room[] doors = floor.getDoors(this);
        Room door = null;
        if(doors[0] != null && player.getPosition()[0] < 0 && player.getPosition()[1]> (sizes[1]*32)/2-32 && player.getPosition()[1]< (sizes[1]*32)/2+32){
            door = doors[0];
            player.setPosition(new double[]{(door.sizes[0]-1)*32,(door.sizes[1]*32/2)-16 });
        }else if(doors[1]!= null && player.getPosition()[0]>(sizes[0]-1)*32 && player.getPosition()[1]> (sizes[1]*32)/2-32 && player.getPosition()[1]< (sizes[1]*32)/2+32){
            door = doors[1];
            player.setPosition(new double[]{0, door.sizes[1]*32/2-16});
        }

        if(doors[2]!= null && player.getPosition()[1]<0 && player.getPosition()[0]> (sizes[0]*32)/2-32 && player.getPosition()[0]< (sizes[0]*32)/2+32){
            door = doors[2];
            player.setPosition(new double[]{(door.sizes[0]*32/2)-16, (door.sizes[1]-1)*32});
        }else if(doors[3]!= null && player.getPosition()[1]>(sizes[1]-1)*32 && player.getPosition()[0]> (sizes[0]*32)/2-32 && player.getPosition()[0]< (sizes[0]*32)/2+32){
            door = doors[3];
            player.setPosition(new double[]{(door.sizes[0]*32/2)-16, 0});
        }
        if (door != null){
            player.setVelocity(new double[] {0,0});
        }
        return door;
    }
    public void setCanMove(Entity entity ) {
        boolean[][] canMove = entity.getCanMoveInDirection();
        double[] pos = entity.getPosition();
        if (entity.getPosition()[0]<0){
            pos[0]= 0;
            canMove[0][0] = false;
        } else if (entity.getPosition()[0]>(sizes[0]-1)*32){
            pos[0] = (sizes[0]-1)*32;
            canMove[0][1] = false;
        }
        if (entity.getPosition()[1]<0){
            pos[1] = 0;
            canMove[1][0] = false;
        } else if (entity.getPosition()[1]>(sizes[1]-1)*32){
            pos[1] = (sizes[1]-1)*32;
            canMove[1][1] = false;
        }
    }
}
