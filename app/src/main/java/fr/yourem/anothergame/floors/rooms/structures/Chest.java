package fr.yourem.anothergame.floors.rooms.structures;

import fr.yourem.anothergame.items.Item;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Chest extends Structure{
    private ArrayList<Item> inventory = new ArrayList<>();

    public Chest(double[] position, boolean isUsed, Image image, ArrayList<Item> inventory) {
        super(position, isUsed, image);
        this.inventory = inventory;
    }

    public boolean addItem(Item item){
        inventory.add(item);
        return true;
    }
}
