package fr.yourem.anothergame.floors.rooms.structures;

import fr.yourem.anothergame.Positioned;
import javafx.scene.image.Image;

public class Structure extends Positioned {
    protected boolean isUsed = false;
    protected Image image;

    public Structure(double[] position, boolean isUsed, Image image) {
        this.position = position;
        this.isUsed = isUsed;
        this.image = image;
    }

    public void setPosition(int x, int y){
        position[1] = x;
        position[0] = y;
    }
    public void use(){
        isUsed = !isUsed;
    }

    public Image getImage() {
        return image;
    }
}
