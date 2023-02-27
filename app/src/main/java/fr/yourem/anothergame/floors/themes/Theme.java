package fr.yourem.anothergame.floors.themes;

import fr.yourem.anothergame.entities.players.Player;
import javafx.scene.image.Image;

public enum Theme {
    JUNGLE, UNDERTOWN, MOUNTAINE;

    public Image getFloor(){
        switch (this){
            default : return new Image("floorCase.png");
        }
    }

    public Theme[] getNextThemes(){

        switch (this){
            case JUNGLE :
                return new Theme[]{UNDERTOWN, JUNGLE};
            default:
                return  new  Theme[]{JUNGLE,UNDERTOWN};
        }
    }
    public double getNaturalDiffculty (){

        switch (this){
            case JUNGLE :
                return 1.5;
            case  MOUNTAINE:
                return 0.8;
            default:
                return  1;
        }
    }
}
