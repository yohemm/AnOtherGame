package fr.yourem.anothergame.items.upgrader;

import fr.yourem.anothergame.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Upgrader extends Item {
    private Map<String, Integer> effects = new HashMap<>();
    public Upgrader(String name, String description) {
        super(name, description);
    }
    public Upgrader(String name, String description, Map<String, Integer> effects) {
        super(name, description);
        this.effects = effects;
    }
    public void addEffect(String name, int power){
        boolean find = false;
        for(Map.Entry<String, Integer> entry : effects.entrySet()){
            if (entry.getKey().equals(name)){
                entry.setValue(entry.getValue()+power);
                find = true;
            }
        }
        if (!find){
            effects.put(name, power);
        }
    }
}
