package fr.yourem.anothergame.entities.ennemies;

import fr.yourem.anothergame.entities.players.Player;
import fr.yourem.anothergame.floors.Floor;
import fr.yourem.anothergame.floors.rooms.Room;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum EnnemyType {
    ARCHER, GARDIAN, DAMAGER, BOSS;
    public EnnemyBuilder getEnnemies(){
        switch (this){
            case ARCHER :  return (EnnemyBuilder) new EnnemyBuilder().setType(this).setDamage(4).setAttackSpeed(3).setPvMax(15).setSpeed(0.5);
            case GARDIAN:  return (EnnemyBuilder) new EnnemyBuilder().setType(this).setDamage(3).setAttackSpeed(1).setPvMax(50).setSpeed(0.2);
            case DAMAGER:  return (EnnemyBuilder) new EnnemyBuilder().setType(this).setDamage(7).setAttackSpeed(2).setPvMax(25).setSpeed(0.3);
            default: return new EnnemyBuilder();
        }
    }

    static void move(Ennemy ennemy, Player player, Floor floor, Room r){
        switch (ennemy.getType()){
            default :
                double[] pos = ennemy.getPosition();
                double[] distance = new double[] {(player.getPosition()[0]-pos[0]), (player.getPosition()[1]-pos[1])};
                double maxDist = Math.max(Math.abs(distance[0]), Math.abs(distance[1]));
                double[] newPos = new double[]{(pos[0]+((double)distance[0]/(double)maxDist)*ennemy.getSpeed()), (pos[1]+((double)distance[1]/(double)maxDist)*ennemy.getSpeed())};
                if (!ennemy.getCanMoveInDirection()[0][distance[0]<0?0:1]){
                    newPos[0]=pos[0];
                }
                if (!ennemy.getCanMoveInDirection()[1][distance[1]<0?0:1]){
                    newPos[1]=pos[1];
                }
                ennemy.setPosition(newPos);
        }
    }
    static EnnemyType[] AllEnnemies(){
        return new EnnemyType[] {ARCHER, GARDIAN, DAMAGER};
    }
    public static EnnemyType generate(){
        Random r = new Random();
        double freq = r.nextDouble(0,1);
        double sum = 0;
        for (Map.Entry<EnnemyType, Double> entry: allDropsFreq().entrySet()){
            if (freq > sum && freq<entry.getValue()+sum)
                return entry.getKey();
            sum+= entry.getValue();
        }
        return null;
    }
    static Map<EnnemyType, Double> allDropsFreq(){
        Map<EnnemyType, Double> allDropsFreq = new HashMap<>();
        for (EnnemyType ennemy: AllEnnemies()){
            allDropsFreq.put(ennemy, ennemy.getDropFreq());
        }
        return allDropsFreq;
    }

    public double getDropFreq(){
        switch (this){
            case ARCHER : return 0.3;
            case GARDIAN: return 0.2;
            case DAMAGER: return 0.5;
            default: return 0;
        }
    }
    public Image getSprite(){
        switch (this){
            default: return new Image("/ennemy.png");
        }
    }
}
