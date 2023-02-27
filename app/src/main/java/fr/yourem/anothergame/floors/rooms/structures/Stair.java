package fr.yourem.anothergame.floors.rooms.structures;

import fr.yourem.anothergame.items.Item;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Stair extends Structure{
    private int deep = 1;

    public Stair(double[] position, boolean isUsed, Image image) {
        super(position, isUsed, image);
    }
    public Stair(double[] position, boolean isUsed, Image image, int deep) {
        super(position, isUsed, image);
        this.deep = deep;
    }
}
