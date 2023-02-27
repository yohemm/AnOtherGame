package fr.yourem.anothergame.entities.players;

import javafx.scene.image.Image;

public enum PlayerType {
    FIRSTPLAYER,WOODBODY;

    public Image getSprite(){
        switch (this) {
            default:
                return new Image("/Player.png");
        }

    }
    public PlayerBuilder getPlayers(){
        PlayerBuilder playerBuilder = new PlayerBuilder().setType(this);
        switch (this){
            case WOODBODY :
                return (PlayerBuilder) playerBuilder.setDoge(1).setControl(10).setSpeed(15).setPosition(new double[]{0,0});
            default:
                return (PlayerBuilder) playerBuilder;
        }
    }
}
