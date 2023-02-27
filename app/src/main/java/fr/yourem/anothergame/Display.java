package fr.yourem.anothergame;

import fr.yourem.anothergame.entities.Entity;
import fr.yourem.anothergame.entities.ennemies.Ennemy;
import fr.yourem.anothergame.entities.ennemies.boss.Boss;
import fr.yourem.anothergame.entities.players.Player;
import fr.yourem.anothergame.floors.Floor;
import fr.yourem.anothergame.floors.rooms.Room;
import fr.yourem.anothergame.floors.rooms.structures.Chest;
import fr.yourem.anothergame.floors.rooms.structures.Stair;
import fr.yourem.anothergame.floors.rooms.structures.Structure;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.Proxy;

public class Display {
    private static double RATIO = 16d/9d;
    private static Floor floor;
    public static void configurationRoom(Floor f, Room currentRoom, Scene s, Group g){
        floor = f;

    }

    public static ImageView getSprite(Positioned positioned){
        if (positioned instanceof Entity){
            if (positioned instanceof Player){

            } else if (positioned instanceof Ennemy){
                if (positioned instanceof Boss){

                }

            }else{

            }

        }else if (positioned instanceof Structure){
            if (positioned instanceof Stair){

            } else if (positioned instanceof Chest){

            }else{

            }
        }else {

        }
        return new ImageView(new Image("aa.png"));
    }


    public static void configure(ImageView iV){

    }
}
