package fr.yourem.anothergame.items.gadjets;

import fr.yourem.anothergame.items.Item;

import java.security.Timestamp;

public class Gadjet extends Item {
    private long couldown, timer = 0;
    private boolean isActived;
    public Gadjet(String name, String description, int couldown) {
        super(name, description);
        this.couldown = couldown;
        isActived = false;
    }

    public void active(){
        if(isActived && timer + couldown * 1000 < System.currentTimeMillis()){
            isActived = false;
        }
        if (!isActived)
            if (timer + couldown * 1000 < System.currentTimeMillis()){
                timer = System.currentTimeMillis() + couldown*1000;
                isActived = true;
            }
    }
}
