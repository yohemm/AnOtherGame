package fr.yourem.anothergame.entities;

import fr.yourem.anothergame.Positioned;
import fr.yourem.anothergame.entities.players.Player;
import fr.yourem.anothergame.floors.rooms.Room;
import javafx.scene.image.Image;

import java.util.Arrays;

public class Entity extends Positioned {
    protected double speed=10, pv;
    protected int pvMax;
    protected double[] position = new double[2];
    protected int[] size;

    public void update(Room room){
        canMoveInDirection = new boolean[][] {{true,true},{true,true}};
        room.setCanMove(this);
    }

    public double getSpeed() {
        return speed;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public Entity(double speed, double pv, int pvMax, double[] position, int[] size, boolean[][] canMoveInDirection) {
        this.speed = speed;
        this.pv = pv;
        this.pvMax = pvMax;
        this.position = position;
        this.size = size;
        this.canMoveInDirection = canMoveInDirection;
        this.image = image;
    }
    public Entity(Entity e){
        this.speed = e.speed;
        this.pv = e.pv;
        this.pvMax = e.pvMax;
        this.position = e.position;
        this.size = e.size;
        this.canMoveInDirection = e.canMoveInDirection;
        this.image = e.image;

    }
    public Entity(EntityBuilder entityBuilder){
        this(entityBuilder.createEntity());
    }

    //                                          top bottom  left    RIGHT
    protected boolean[][] canMoveInDirection = {{true,true},{true,true}};
    protected Image image;

    @Override
    public String toString() {
        return "Entity{" +
                "speed=" + speed +
                ", pv=" + pv +
                ", pvMax=" + pvMax +
                ", position=" + Arrays.toString(position) +
                ", size=" + Arrays.toString(size) +
                '}';
    }
    public void move(){
        return;
    }
    static public boolean[][] collision(Entity e1, Entity e2){
        /*                              up, down, left, right*/
        boolean[][] res = new  boolean[][] {{false, false}, {false, false}};
        // e1 <- e2
        if (e1.position[1] < e2.position[1] && e2.position[1] <  e1.position[1]+ e1.size[1] && Math.abs(e2.position[0] - e1.position[0])<e1.size[0]){
            e1.canMoveInDirection[1][1] = false;
            e2.canMoveInDirection[1][0] = false;
            res[1][0] = true;
        }
        // e1 -> e2
        if (e2.position[1] < e1.position[1] && e1.position[1] <  e2.position[1]+ e2.size[1] && Math.abs(e2.position[0] - e1.position[0])<e1.size[0]){
            e2.canMoveInDirection[1][1] = false;
            e1.canMoveInDirection[1][0] = false;
            res[1][1] = true;
        }
        // e1 |^ e2
        if (e1.position[0] < e2.position[0] && e2.position[0] <  e1.position[0]+ e1.size[0] && Math.abs(e2.position[1] - e1.position[1])<e1.size[1]){
            e1.canMoveInDirection[0][1] = false;
            e2.canMoveInDirection[0][0] = false;
            res[0][0] = true;
        }
        // e1 |v e2
        if (e2.position[0] < e1.position[0] && e1.position[0] <  e2.position[0]+ e2.size[0] && Math.abs(e2.position[1] - e1.position[1])<e1.size[1]){
            e2.canMoveInDirection[0][1] = false;
            e1.canMoveInDirection[0][0] = false;
            res[0][1] = true;
        }
        return res;
    }
    static public boolean[][] collision(Entity e1, Player e2){
        /*                              up, down, left, right*/
        boolean[][] res = new  boolean[][] {{false, false}, {false, false}};
        // e1 <- e2
        if (e1.position[1] < e2.position[1]+e2.getVelocity()[1]* e2.speed && e2.position[1] <  e1.position[1]+ e1.size[1]+e2.getVelocity()[1]* e2.speed && Math.abs(e2.position[0] - e1.position[0])<e1.size[0]*2){
            e1.canMoveInDirection[1][1] = false;
            e2.canMoveInDirection[1][0] = false;
            res[1][0] = true;
        }
        // e1 -> e2
        if (e2.position[1] < e1.position[1]+e2.getVelocity()[1]* e2.speed && e1.position[1] <  e2.position[1]+ e2.size[1]+e2.getVelocity()[1]* e2.speed && Math.abs(e2.position[0] - e1.position[0])<e1.size[0]*2){
            e2.canMoveInDirection[1][1] = false;
            e1.canMoveInDirection[1][0] = false;
            res[1][1] = true;
        }
        // e1 |^ e2
        if (e1.position[0] < e2.position[0] + e2.getVelocity()[0]* e2.speed && e2.position[0] <  e1.position[0]+ e1.size[0] + e2.getVelocity()[0]* e2.speed && Math.abs(e2.position[1] - e1.position[1])<e1.size[1]*2){
            e1.canMoveInDirection[0][1] = false;
            e2.canMoveInDirection[0][0] = false;
            res[0][0] = true;
        }
        // e1 |v e2
        if (e2.position[0] < e1.position[0] + e2.getVelocity()[0]* e2.speed && e1.position[0] <  e2.position[0]+ e2.size[0] + e2.getVelocity()[0]* e2.speed && Math.abs(e2.position[1] - e1.position[1])<e1.size[1]*2){
            e2.canMoveInDirection[0][1] = false;
            e1.canMoveInDirection[0][0] = false;
            res[0][1] = true;
        }
        return res;
    }

    public boolean[][] getCanMoveInDirection() {
        return canMoveInDirection;
    }

    public void setCanMoveInDirection(boolean[][] canMoveInDirection) {
        this.canMoveInDirection = canMoveInDirection;
    }

    public static class EntityBuilder {
        protected double speed= -1;
        protected double pv;
        protected int pvMax;
        protected double[] position;
        protected int[] size;
        protected boolean[][] canMoveInDirection;

        public EntityBuilder setSpeed(double speed) {
            this.speed = speed;
            return this;
        }

        public EntityBuilder setPv(double pv) {
            this.pv = pv;
            return this;
        }

        public EntityBuilder setPvMax(int pvMax) {
            this.pvMax = pvMax;
            return this;
        }

        public EntityBuilder setPosition(double[] position) {
            this.position = position;
            return this;
        }

        public EntityBuilder setSize(int[] size) {
            this.size = size;
            return this;
        }

        public EntityBuilder setCanMoveInDirection(boolean[][] canMoveInDirection) {
            this.canMoveInDirection = canMoveInDirection;
            return this;
        }

        public void reformat(){
            if (speed < 0)
                speed = 10;
            if (pvMax <= 0)
                pvMax = 100;
            if (size == null || size.length != 2)
                size = new int[] {1,1};
            if (canMoveInDirection == null || canMoveInDirection.length != 2 && canMoveInDirection[0].length != 2)
                canMoveInDirection = new boolean[][] {{true,true},{true,true}};
            if (pv <= 0)
                pv = pvMax;
        }

        public Entity createEntity() {
            reformat();
            return new Entity(speed, pv, pvMax, position, size, canMoveInDirection);
        }
    }
}
