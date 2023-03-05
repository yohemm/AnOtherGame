package fr.yourem.anothergame.floors.rooms;

import fr.yourem.anothergame.entities.ennemies.EnnemyType;
import fr.yourem.anothergame.entities.ennemies.Ennemy;
import fr.yourem.anothergame.entities.ennemies.EnnemyBuilder;
import fr.yourem.anothergame.floors.Floor;
import fr.yourem.anothergame.floors.rooms.structures.Chest;
import fr.yourem.anothergame.floors.rooms.structures.Stair;
import fr.yourem.anothergame.floors.rooms.structures.Structure;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class RoomBuilder {
    static final int maxSize = 16;
    static final int minSize = 8;
    private static long lastSeed;
    private Structure structure;

    private int[] sizes = {10,10};
    private int timeToSurvive = 10;
    private RoomType type;
    private ArrayList<Ennemy> ennemies;
    public RoomBuilder(Long seed){
        sizes[0] = 8;
        sizes[1] = 8;
        RoomBuilder.lastSeed = seed;
    }
    private void setSize(int axe){
        Random SeedR = new Random(lastSeed);
        RoomBuilder.lastSeed ++;
        sizes[axe] = SeedR.nextInt(minSize,maxSize);
        if (sizes[axe]%2==0)
            sizes[axe] = sizes[axe]>maxSize?sizes[axe]-1:sizes[axe]+1;
    }
    public RoomBuilder(){
        setSize(0);
        setSize(1);

    }
    public RoomBuilder setType(RoomType type) {
        this.type = type;
        return this;
    }

    public RoomBuilder setStructure(Structure structure) {
        this.structure = structure;
        return this;
    }

    public RoomBuilder setTimeToSurvive(int timeToSurvive) {
        this.timeToSurvive = timeToSurvive;
        return this;
    }

    public RoomBuilder setEnnemies(ArrayList<Ennemy> ennemies) {
        this.ennemies = ennemies;
        return this;
    }
    public Room createRoom(Floor floor) {
        Random random = new Random();
        if (ennemies == null){
            ennemies=new ArrayList<>();
        }
        if (timeToSurvive < 5){
            timeToSurvive = 10;
        }
        switch (type){
            case BOSS:
                if (timeToSurvive < 20){
                    timeToSurvive = 20;
                }
            case SPAWN:
                setTimeToSurvive(0);
            case CHEST :
                setStructure(new Chest(new double[] {sizes[0]/2,sizes[1]/2},false, new Image("bazin.jpg"), new ArrayList<>()));
                break;
            case NEXT_FLOOR:
                if (!ennemies.isEmpty()){
                    ennemies = new ArrayList<>();
                }
                if (random.nextInt(10) == 0)
                    setStructure(new Stair(new double[] {sizes[0]/2,sizes[1]/2},false, new Image("bazin.jpg"),random.nextInt(1,5)));
                else
                    setStructure(new Stair(new double[] {sizes[0]/2,sizes[1]/2},false, new Image("bazin.jpg")));
                break;
            case ENNEMIES:
                if (ennemies.isEmpty()){
                    ennemies = new ArrayList<>();
                    int nbennemies = (int)((double)floor.getDifficulty()/(double)50*random.nextInt(Room.maxEnnemies-4));
                    double[][] allPosEnnemy = new double[nbennemies+4][2];
                    for (int i = 0; i < 4+nbennemies;i++){
                        EnnemyBuilder ennemyBuilder = EnnemyType.generate().getEnnemies();
                        double[] pos;
                        boolean goodPos = false;
                        do{
                            pos = new double[] {random.nextInt((sizes[0]-1)*32),random.nextInt((sizes[1]-1)*32)};
                            goodPos = true;
                            for (double[] otherPos : allPosEnnemy){
                                if (Math.abs(otherPos[0] - pos[0]) < 32 && Math.abs(otherPos[0] - pos[0] )< 32) {
                                    goodPos = false;
                                    break;
                                }
                            }

                        }while (!goodPos);
                        allPosEnnemy[i] = pos;
                        ennemyBuilder.setPosition(pos);
                        ennemies.add(ennemyBuilder.createEnnemy());
                    }
                }
        }
        return new Room(type, structure, sizes, timeToSurvive, ennemies);
    }

    public RoomType getType() {
        return type;
    }
}